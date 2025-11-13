package core;

import util.Types.ZoneID;
import java.util.EnumMap;

/** Verwalten, ob eine Zone aktiv ist */
public class ZoneManager {
    private final EnumMap<ZoneID, Boolean> active = new EnumMap<>(ZoneID.class);

    public ZoneManager() {
        for (ZoneID z : ZoneID.values()) active.put(z, false);
    }

    public void setActive(ZoneID zone, boolean isActive) {
        active.put(zone, isActive);
    }

    public boolean isActive(ZoneID zone) {
        return Boolean.TRUE.equals(active.get(zone));
    }
}
