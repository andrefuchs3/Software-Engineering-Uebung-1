import core.CooktopController;
import core.TimerManager;
import hmi.HmiInput;
import hmi.HmiOutput;
import util.Types.ZoneID;

public class Test_Sprint2 {

    public static void main(String[] args) {
        System.out.println("===== Sprint 2 – Testdurchlauf =====");

        // --------- Modultests (TimerManager) ---------
        runMT04_Timer_StartUndTick();
        runMT05_Timer_Aendern();
        runMT06_Timer_Abbrechen();

        // --------- Integrationstests (Timer-Ende-zu-Ende) ---------
        runIT04_Timer_Setzen_und_Anzeigen();
        runIT05_Timerablauf_Deaktivierung();
        runIT06_Timer_Aendern_und_Abbrechen();
        runIT07_AutoDeaktivierung_EndToEnd();
        runIT08_Timerablauf_Mit_Beep();
        runIT09_Timer_Aendern_Abbrechen_EndToEnd();

        System.out.println("===== Testdurchlauf beendet =====");
    }

    // =====================================================================
    //  MT-04 – Modul: TimerManager (Timer starten & runterzählen)
    // =====================================================================
    private static void runMT04_Timer_StartUndTick() {
        System.out.println("\n--- MT-04: TimerManager – Timer starten & runterzählen ---");

        TimerManager tm = new TimerManager();
        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("Vorbedingung: Kein Timer für FRONT_LEFT aktiv.");
        System.out.println("Aktion: startTimer(FRONT_LEFT, 5), danach 5× tick().");

        tm.startTimer(zone, 5);
        for (int i = 1; i <= 5; i++) {
            System.out.println("tick() #" + i);
            tm.tick();
        }

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - Restzeit FRONT_LEFT wird von 5 auf 0 heruntergezählt.");
        System.out.println(" - Beim Übergang auf 0 entsteht ein Ablauf-Ereignis für FRONT_LEFT.");
        System.out.println("Nachbedingung: Kein positiver Restwert mehr für FRONT_LEFT.");
        System.out.println("Ergebnis MT-04: MANUELL PRÜFEN (Konsole / Debug-Ausgaben prüfen).");
    }

    // =====================================================================
    //  MT-05 – Modul: TimerManager (Timer ändern)
    // =====================================================================
    private static void runMT05_Timer_Aendern() {
        System.out.println("\n--- MT-05: TimerManager – Timer ändern ---");

        TimerManager tm = new TimerManager();
        ZoneID zone = ZoneID.FRONT_RIGHT;

        System.out.println("Vorbedingung: startTimer(FRONT_RIGHT, 10), Restzeit ≥ 7.");
        tm.startTimer(zone, 10);

        // z.B. 2 Ticks → Restzeit 8 (immer noch ≥ 7)
        tm.tick();
        tm.tick();

        System.out.println("Aktion: changeTimer(FRONT_RIGHT, 3), danach 3× tick().");
        tm.changeTimer(zone, 3);
        for (int i = 1; i <= 3; i++) {
            System.out.println("tick() #" + i);
            tm.tick();
        }

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - Restzeit wird sofort auf 3 gesetzt.");
        System.out.println(" - Nach 3 weiteren tick() läuft der Timer ab (Ablauf-Ereignis FRONT_RIGHT).");
        System.out.println("Nachbedingung: Timer für FRONT_RIGHT ist nach genau 3 Ticks abgelaufen,");
        System.out.println("               keine weiteren Ablauf-Ereignisse nach zusätzlichen Ticks.");
        System.out.println("Ergebnis MT-05: MANUELL PRÜFEN.");
    }

    // =====================================================================
    //  MT-06 – Modul: TimerManager (Timer abbrechen)
    // =====================================================================
    private static void runMT06_Timer_Abbrechen() {
        System.out.println("\n--- MT-06: TimerManager – Timer abbrechen ---");

        TimerManager tm = new TimerManager();
        ZoneID zone = ZoneID.BACK_LEFT;

        System.out.println("Vorbedingung: startTimer(BACK_LEFT, 8).");
        tm.startTimer(zone, 8);

        System.out.println("Aktion: 3× tick(), dann cancelTimer(BACK_LEFT), dann 10× tick().");
        for (int i = 1; i <= 3; i++) {
            System.out.println("tick() vor Cancel #" + i);
            tm.tick();
        }

        tm.cancelTimer(zone);
        System.out.println("Timer für BACK_LEFT abgebrochen.");

        for (int i = 1; i <= 10; i++) {
            System.out.println("tick() nach Cancel #" + i);
            tm.tick();
        }

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - Während der ersten 3 Ticks wird Restzeit reduziert.");
        System.out.println(" - Nach cancelTimer(...) tritt KEIN Ablauf-Ereignis für BACK_LEFT mehr auf.");
        System.out.println("Nachbedingung: Timer für BACK_LEFT ist inaktiv; kein Ablauf nach Cancel.");
        System.out.println("Ergebnis MT-06: MANUELL PRÜFEN.");
    }

    // =====================================================================
    //  IT-04 – HmiInput ↔ CooktopController ↔ TimerManager (Timer setzen & anzeigen)
    // =====================================================================
    private static void runIT04_Timer_Setzen_und_Anzeigen() {
        System.out.println("\n--- IT-04: Timer setzen & anzeigen (HmiInput ↔ Controller ↔ TimerManager ↔ HmiOutput) ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("Vorbedingung: Kochfeld initialisiert, FRONT_LEFT aktiv, kein Timer gesetzt.");
        hmi.selectZone(zone, true);

        System.out.println("Aktion: hmi.setTimer(FRONT_LEFT, 5)");
        hmi.setTimer(zone, 5);

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - HmiInput ruft CooktopController.setTimer(FRONT_LEFT, 5) auf.");
        System.out.println(" - CooktopController delegiert an TimerManager.startTimer(...).");
        System.out.println(" - HmiOutput.showTimer(FRONT_LEFT, 5) zeigt den gesetzten Timerwert.");
        System.out.println("Nachbedingung: Timer für FRONT_LEFT im TimerManager mit Restzeit 5 registriert;");
        System.out.println("               Anzeige zeigt den gesetzten Wert.");
        System.out.println("Ergebnis IT-04: MANUELL PRÜFEN.");
    }

    // =====================================================================
    //  IT-05 – CooktopController ↔ TimerManager ↔ ZoneManager/HmiOutput (Timerablauf)
    // =====================================================================
    private static void runIT05_Timerablauf_Deaktivierung() {
        System.out.println("\n--- IT-05: Timerablauf – Zone deaktivieren & Benutzer informieren ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("Vorbedingung: Zone FRONT_LEFT aktiv, Leistungsstufe > 0, Timer Restzeit = 3, Kindersicherung aus.");
        hmi.selectZone(zone, true);
        // Leistungsstufe > 0 herstellen
        hmi.increasePower(zone);
        hmi.increasePower(zone);

        hmi.setTimer(zone, 3);

        System.out.println("Aktion: 3× hmi.tickTimer() → 3× handleTimerTick() im Controller.");
        hmi.tickTimer();
        hmi.tickTimer();
        hmi.tickTimer();

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - TimerManager meldet Ablauf für FRONT_LEFT.");
        System.out.println(" - CooktopController setzt ZoneManager.setActive(FRONT_LEFT, false).");
        System.out.println(" - Leistungsstufe wird auf 0 gesetzt.");
        System.out.println(" - HmiOutput.showTimerExpired(FRONT_LEFT) und HmiOutput.beep() werden aufgerufen.");
        System.out.println("Nachbedingung: Zone FRONT_LEFT inaktiv, Leistungsstufe = 0;");
        System.out.println("               Anzeige zeigt abgelaufenen Timer mit akustischem Signal.");
        System.out.println("Ergebnis IT-05: MANUELL PRÜFEN.");
    }

    // =====================================================================
    //  IT-06 – HmiInput ↔ CooktopController ↔ TimerManager (Timer ändern & abbrechen)
    // =====================================================================
    private static void runIT06_Timer_Aendern_und_Abbrechen() {
        System.out.println("\n--- IT-06: Timer ändern & abbrechen (BACK_LEFT) ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.BACK_LEFT;

        System.out.println("Vorbedingung: Zone BACK_LEFT aktiv, Timer mit setTimer(BACK_LEFT, 10) gesetzt.");
        hmi.selectZone(zone, true);
        hmi.setTimer(zone, 10);

        System.out.println("Aktion 1: hmi.changeTimer(BACK_LEFT, 3)");
        hmi.changeTimer(zone, 3);

        System.out.println("Aktion 2: 2× hmi.tickTimer()");
        hmi.tickTimer();
        hmi.tickTimer();

        System.out.println("Aktion 3: hmi.cancelTimer(BACK_LEFT)");
        hmi.cancelTimer(zone);

        System.out.println("Aktion 4: 5× hmi.tickTimer()");
        for (int i = 1; i <= 5; i++) {
            System.out.println("tick() nach Cancel #" + i);
            hmi.tickTimer();
        }

        System.out.println("Erwartete Reaktion:");
        System.out.println(" 1. Restzeit im TimerManager wird auf 3 gesetzt und angezeigt.");
        System.out.println(" 2. Nach 2 Ticks ist Restzeit = 1.");
        System.out.println(" 3. Nach cancelTimer(...) wird der Timer gelöscht; Anzeige ggf. ohne Timer.");
        System.out.println(" 4. Kein Ablauf-Ereignis und kein showTimerExpired()/beep() für BACK_LEFT.");
        System.out.println("Nachbedingung: Timer BACK_LEFT inaktiv; Zone bleibt aktiv, keine Deaktivierung durch Timer.");
        System.out.println("Ergebnis IT-06: MANUELL PRÜFEN.");
    }

    // =====================================================================
    //  IT-07 – Auto-Deaktivierung nach Timerablauf (End-to-End)
    // =====================================================================
    private static void runIT07_AutoDeaktivierung_EndToEnd() {
        System.out.println("\n--- IT-07: Auto-Deaktivierung nach Timerablauf (End-to-End) ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("Vorbedingung: FRONT_LEFT aktiv, Timer noch nicht gesetzt, Kindersicherung aus.");
        hmi.selectZone(zone, true);

        System.out.println("Aktion 1: hmi.setTimer(FRONT_LEFT, 2)");
        hmi.setTimer(zone, 2);

        System.out.println("Aktion 2: 2× hmi.tickTimer()");
        hmi.tickTimer();
        hmi.tickTimer();

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - Nach letztem Tick meldet TimerManager Ablauf.");
        System.out.println(" - CooktopController setzt ZoneManager.setActive(FRONT_LEFT, false) und Stufe auf 0.");
        System.out.println(" - Statusanzeige wechselt auf AUS/Restwärme.");
        System.out.println("Nachbedingung: FRONT_LEFT inaktiv, Leistungsstufe = 0; HMI zeigt deaktivierte Zone.");
        System.out.println("Ergebnis IT-07: MANUELL PRÜFEN.");
    }

    // =====================================================================
    //  IT-08 – Visuelle & akustische Rückmeldung nach Timerablauf
    // =====================================================================
    private static void runIT08_Timerablauf_Mit_Beep() {
        System.out.println("\n--- IT-08: Visuelle & akustische Rückmeldung nach Ablauf ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("Vorbedingung: FRONT_LEFT aktiv, Timer noch nicht gesetzt, Kindersicherung aus.");
        hmi.selectZone(zone, true);

        System.out.println("Aktion: hmi.setTimer(FRONT_LEFT, 1) und 1× hmi.tickTimer()");
        hmi.setTimer(zone, 1);
        hmi.tickTimer();

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - Beim Ablauf ruft der Controller HmiOutput.showTimerExpired(FRONT_LEFT)");
        System.out.println("   und anschließend HmiOutput.beep() auf.");
        System.out.println("Nachbedingung: In der Konsole Meldung zum abgelaufenen Timer + Beep-Ausgabe.");
        System.out.println("Ergebnis IT-08: MANUELL PRÜFEN.");
    }

    // =====================================================================
    //  IT-09 – Timeränderung / Abbruch über HmiInput (End-to-End)
    // =====================================================================
    private static void runIT09_Timer_Aendern_Abbrechen_EndToEnd() {
        System.out.println("\n--- IT-09: Timeränderung / Abbruch über HmiInput (End-to-End) ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("Vorbedingung: FRONT_LEFT aktiv, kein Timer gesetzt, Kindersicherung aus.");
        hmi.selectZone(zone, true);

        System.out.println("Aktion 1: hmi.setTimer(FRONT_LEFT, 10)");
        hmi.setTimer(zone, 10);

        System.out.println("Aktion 2: hmi.changeTimer(FRONT_LEFT, 5)");
        hmi.changeTimer(zone, 5);

        System.out.println("Aktion 3: 1× hmi.tickTimer()");
        hmi.tickTimer();

        System.out.println("Aktion 4: hmi.cancelTimer(FRONT_LEFT)");
        hmi.cancelTimer(zone);

        System.out.println("Aktion 5: 5× hmi.tickTimer()");
        for (int i = 1; i <= 5; i++) {
            System.out.println("tick() nach Cancel #" + i);
            hmi.tickTimer();
        }

        System.out.println("Erwartete Reaktion:");
        System.out.println(" - Timer wird zunächst mit 10s angelegt und angezeigt.");
        System.out.println(" - Danach auf 5s geändert und erneut angezeigt.");
        System.out.println(" - Nach einem Tick reduzierte Restzeit (z. B. 4s).");
        System.out.println(" - Nach cancelTimer(...) Entfernen des Timers; weitere Ticks führen");
        System.out.println("   zu keinem showTimerExpired()/beep() für FRONT_LEFT.");
        System.out.println("Nachbedingung: Kein aktiver Timer für FRONT_LEFT, Zone bleibt aktiv,");
        System.out.println("               kein Timerablauf-Ereignis wurde ausgelöst.");
        System.out.println("Ergebnis IT-09: MANUELL PRÜFEN.");
    }
}
