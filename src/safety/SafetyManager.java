package safety;

/** Kindersicherung als Singleton (eine Instanz im ganzen System) */
public final class SafetyManager {
    private static volatile SafetyManager instance;
    private boolean locked;

    private SafetyManager() {
        this.locked = false;
    }

    public static SafetyManager getInstance() {
        if (instance == null) {
            synchronized (SafetyManager.class) {
                if (instance == null) instance = new SafetyManager();
            }
        }
        return instance;
    }

    public boolean isLocked() { return locked; }
    public void lockInput()   { locked = true; }
    public void unlockInput() { locked = false; }
}
