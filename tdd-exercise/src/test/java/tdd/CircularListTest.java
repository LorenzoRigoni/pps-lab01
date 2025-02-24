package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private static final int QUEUE_CAPACITY = 5;
    private static final int QUEUE_CAPACITY_AFTER_DEQUEUE = 4;
    private static final int FIRST_ELEMENT_OF_QUEUE = 0;
    private CircularQueue queue;

    @BeforeEach
    void beforeEach() {
        this.queue = new CircolarQueueImpl(QUEUE_CAPACITY);
    }

    @Test
    void testEnqueue() {
        final List<Integer> elementsAdded = new ArrayList<>();
        for (int i = 0; i < QUEUE_CAPACITY; i++) {
            queue.enqueue(i);
            elementsAdded.add(i);
        }
        assertAll(
                () -> assertFalse(queue.isEmpty()),
                () -> assertEquals(QUEUE_CAPACITY, queue.getSize()),
                () -> assertEquals(elementsAdded, queue.getQueue())
        );
    }

    @Test
    void testDequeue() {
        for (int i = 0; i < QUEUE_CAPACITY; i++) {
            queue.enqueue(i);
        }
        assertAll(
                () -> assertEquals(FIRST_ELEMENT_OF_QUEUE, queue.dequeue()),
                () -> assertEquals(QUEUE_CAPACITY_AFTER_DEQUEUE, queue.getSize())
        );
    }
}
