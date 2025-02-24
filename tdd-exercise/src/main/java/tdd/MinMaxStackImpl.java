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
        if (!isEmpty())
            return this.stack.remove(this.stack.size() - 1);
        throw new IllegalStateException("The stack is empty");
    }

    @Override
    public int peek() {
        if (!isEmpty())
            return this.stack.get(this.stack.size() - 1);
        throw new IllegalStateException("The stack is empty");
    }

    @Override
    public int getMin() {
        if (!isEmpty()) {
            return calcMinOrMax(true);
        }
        throw new IllegalStateException("The stack is empty");
    }

    @Override
    public int getMax() {
        if (!isEmpty()) {
            return calcMinOrMax(false);
        }
        throw new IllegalStateException("The stack is empty");
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    private int calcMinOrMax(final boolean isMinToCalc) {
        int minOrMax = this.stack.get(0);
        for (final Integer val : this.stack) {
            if (isMinToCalc) {
                if (val < minOrMax)
                    minOrMax = val;
            } else
                if (val > minOrMax)
                    minOrMax = val;
        }
        return minOrMax;
    }
}
