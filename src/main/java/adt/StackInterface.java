package adt;

public interface StackInterface<T> {

    public void push(T newEntry);

    public T pop();

    public T peek();
    
    public StackInterface<T> reverse();

    public boolean isEmpty();

    public void clear();
    
    public int getSize();
}
