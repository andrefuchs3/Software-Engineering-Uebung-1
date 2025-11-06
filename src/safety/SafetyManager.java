package safety;

public final class SafetyManager {
    private static SafetyManager instance;
    private boolean locked;

    private SafetyManager() { this.locked = false; }

    public static synchronized SafetyManager getInstance() {
        if (instance == null) instance = new SafetyManager();
        return instance;
    }

    public boolean isLocked() { return locked; }
    public void lockInput() { locked = true; }
    public void unlockInput() { locked = false; }
}
