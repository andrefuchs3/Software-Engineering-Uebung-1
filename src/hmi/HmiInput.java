package hmi;

import core.CooktopController;
import util.Types.ZoneID;

/** Eingabe-Fassade: Methoden, die in der UI (Touch) auslösbar wären */
public class HmiInput {
    private final CooktopController controller;

    public HmiInput(CooktopController controller) {
        this.controller = controller;
    }

    public void selectZone(ZoneID z, boolean active) { controller.setZoneActive(z, active); }
    public void increasePower(ZoneID z)              { controller.increasePower(z); }
    public void decreasePower(ZoneID z)              { controller.decreasePower(z); }
    public void toggleChildLock()                    { controller.toggleChildLock(); }
}
