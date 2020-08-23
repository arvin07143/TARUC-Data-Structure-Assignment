package adt;

public class ArrayQueue<T> implements QueueInterface<T> {

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

    @Override
    public void enqueue(T newPlayer) {
        if (!isArrayFull()) {
            backIndex = (backIndex + 1) % array.length;
            array[backIndex] = newPlayer;
        }
    }

    @Override
    public T peek(){
        return array[frontIndex];
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % array.length);
    }

    @Override
    public T dequeue() {
        T front = null;

        if (!isEmpty()) {
            front = array[frontIndex];
            array[frontIndex] = null;
            frontIndex = (frontIndex + 1) % array.length;
        }
        return front;
    }

    @Override
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
    
    @Override
    public int getSize(){
        return (backIndex + 1);
    }
    
    
    
    public String toString (){
        StringBuilder str = new StringBuilder();
        for (int i = 0 ; i < getSize(); i++){
           str.append(i ).append(". ").append(array[i]).append("\n");
        }
        return str.toString();
    }

}
