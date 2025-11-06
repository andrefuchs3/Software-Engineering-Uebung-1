package core;

import power.PowerControl;
import safety.SafetyManager;

public class CooktopController {
    private final PowerControl power = new PowerControl();

    public void increasePower(int zoneId) {
        if (SafetyManager.getInstance().isLocked()) return;
        power.increaseLevel(zoneId);
        // TODO: HmiOutput aktualisieren
    }
}
