package hmi;

import util.Types.ZoneID;

/**
 * Vereinfachte Anzeige-Ausgabe (Platzhalter: Konsole/Log).
 * Wird in Sprint 2 um Timer- und Fehleranzeigen erweitert.
 */
public class HmiOutput {

    // -------------------------------------------------------------------------
    // Basisanzeigen (Sprint 1)
    // -------------------------------------------------------------------------

    public void showActiveZone(ZoneID zone, boolean active) {
        System.out.printf("[HMI] Zone %s %s%n", zone, active ? "AKTIV" : "INAKTIV");
    }

    public void showPowerLevel(ZoneID zone, int level) {
        System.out.printf("[HMI] Zone %s -> Leistungsstufe %d%n", zone, level);
    }

    public void showLock(boolean locked) {
        System.out.printf("[HMI] Kindersicherung: %s%n", locked ? "AKTIV" : "AUS");
    }

    public void showError(String message) {
        System.out.printf("[HMI] FEHLER: %s%n", message);
    }

    // -------------------------------------------------------------------------
    // Erweiterungen Sprint 2 – Timer & Rückmeldungen
    // -------------------------------------------------------------------------

    /** Anzeige der verbleibenden Zeit eines Timers. */
    public void showTimer(ZoneID zone, int remainingSeconds) {
        System.out.printf("[HMI] Timer %s: %ds verbleibend%n", zone, remainingSeconds);
    }

    /** Meldung, dass der Timer einer Zone abgelaufen ist. */
    public void showTimerExpired(ZoneID zone) {
        System.out.printf("[HMI] Timer abgelaufen für %s%n", zone);
    }

    /** Einfache "akustische" Rückmeldung über die Konsole. */
    public void beep() {
        System.out.println("[HMI] *BEEP*");
    }
}
