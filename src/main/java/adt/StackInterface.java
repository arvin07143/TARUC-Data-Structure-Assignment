package adt;

public interface StackInterface<T> {

    void push(T newEntry);

    T pop();

    T peek();
    
    StackInterface<T> reverse();

    boolean isEmpty();

    void clear();
    
    int getSize();
}
