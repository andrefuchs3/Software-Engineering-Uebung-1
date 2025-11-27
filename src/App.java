import core.CooktopController;
import hmi.HmiInput;
import hmi.HmiOutput;
import util.Types.ZoneID;

public class Sprint1_App {

    public static void main(String[] args) {

        System.out.println("===== Sprint 1 – Demoausführung =====");

        // Ausgabe
        HmiOutput out = new HmiOutput();

        // Controller (Kernlogik)
        CooktopController ctl = new CooktopController(out);

        // Eingabe (Touch-Eingaben simuliert)
        HmiInput hmi = new HmiInput(ctl);

        // ---------------------------------------
        // 1) Zone aktivieren
        // ---------------------------------------
        System.out.println("\n--- Schritt 1: Zone aktivieren ---");
        hmi.selectZone(ZoneID.FRONT_LEFT, true);

        // ---------------------------------------
        // 2) Leistung erhöhen
        // ---------------------------------------
        System.out.println("\n--- Schritt 2: Leistung erhöhen ---");
        hmi.increasePower(ZoneID.FRONT_LEFT);
        hmi.increasePower(ZoneID.FRONT_LEFT);

        // ---------------------------------------
        // 3) Kindersicherung aktivieren
        // ---------------------------------------
        System.out.println("\n--- Schritt 3: Kindersicherung aktivieren ---");
        hmi.toggleChildLock();

        // ---------------------------------------
        // 4) Versuch, Leistung zu erhöhen (BLOCKIERT)
        // ---------------------------------------
        System.out.println("\n--- Schritt 4: Leistung erhöhen (soll blockiert werden) ---");
        hmi.increasePower(ZoneID.FRONT_LEFT);

        // ---------------------------------------
        // 5) Kindersicherung deaktivieren
        // ---------------------------------------
        System.out.println("\n--- Schritt 5: Kindersicherung deaktivieren ---");
        hmi.toggleChildLock();

        // ---------------------------------------
        // 6) Wieder Leistung ändern
        // ---------------------------------------
        System.out.println("\n--- Schritt 6: Leistung erneut erhöhen ---");
        hmi.increasePower(ZoneID.FRONT_LEFT);

        System.out.println("\n===== Demo abgeschlossen =====");
    }
}
