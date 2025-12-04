package core;

import java.util.List;

import hmi.HmiOutput;
import power.PowerControl;
import safety.SafetyManager;
import util.Types.ZoneID;

/**
 * Zentrale Steuerlogik:
 * - prüft Kindersicherung
 * - delegiert an ZoneManager / PowerControl / TimerManager
 * - aktualisiert die Anzeige
 */
public class CooktopController {

    private final ZoneManager zones = new ZoneManager();
    private final PowerControl power = new PowerControl();
    private final TimerManager timers = new TimerManager();   // NEU: Timerverwaltung
    private final HmiOutput out;

    public CooktopController(HmiOutput out) {
        this.out = out;
    }

    // -------------------------------------------------------------------------
    // Sprint 1 – bestehende Funktionen
    // -------------------------------------------------------------------------

    /** Zonenaktivierung/-deaktivierung (F-01, F-02) */
    public void setZoneActive(ZoneID z, boolean active) {
        if (SafetyManager.getInstance().isLocked()) {
            out.showError("Bedienung gesperrt");
            out.showLock(true);
            return;
        }
        zones.setActive(z, active);
        out.showActiveZone(z, active);

        // Leistungsstufe anzeigen, wenn Zone aktiv ist (F-07)
        if (active) {
            out.showPowerLevel(z, power.getLevel(z));
        } else {
            // optional: Timer beim Deaktivieren zurücksetzen
            timers.cancelTimer(z);
            out.showTimer(z, 0);
        }
    }

    /** Leistungsstufe + (F-03/F-04, F-07) */
    public void increasePower(ZoneID z) {
        if (!preCheck(z)) return;
        power.increaseLevel(z);
        out.showPowerLevel(z, power.getLevel(z));
    }

    /** Leistungsstufe - (F-03/F-04, F-07) */
    public void decreasePower(ZoneID z) {
        if (!preCheck(z)) return;
        power.decreaseLevel(z);
        out.showPowerLevel(z, power.getLevel(z));
    }

    /** Direkte Stufe setzen (intern/optional) */
    public void setPowerLevel(ZoneID z, int level) {
        if (!preCheck(z)) return;
        power.setLevel(z, level);
        out.showPowerLevel(z, power.getLevel(z));
    }

    /** Kindersicherung toggeln (F-13) */
    public void toggleChildLock() {
        SafetyManager sm = SafetyManager.getInstance();
        if (sm.isLocked()) {
            sm.unlockInput();
        } else {
            sm.lockInput();
        }
        out.showLock(sm.isLocked());
    }

    /** Basisprüfung: Lock & Zone aktiv */
    private boolean preCheck(ZoneID z) {
        if (SafetyManager.getInstance().isLocked()) {
            out.showError("Bedienung gesperrt");
            out.showLock(true);
            return false;
        }
        if (!zones.isActive(z)) {
            out.showError("Zone nicht aktiv");
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // Sprint 2 – Timerfunktionen (F-09, F-10, F-11, F-12, F-08)
    // -------------------------------------------------------------------------

    /** Timer für eine aktive Zone setzen oder ändern. */
    public void setTimer(ZoneID z, int seconds) {
        if (!preCheck(z)) return;
        if (seconds <= 0) {
            out.showError("Timerdauer muss > 0 sein");
            return;
        }
        timers.startTimer(z, seconds);
        out.showTimer(z, seconds);
    }

    /** Convenience: Timeränderung ist intern dasselbe wie setzen. */
    public void changeTimer(ZoneID z, int seconds) {
        setTimer(z, seconds);
    }

    /** Timer für eine Zone abbrechen. */
    public void cancelTimer(ZoneID z) {
        timers.cancelTimer(z);
        out.showTimer(z, 0);
    }

    /**
     * Simulierter Zeit-Tick (z.B. 1 Sekunde).
     * Wird z.B. von HmiInput.tickTimer() aufgerufen.
     */
    public void handleTimerTick() {
        List<ZoneID> expired = timers.tick();

        for (ZoneID z : expired) {
            // Auto-Deaktivierung der Zone (F-10)
            zones.setActive(z, false);
            power.setLevel(z, 0);

            out.showActiveZone(z, false);
            out.showPowerLevel(z, 0);

            // Visuelle & "akustische" Rückmeldung (F-11)
            out.showTimerExpired(z);
            out.beep();
        }
    }
}
