package tdd;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {
    private final List<Integer> stack;

    public MinMaxStackImpl() {
        this.stack = new ArrayList<>();
    }

    @Override
    public void push(int value) {
        this.stack.add(value);
    }

    @Override
    public int pop() {
        return this.stack.remove(this.stack.size() - 1);
    }

    @Override
    public int peek() {
        return this.stack.get(this.stack.size() - 1);
    }

    @Override
    public int getMin() {
        int min = this.stack.get(0);
        for (Integer val : this.stack) {
            if (val < min)
                min = val;
        }
        return min;
    }

    @Override
    public int getMax() {
        int max = this.stack.get(0);
        for (Integer val : this.stack) {
            if (val > max)
                max = val;
        }
        return max;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}
