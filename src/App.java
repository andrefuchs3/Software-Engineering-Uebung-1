import core.CooktopController;
import hmi.HmiInput;
import hmi.HmiOutput;
import util.Types.ZoneID;

public class App {
    public static void main(String[] args) {
        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        hmi.selectZone(ZoneID.FRONT_LEFT, true);
        hmi.increasePower(ZoneID.FRONT_LEFT);
        hmi.increasePower(ZoneID.FRONT_LEFT);

        hmi.toggleChildLock();            // sperren
        hmi.increasePower(ZoneID.FRONT_LEFT); // wird blockiert
        hmi.toggleChildLock();            // entsperren
        hmi.decreasePower(ZoneID.FRONT_LEFT);
    }
}
