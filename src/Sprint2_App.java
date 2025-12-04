import core.CooktopController;
import hmi.HmiInput;
import hmi.HmiOutput;
import util.Types.ZoneID;

/**
 * Demo-Anwendung für Sprint 2:
 * - Timer setzen
 * - Timer ticken lassen (Ablauf)
 * - Timer ändern
 * - Timer abbrechen
 *
 * Die eigentliche Logik liegt in CooktopController / TimerManager / HmiInput.
 */
public class Sprint2_App {

    public static void main(String[] args) {
        System.out.println("===== Sprint 2 – Timer-Demo =====");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        // Für alle Szenarien verwenden wir die vordere linke Kochzone
        ZoneID zone = ZoneID.FRONT_LEFT;

        demoScenario1_TimerLäuftAb(hmi, zone);
        demoScenario2_TimerÄndern(hmi, zone);
        demoScenario3_TimerAbbrechen(hmi, zone);

        System.out.println("===== Demo beendet =====");
    }

    /**
     * Szenario 1:
     * - Zone aktivieren
     * - Timer setzen
     * - Tick auf Ablauf -> Zone wird deaktiviert
     */
    private static void demoScenario1_TimerLäuftAb(HmiInput hmi, ZoneID zone) {
        System.out.println("\n--- Szenario 1: Timer läuft ab ---");

        // Zone einschalten (Sprint 1 Funktionalität)
        hmi.selectZone(zone, true);

        // Timer für 30 Sekunden setzen
        System.out.println("Setze Timer auf 30 Sekunden …");
        hmi.setTimer(zone, 30);

        // Zeit simuliert vergehen lassen
        System.out.println("Simuliere 3 Ticks à 10 Sekunden …");
        hmi.tickTimer(10);
        hmi.tickTimer(10);
        hmi.tickTimer(10); // hier sollte der Timer ablaufen

        System.out.println("--- Ende Szenario 1 ---");
    }

    /**
     * Szenario 2:
     * - Timer setzen
     * - Nach einiger Zeit Timer auf neuen Wert ändern
     */
    private static void demoScenario2_TimerÄndern(HmiInput hmi, ZoneID zone) {
        System.out.println("\n--- Szenario 2: Timer wird geändert ---");

        // Zone erneut aktivieren (falls sie am Ende von Szenario 1 deaktiviert wurde)
        hmi.selectZone(zone, true);

        System.out.println("Setze Timer auf 60 Sekunden …");
        hmi.setTimer(zone, 60);

        System.out.println("Simuliere 20 Sekunden …");
        hmi.tickTimer(20);

        System.out.println("Ändere Timer auf 40 Sekunden Restlaufzeit …");
        hmi.changeTimer(zone, 40);

        System.out.println("Simuliere weitere 40 Sekunden …");
        hmi.tickTimer(20);
        hmi.tickTimer(20); // hier sollte der geänderte Timer ablaufen

        System.out.println("--- Ende Szenario 2 ---");
    }

    /**
     * Szenario 3:
     * - Timer setzen
     * - Vor Ablauf manuell abbrechen
     */
    private static void demoScenario3_TimerAbbrechen(HmiInput hmi, ZoneID zone) {
        System.out.println("\n--- Szenario 3: Timer wird abgebrochen ---");

        hmi.selectZone(zone, true);

        System.out.println("Setze Timer auf 45 Sekunden …");
        hmi.setTimer(zone, 45);

        System.out.println("Simuliere 15 Sekunden …");
        hmi.tickTimer(15);

        System.out.println("Breche Timer ab …");
        hmi.cancelTimer(zone);

        System.out.println("Simuliere weitere 40 Sekunden (es darf kein Auto-Off mehr passieren) …");
        hmi.tickTimer(20);
        hmi.tickTimer(20);

        System.out.println("--- Ende Szenario 3 ---");
    }
}
