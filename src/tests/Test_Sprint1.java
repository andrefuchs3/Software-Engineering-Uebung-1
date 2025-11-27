import core.CooktopController;
import hmi.HmiInput;
import hmi.HmiOutput;
import power.PowerControl;
import safety.SafetyManager;
import util.Types.ZoneID;

public class Test_Sprint1 {

    public static void main(String[] args) {
        System.out.println("===== Sprint 1 – Testdurchlauf =====");

        runMT01_PowerControl();
        runMT02_SafetyManager();
        runMT03_ZoneIdEnum();

        runIT01_HmiInput_CooktopController();
        runIT02_CooktopController_PowerControl();
        runIT03_CooktopController_SafetyManager();

        System.out.println("===== Testdurchlauf beendet =====");
    }

    // ---------------- Modulebene ----------------

    // MT-01 – PowerControl: Leistungsstufe erhöhen
    private static void runMT01_PowerControl() {
        System.out.println("\n--- MT-01: PowerControl – Leistungsstufe erhöhen ---");

        PowerControl power = new PowerControl();
        ZoneID zone = ZoneID.FRONT_LEFT;

        // Vorbedingung: Level = 5 (wir erhöhen 5x von 0 auf 5)
        for (int i = 0; i < 5; i++) {
            power.increaseLevel(zone);
        }
        int before = power.getLevel(zone);
        power.increaseLevel(zone);
        int after = power.getLevel(zone);

        System.out.println("Vorbedingung: Level = " + before);
        System.out.println("Aktion: increaseLevel(" + zone + ")");
        System.out.println("Erwartete Nachbedingung: Level = " + (before + 1));
        System.out.println("Tatsächliche Nachbedingung: Level = " + after);
    }

    // MT-02 – SafetyManager: Sperren / Entsperren
    private static void runMT02_SafetyManager() {
        System.out.println("\n--- MT-02: SafetyManager – Sperren / Entsperren ---");

        SafetyManager sm = SafetyManager.getInstance();

        sm.unlockInput(); // Vorbedingung: entsperrt
        System.out.println("Vorbedingung: isLocked() = " + sm.isLocked());

        sm.lockInput();   // Aktion
        boolean locked = sm.isLocked();

        System.out.println("Aktion: lockInput()");
        System.out.println("Erwartete Reaktion: isLocked() = true");
        System.out.println("Tatsächliche Reaktion: isLocked() = " + locked);

        sm.unlockInput(); // Aufräumen
    }

    // MT-03 – ZoneID Enum: alle Zonen vorhanden?
    private static void runMT03_ZoneIdEnum() {
        System.out.println("\n--- MT-03: ZoneID – Auflistung aller Kochzonen ---");

        ZoneID[] values = ZoneID.values();

        System.out.println("Erwartete Werte: FRONT_LEFT, FRONT_RIGHT, BACK_LEFT, BACK_RIGHT");
        System.out.println("Tatsächliche Werte:");
        for (ZoneID z : values) {
            System.out.println(" - " + z);
        }
    }

    // ---------------- Integrationsebene ----------------

    // IT-01 – HmiInput ↔ CooktopController (Zone aktivieren)
    private static void runIT01_HmiInput_CooktopController() {
        System.out.println("\n--- IT-01: HmiInput ↔ CooktopController – Zone aktivieren ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        System.out.println("Vorbedingung: Zone FRONT_LEFT ist inaktiv (Initialzustand).");
        System.out.println("Aktion: hmi.selectZone(FRONT_LEFT, true)");

        hmi.selectZone(ZoneID.FRONT_LEFT, true);

        System.out.println("Erwartete Wirkung: Ausgabe zeigt Zone FRONT_LEFT = AKTIV.");
        System.out.println("Tatsächliche Wirkung: siehe HMI-Ausgaben oben.");
    }

    // IT-02 – CooktopController ↔ PowerControl (Leistungsstufe erhöhen)
    private static void runIT02_CooktopController_PowerControl() {
        System.out.println("\n--- IT-02: CooktopController ↔ PowerControl – Leistungsstufe erhöhen ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        System.out.println("Vorbedingung: Zone FRONT_LEFT wird aktiviert.");
        hmi.selectZone(ZoneID.FRONT_LEFT, true);

        System.out.println("Aktion: hmi.increasePower(FRONT_LEFT)");
        hmi.increasePower(ZoneID.FRONT_LEFT);

        System.out.println("Erwartete Wirkung: Leistungsstufe der Zone wird erhöht und über HMI angezeigt.");
        System.out.println("Tatsächliche Wirkung: siehe HMI-Ausgaben oben.");
    }

    // IT-03 – CooktopController ↔ SafetyManager (Kindersicherung blockiert)
    private static void runIT03_CooktopController_SafetyManager() {
        System.out.println("\n--- IT-03: CooktopController ↔ SafetyManager – Kindersicherung blockiert ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        System.out.println("Vorbedingung: Zone FRONT_LEFT wird aktiviert, Kindersicherung ist AUS.");
        hmi.selectZone(ZoneID.FRONT_LEFT, true);

        System.out.println("Aktion 1: hmi.toggleChildLock()  (Kindersicherung EIN)");
        hmi.toggleChildLock();

        System.out.println("Aktion 2: hmi.increasePower(FRONT_LEFT)  (soll BLOCKIERT werden)");
        hmi.increasePower(ZoneID.FRONT_LEFT);

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - SafetyManager meldet isLocked() = true");
        System.out.println(" - HMI zeigt Fehlermeldung / keine Erhöhung der Stufe");
        System.out.println("Tatsächliche Wirkung: siehe HMI-Ausgaben oben.");
    }
}
