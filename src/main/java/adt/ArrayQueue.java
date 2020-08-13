package adt;

public abstract class ArrayQueue<T> implements QueueInterface<T> {

    private T[] array;
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 50;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int initialCapacity) {
        array = (T[]) new Object[initialCapacity + 1];
        frontIndex = 0;
        backIndex = initialCapacity;
    }

    private boolean isArrayFull() {
        return frontIndex == ((backIndex + 2) % array.length);
    }

    public void enqueue(T newEntry) {
        if (!isArrayFull()) {
            backIndex = (backIndex + 1) % array.length;
            array[backIndex] = newEntry;
        }
    }

    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % array.length);
    }

    public T getFront() {
        T front = null;

        if (!isEmpty()) {
            front = array[frontIndex];
        }
        return front;
    }

    public T dequeue() {
        T front = null;

        if (!isEmpty()) {
            front = array[frontIndex];
            array[frontIndex] = null;
            frontIndex = (frontIndex + 1) % array.length;
        }
        return front;
    }

    public void clear() {
        if (!isEmpty()) {
            for (int i = frontIndex; i != backIndex; i = (i + 1) % array.length) {
                array[i] = null;
            }
            array[backIndex] = null;
        }

        frontIndex = 0;
        backIndex = array.length - 1;
    }

}
