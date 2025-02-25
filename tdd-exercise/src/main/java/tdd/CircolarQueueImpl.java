package tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CircolarQueueImpl implements CircularQueue {
    private final List<Integer> queue;
    private final int capacity;
    private int numElementsEntered;

    public CircolarQueueImpl(final int capacity) {
        this.queue = new ArrayList<>();
        this.capacity = capacity;
        this.numElementsEntered = 0;
    }

    @Override
    public void enqueue(int value) {
        if (this.numElementsEntered < this.capacity)
            this.queue.add(value);
        else
            this.queue.set(this.numElementsEntered % this.capacity, value);
        this.numElementsEntered++;
    }

    @Override
    public int dequeue() {
        if (!isEmpty())
            return this.queue.remove(0);
        throw new IllegalStateException("The queue is empty");
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public List<Integer> getQueue() {
        return Collections.unmodifiableList(this.queue);
    }

    @Override
    public int getSize() {
        return this.queue.size();
    }
}
