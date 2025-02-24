package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private static final int SINGLE_VALUE_TO_PUSH = 1;
    private static final int SINGLE_VALUE_TO_POP = 1;
    private static final int NUM_VALUES_TO_PUSH = 5;
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

    @Test
    void testMin() {
        final int minValue = 1;
        for (int i = 1; i <= NUM_VALUES_TO_PUSH; i++)
            stack.push(i);
        assertEquals(minValue, stack.getMin());
    }

    @Test
    void testMax() {
        final int maxValue = 5;
        for (int i = 1; i <= NUM_VALUES_TO_PUSH; i++)
            stack.push(i);
        assertEquals(maxValue, stack.getMax());
    }

    @Test
    void testActionsWithEmptyStack() {
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> stack.pop()),
                () -> assertThrows(IllegalStateException.class, () -> stack.peek()),
                () -> assertThrows(IllegalStateException.class, () -> stack.getMin()),
                () -> assertThrows(IllegalStateException.class, () -> stack.getMax())
        );
    }
}