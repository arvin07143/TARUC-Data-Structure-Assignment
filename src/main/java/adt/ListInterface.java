package adt;

public interface ListInterface<T> {

    public void add(T newObject);

    public void add(T newObject, int index);

    public void set(int index, T changeObject);

    public void remove(int index);

    public void remove(T removedObject);

    public T get(int index);

    public void sortList();
}
