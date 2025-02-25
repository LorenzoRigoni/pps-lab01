package tdd;

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
    private static final int QUEUE_CAPACITY_AFTER_DEQUEUE = QUEUE_CAPACITY - 1;
    private static final int NEW_ELEMENT_ENQUEUE = 5;
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
        final List<Integer> elementsAdded = new ArrayList<>();
        for (int i = 0; i < QUEUE_CAPACITY; i++) {
            queue.enqueue(i);
            elementsAdded.add(i);
        }
        assertAll(
                () -> assertEquals(elementsAdded.get(0), queue.dequeue()),
                () -> assertEquals(QUEUE_CAPACITY_AFTER_DEQUEUE, queue.getSize())
        );
    }

    @Test
    void testEqueueAfterMaxCapacity() {
        final List<Integer> elementsAdded = new ArrayList<>();
        for (int i = 0; i < QUEUE_CAPACITY; i++) {
            queue.enqueue(i);
            elementsAdded.add(i);
        }
        queue.enqueue(NEW_ELEMENT_ENQUEUE);
        elementsAdded.set(0, NEW_ELEMENT_ENQUEUE);
        assertAll(
                () -> assertEquals(QUEUE_CAPACITY, queue.getSize()),
                () -> assertEquals(elementsAdded, queue.getQueue())
        );
    }

    @Test
    void testDequeueWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }
}
