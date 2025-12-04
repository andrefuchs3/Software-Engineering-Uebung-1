import core.CooktopController;
import hmi.HmiInput;
import hmi.HmiOutput;
import util.Types.ZoneID;

public class Sprint2_App {

    public static void main(String[] args) {

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        System.out.println("===== Sprint 2 – Demo Timerfunktion =====");

        demoTimerLaeuftAb(hmi);
        demoTimerAendern(hmi);
        demoTimerAbbrechen(hmi);

        System.out.println("===== Demo beendet =====");
    }

    // ------------------------------------------------------------------
    // 1) Timer läuft normal ab
    // ------------------------------------------------------------------
    private static void demoTimerLaeuftAb(HmiInput hmi) {
        System.out.println("\n--- Szenario 1: Timer läuft ab ---");

        ZoneID zone = ZoneID.FRONT_LEFT;

        // Zone aktivieren und Timer setzen, z.B. 5 Sekunden
        hmi.selectZone(zone, true);
        hmi.setTimer(zone, 5);

        // 5 Ticks simulieren (z.B. 1 Tick = 1 Sekunde)
        for (int i = 0; i < 5; i++) {
            hmi.tickTimer();
        }
    }

    // ------------------------------------------------------------------
    // 2) Timer wird geändert (Restzeit verkürzt)
    // ------------------------------------------------------------------
    private static void demoTimerAendern(HmiInput hmi) {
        System.out.println("\n--- Szenario 2: Timer wird geändert ---");

        ZoneID zone = ZoneID.FRONT_RIGHT;

        hmi.selectZone(zone, true);
        hmi.setTimer(zone, 10);   // Start mit 10 Sekunden

        // ein paar Ticks laufen lassen, z.B. 4 Sekunden
        for (int i = 0; i < 4; i++) {
            hmi.tickTimer();
        }

        // Timer verkürzen, z.B. auf 3 Sekunden Restzeit
        hmi.changeTimer(zone, 3);

        // weitere 3 Ticks – Timer sollte jetzt ablaufen
        for (int i = 0; i < 3; i++) {
            hmi.tickTimer();
        }
    }

    // ------------------------------------------------------------------
    // 3) Timer wird abgebrochen
    // ------------------------------------------------------------------
    private static void demoTimerAbbrechen(HmiInput hmi) {
        System.out.println("\n--- Szenario 3: Timer wird abgebrochen ---");

        ZoneID zone = ZoneID.BACK_LEFT;

        hmi.selectZone(zone, true);
        hmi.setTimer(zone, 8);

        // 3 Ticks laufen lassen
        for (int i = 0; i < 3; i++) {
            hmi.tickTimer();
        }

        // Timer abbrechen
        hmi.cancelTimer(zone);

        // weitere Ticks – es darf kein Ablauf mehr gemeldet werden
        for (int i = 0; i < 5; i++) {
            hmi.tickTimer();
        }
    }
}
