package adt;

public interface QueueInterface<T> {

    public void enqueue(T newEntry);

    public T dequeue();

    public T peek();

    public void clear();

    public boolean isEmpty();
}
