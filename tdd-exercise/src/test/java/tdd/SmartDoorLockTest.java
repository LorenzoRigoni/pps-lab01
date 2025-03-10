package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private static final int RIGHT_LOCK_PIN = 1234;
    private static final int WRONG_LOCK_PIN = 4321;
    private static final int INITIAL_NUM_ATTEMPTS = 0;
    private static final int FIRST_ATTEMPT = 1;
    private static int MAX_ATTEMPTS;
    private SmartDoorLock lock;

    @BeforeEach
    void beforeEach() {
        this.lock = new SmartDoorLockImpl();
        MAX_ATTEMPTS = this.lock.getMaxAttempts();
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
        assertThrows(Exception.class, () -> lock.lock());
    }

    @Test
    void testSetPinWhenIsLocked() {
        lock.setPin(RIGHT_LOCK_PIN);

        try {
            lock.lock();
        } catch (Exception e) {}

        lock.setPin(WRONG_LOCK_PIN);
        lock.unlock(WRONG_LOCK_PIN);

        assertAll(
                () -> assertEquals(FIRST_ATTEMPT, lock.getFailedAttempts()),
                () -> assertTrue(lock.isLocked())
        );
    }

    @Test
    void testSetPinWhenIsBlocked() {
        lock.setPin(RIGHT_LOCK_PIN);

        try {
            lock.lock();
        } catch (Exception e) {}

        lock.setPin(WRONG_LOCK_PIN);

        for (int i = 0; i < MAX_ATTEMPTS; i++)
            lock.unlock(WRONG_LOCK_PIN);

        assertAll(
                () -> assertTrue(lock.isLocked()),
                () -> assertTrue(lock.isBlocked()),
                () -> assertEquals(MAX_ATTEMPTS, lock.getFailedAttempts())
        );
    }
}
