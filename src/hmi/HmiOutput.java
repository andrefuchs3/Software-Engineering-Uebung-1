package hmi;

import util.Types.ZoneID;

/** Vereinfachte Anzeige-Ausgabe (Platzhalter: Konsole/Log) */
public class HmiOutput {
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
}
