package hmi;

import core.CooktopController;
import util.Types.ZoneID;

/**
 * Eingabe-Fassade: Methoden, die in der UI (Touch) auslösbar wären.
 * Sprint 2 erweitert die Eingaben um Timer-Funktionen.
 */
public class HmiInput {

    private final CooktopController controller;

    public HmiInput(CooktopController controller) {
        this.controller = controller;
    }

    // -------------------------------------------------------------------------
    // Sprint 1 – bestehende Eingaben
    // -------------------------------------------------------------------------

    public void selectZone(ZoneID z, boolean active) {
        controller.setZoneActive(z, active);
    }

    public void increasePower(ZoneID z) {
        controller.increasePower(z);
    }

    public void decreasePower(ZoneID z) {
        controller.decreasePower(z);
    }

    public void toggleChildLock() {
        controller.toggleChildLock();
    }

    // -------------------------------------------------------------------------
    // Sprint 2 – Timer-Eingaben
    // -------------------------------------------------------------------------

    /** Timer für eine Zone setzen (in Sekunden). */
    public void setTimer(ZoneID z, int seconds) {
        controller.setTimer(z, seconds);
    }

    /** Timerdauer ändern. */
    public void changeTimer(ZoneID z, int seconds) {
        controller.changeTimer(z, seconds);
    }

    /** Timer abbrechen. */
    public void cancelTimer(ZoneID z) {
        controller.cancelTimer(z);
    }

    /** Simuliert einen Zeit-Tick für alle aktiven Timer. */
    public void tickTimer() {
        controller.handleTimerTick();
    }
}
