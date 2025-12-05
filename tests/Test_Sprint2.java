import core.CooktopController;
import hmi.HmiInput;
import hmi.HmiOutput;
import power.PowerControl;
import safety.SafetyManager;
import util.Types.ZoneID;

public class Test_Sprint2 {

    public static void main(String[] args) {
        System.out.println("===== Sprint 2 – Testdurchlauf (inkl. Regression Sprint 1) =====");

        // ---- Regression: Tests aus Sprint 1 ----
        runMT01_PowerControl();
        runMT02_SafetyManager();
        runMT03_ZoneIdEnum();

        runIT01_HmiInput_CooktopController();
        runIT02_CooktopController_PowerControl();
        runIT03_CooktopController_SafetyManager();

        // ---- Neue Tests für Sprint 2 (Timer / Status / Fehleranzeige) ----
        runMT04_Timer_StartUndTick();
        runMT05_Timer_AutoDeaktivierung();
        runMT06_Timer_AenderungUndAbbruch();

        runIT04_Statusanzeige_MitTimer();
        runIT05_Fehleranzeige_MitKindersicherung();
        runIT06_Timer_Setzen_UeberHmi();
        runIT07_Timer_AutoDeaktivierung();
        runIT08_Timer_Ablauf_MitBeep();
        runIT09_Timer_AenderungUndAbbruch();

        System.out.println("===== Testdurchlauf beendet =====");
    }

    // --------------------------------------------------------------------
    //  Modultests – aus Sprint 1
    // --------------------------------------------------------------------

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

    // --------------------------------------------------------------------
    //  Integrationstests – aus Sprint 1
    // --------------------------------------------------------------------

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

    // --------------------------------------------------------------------
    //  Neue Modultests – Sprint 2 (Timer)
    // --------------------------------------------------------------------

    // MT-04 – Timer: Start und Tick (Grundfunktion)
    private static void runMT04_Timer_StartUndTick() {
        System.out.println("\n--- MT-04: Timer – Start und Tick (Grundfunktion) ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;
        hmi.selectZone(zone, true);

        System.out.println("Aktion: hmi.setTimer(FRONT_LEFT, 3)");
        hmi.setTimer(zone, 3);

        System.out.println("Dann 3x hmi.tickTimer() – Timer sollte bis 0 herunterzählen.");
        for (int i = 0; i < 3; i++) {
            hmi.tickTimer();
        }
        System.out.println("Erwartete Wirkung: HMI zeigt ablaufenden Timer an.");
    }

    // MT-05 – Timer: Auto-Deaktivierung nach Ablauf
    private static void runMT05_Timer_AutoDeaktivierung() {
        System.out.println("\n--- MT-05: Timer – Auto-Deaktivierung nach Ablauf ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;
        hmi.selectZone(zone, true);

        System.out.println("Aktion: hmi.setTimer(FRONT_LEFT, 2) und 2x hmi.tickTimer()");
        hmi.setTimer(zone, 2);
        hmi.tickTimer();
        hmi.tickTimer();

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - Timer läuft ab");
        System.out.println(" - Zone wird deaktiviert (Status AUS/Restwärme)");
        System.out.println(" - HMI zeigt Timerablauf an.");
    }

    // MT-06 – Timer: Änderung / Abbruch
    private static void runMT06_Timer_AenderungUndAbbruch() {
        System.out.println("\n--- MT-06: Timer – Änderung und Abbruch ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;
        hmi.selectZone(zone, true);

        System.out.println("Aktion: setTimer(10), dann changeTimer(5) und schließlich cancelTimer()");
        hmi.setTimer(zone, 10);
        hmi.changeTimer(zone, 5);
        hmi.tickTimer();
        hmi.cancelTimer(zone);

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - Timer wird zunächst gesetzt, dann verkürzt");
        System.out.println(" - Nach cancelTimer() läuft der Timer nicht weiter.");
    }

    // --------------------------------------------------------------------
    //  Neue Integrationstests – Sprint 2 (Timer & Anzeige)
    // --------------------------------------------------------------------

    // IT-04 – Statusanzeige (aus, aktiv, Restwärme) in Verbindung mit Timer
    private static void runIT04_Statusanzeige_MitTimer() {
        System.out.println("\n--- IT-04: Statusanzeige – aus/aktiv/Restwärme mit Timer ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("1) Zone aktivieren");
        hmi.selectZone(zone, true);

        System.out.println("2) Timer setzen (3 Sekunden)");
        hmi.setTimer(zone, 3);

        System.out.println("3) Timer ablaufen lassen (3x tickTimer)");
        hmi.tickTimer();
        hmi.tickTimer();
        hmi.tickTimer();

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - HMI zeigt Übergang von AKTIV zu AUS/Restwärme an.");
    }

    // IT-05 – Fehler-/Sperrzustände mit Timer und Kindersicherung
    private static void runIT05_Fehleranzeige_MitKindersicherung() {
        System.out.println("\n--- IT-05: Fehler-/Sperrzustände mit Kindersicherung ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;

        System.out.println("Vorbedingung: Zone aktiv, Kindersicherung AUS.");
        hmi.selectZone(zone, true);

        System.out.println("Aktion 1: Kindersicherung einschalten (toggleChildLock)");
        hmi.toggleChildLock();

        System.out.println("Aktion 2: setTimer(FRONT_LEFT, 5) bei aktiver Sperre");
        hmi.setTimer(zone, 5);

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - SafetyManager meldet gesperrt");
        System.out.println(" - HMI zeigt Fehlermeldung / keine Timer-Übernahme.");
        SafetyManager.getInstance().unlockInput(); // Aufräumen für Folgetests
    }

    // IT-06 – Timerfunktion über HmiInput ↔ CooktopController ↔ TimerManager
    private static void runIT06_Timer_Setzen_UeberHmi() {
        System.out.println("\n--- IT-06: Timer setzen über HmiInput ↔ CooktopController ↔ TimerManager ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;
        hmi.selectZone(zone, true);

        System.out.println("Aktion: hmi.setTimer(FRONT_LEFT, 4)");
        hmi.setTimer(zone, 4);

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - HmiInput leitet Aufruf an CooktopController weiter");
        System.out.println(" - CooktopController startet Timer im TimerManager");
        System.out.println(" - HMI zeigt gesetzten Timer.");
    }

    // IT-07 – Auto-Deaktivierung nach Timerablauf
    private static void runIT07_Timer_AutoDeaktivierung() {
        System.out.println("\n--- IT-07: Auto-Deaktivierung nach Timerablauf ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;
        hmi.selectZone(zone, true);

        System.out.println("Aktion: setTimer(2) und 2x tickTimer()");
        hmi.setTimer(zone, 2);
        hmi.tickTimer();
        hmi.tickTimer();

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - Timer läuft ab, CooktopController deaktiviert Zone");
        System.out.println(" - HMI zeigt Status AUS/Restwärme.");
    }

    // IT-08 – Visuelle & akustische Rückmeldung nach Ablauf
    private static void runIT08_Timer_Ablauf_MitBeep() {
        System.out.println("\n--- IT-08: Visuelle & akustische Rückmeldung nach Ablauf ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;
        hmi.selectZone(zone, true);

        System.out.println("Aktion: setTimer(1) und 1x tickTimer()");
        hmi.setTimer(zone, 1);
        hmi.tickTimer();

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - HMI zeigt Timerablauf (showTimerExpired)");
        System.out.println(" - HMI gibt akustisches Signal (beep).");
    }

    // IT-09 – Timeränderung / Abbruch über HmiInput
    private static void runIT09_Timer_AenderungUndAbbruch() {
        System.out.println("\n--- IT-09: Timeränderung / Abbruch über HmiInput ---");

        HmiOutput out = new HmiOutput();
        CooktopController ctl = new CooktopController(out);
        HmiInput hmi = new HmiInput(ctl);

        ZoneID zone = ZoneID.FRONT_LEFT;
        hmi.selectZone(zone, true);

        System.out.println("Aktion: setTimer(10), changeTimer(5), dann cancelTimer()");
        hmi.setTimer(zone, 10);
        hmi.changeTimer(zone, 5);
        hmi.tickTimer();
        hmi.cancelTimer(zone);

        System.out.println("Erwartete Wirkung:");
        System.out.println(" - Timer wird zunächst gesetzt und dann geändert");
        System.out.println(" - Nach cancelTimer() läuft er nicht weiter; keine Auto-Deaktivierung mehr.");
    }
}
