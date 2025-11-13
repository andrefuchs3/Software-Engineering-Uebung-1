package core;

import hmi.HmiOutput;
import power.PowerControl;
import safety.SafetyManager;
import util.Types.ZoneID;

/** Zentrale Steuerlogik: prüft Lock, delegiert an Zone/Power, aktualisiert Anzeige */
public class CooktopController {
    private final ZoneManager zones = new ZoneManager();
    private final PowerControl power = new PowerControl();
    private final HmiOutput out;

    public CooktopController(HmiOutput out) {
        this.out = out;
    }

    /** Zonenaktivierung/-deaktivierung (F-01, F-02) */
    public void setZoneActive(ZoneID z, boolean active) {
        if (SafetyManager.getInstance().isLocked()) {
            out.showError("Bedienung gesperrt");
            out.showLock(true);
            return;
        }
        zones.setActive(z, active);
        out.showActiveZone(z, active);
        if (active) out.showPowerLevel(z, power.getLevel(z)); // F-07
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
        if (sm.isLocked()) sm.unlockInput(); else sm.lockInput();
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
}
