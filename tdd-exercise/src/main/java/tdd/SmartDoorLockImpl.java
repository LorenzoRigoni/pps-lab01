package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private int lockPin;
    private boolean isLocked;
    private boolean isBlocked;
    private int attempts;
    private static final int MAX_ATTEMPTS = 3;
    private static final int PIN_NOT_SET = -1;

    public SmartDoorLockImpl() {
        initialLockState();
    }

    @Override
    public void setPin(int pin) {
        if (!isLocked && !isBlocked())
            this.lockPin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.lockPin == pin && !this.isBlocked)
            this.isLocked = false;
        else if (this.lockPin != pin)
            this.attempts++;
        if (this.attempts >= MAX_ATTEMPTS)
            this.isBlocked = true;
    }

    @Override
    public void lock() throws Exception{
        if (this.lockPin != PIN_NOT_SET)
            this.isLocked = true;
        else
            throw new Exception("The PIN is not set");
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return this.isBlocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {
        initialLockState();
    }

    private void initialLockState() {
        this.lockPin = PIN_NOT_SET;
        this.isLocked = false;
        this.isBlocked = false;
        this.attempts = 0;
    }
}
