package adt;

public interface QueueInterface<T> {

    void enqueue(T newEntry);

    T dequeue();

    T peek();

    void clear();

    boolean isEmpty();

    int getSize();
}
