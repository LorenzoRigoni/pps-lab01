package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final int SINGLE_VALUE_TO_PUSH = 1;
    private static final int SINGLE_VALUE_TO_POP = 1;
    private MinMaxStack stack;

    @BeforeEach
    void beforeEach() {
        this.stack = new MinMaxStackImpl();
    }

    @Test
    void testPush() {
        stack.push(SINGLE_VALUE_TO_PUSH);
        assertEquals(SINGLE_VALUE_TO_PUSH, stack.peek());
    }

    @Test
    void testPop() {
        stack.push(SINGLE_VALUE_TO_PUSH);
        assertAll(
                () -> assertEquals(SINGLE_VALUE_TO_POP, stack.pop()),
                () -> assertTrue(stack.isEmpty())
        );
    }
}