package power;

import util.Types.ZoneID;
import java.util.EnumMap;

/** Setzt/ändert die Leistungsstufe je Kochzone (0..9) */
public class PowerControl {
    private final EnumMap<ZoneID, Integer> level = new EnumMap<>(ZoneID.class);

    public PowerControl() {
        for (ZoneID z : ZoneID.values()) level.put(z, 0);
    }

    public void increaseLevel(ZoneID z) {
        level.put(z, Math.min(9, level.get(z) + 1));
    }

    public void decreaseLevel(ZoneID z) {
        level.put(z, Math.max(0, level.get(z) - 1));
    }

    public void setLevel(ZoneID z, int newLevel) {
        level.put(z, clamp(newLevel));
    }

    public int getLevel(ZoneID z) { return level.get(z); }

    private int clamp(int v) { return Math.max(0, Math.min(9, v)); }
}
