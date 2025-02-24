package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private static final int RIGHT_LOCK_PIN = 1234;
    private static final int WRONG_LOCK_PIN = 4321;
    private static final int INITIAL_NUM_ATTEMPTS = 0;
    private static final int FIRST_ATTEMPT = 1;
    private static final int MAX_ATTEMPTS = 3;
    private static final String PIN_NOT_SET_MESSAGE = "The PIN is not set";
    private SmartDoorLock lock;

    @BeforeEach
    void beforeEach() {
        this.lock = new SmartDoorLockImpl();
    }

    @Test
    void testUnlockCorrectly() {
        lock.setPin(RIGHT_LOCK_PIN);
        try {
            lock.lock();
        } catch (Exception e) {}
        lock.unlock(RIGHT_LOCK_PIN);
        assertFalse(lock.isLocked());
    }

    @Test
    void testUnlockIncorrectly() {
        lock.setPin(RIGHT_LOCK_PIN);
        try {
            lock.lock();
        } catch (Exception e) {}
        lock.unlock(WRONG_LOCK_PIN);
        assertAll(
                () -> assertTrue(lock.isLocked()),
                () -> assertEquals(FIRST_ATTEMPT, lock.getFailedAttempts())
        );
    }

    @Test
    void testUnlockAfterMaxAttempts() {
        lock.setPin(RIGHT_LOCK_PIN);
        try {
            lock.lock();
        } catch (Exception e) {}

        for (int i = 0; i < MAX_ATTEMPTS; i++)
            lock.unlock(WRONG_LOCK_PIN);

        assertAll(
                () -> assertTrue(lock.isLocked()),
                () -> assertTrue(lock.isBlocked()),
                () -> assertEquals(MAX_ATTEMPTS, lock.getFailedAttempts())
        );
    }

    @Test
    void testResetLock() {
        lock.setPin(RIGHT_LOCK_PIN);
        try {
            lock.lock();
        } catch (Exception e) {}

        lock.unlock(WRONG_LOCK_PIN);

        lock.reset();
        assertAll(
                () -> assertEquals(INITIAL_NUM_ATTEMPTS, lock.getFailedAttempts()),
                () -> assertFalse(lock.isLocked()),
                () -> assertFalse(lock.isBlocked())
        );
    }

    @Test
    void testExceptionPinNotSet() {
        Exception exception = assertThrows(Exception.class, () -> lock.lock());
        assertEquals(PIN_NOT_SET_MESSAGE, exception.getMessage());
    }
}
