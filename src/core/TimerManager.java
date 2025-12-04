package core;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.Types.ZoneID;

/**
 * Verwaltet einfache Timer pro Kochzone.
 * Zeit wird in "Sekunden" als int geführt und über tick() heruntergezählt.
 */
public class TimerManager {

    private final Map<ZoneID, Integer> remainingTime = new HashMap<>();

    /** Startet oder überschreibt den Timer für eine Zone. */
    public void startTimer(ZoneID zone, int seconds) {
        if (seconds <= 0) {
            remainingTime.remove(zone);
        } else {
            remainingTime.put(zone, seconds);
        }
    }

    /** Ändert den bestehenden Timer (Shortcut auf startTimer). */
    public void changeTimer(ZoneID zone, int seconds) {
        startTimer(zone, seconds);
    }

    /** Bricht den Timer für eine Zone ab. */
    public void cancelTimer(ZoneID zone) {
        remainingTime.remove(zone);
    }

    /**
     * Zählt alle Timer um 1 Sekunde herunter.
     * Gibt eine Liste aller Zonen zurück, deren Timer abgelaufen sind.
     */
    public List<ZoneID> tick() {
        List<ZoneID> expired = new ArrayList<>();

        for (ZoneID zone : new ArrayList<>(remainingTime.keySet())) {
            int value = remainingTime.get(zone) - 1;
            if (value <= 0) {
                remainingTime.remove(zone);
                expired.add(zone);
            } else {
                remainingTime.put(zone, value);
            }
        }

        return expired;
    }

    /** Liefert die Restzeit für eine Zone (oder 0, falls kein Timer aktiv ist). */
    public int getRemainingTime(ZoneID zone) {
        return remainingTime.getOrDefault(zone, 0);
    }
}
