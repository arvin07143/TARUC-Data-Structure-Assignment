package adt;

public interface StackInterface<T> {

    public void push(T newEntry);

    public T pop();

    public T peek();
    
    public T find(int index);

    public boolean isEmpty();

    public void clear();
    
    public int getSize();
}
