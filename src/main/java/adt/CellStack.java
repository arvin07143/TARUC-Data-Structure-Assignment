package adt;

/**
 *
 * @author GV62 7RC
 */
public class CellStack<T> implements StackInterface<T>{
    public void push(T newEntry);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public void clear();
}
