package adt;

public interface ListInterface<T> {

    void add(T newObject);

    void add(T newObject, int index);

    void set(int index, T changeObject);

    void remove(int index);

    void remove(T removedObject);

    T get(int index);

    int size();

    void sortList();

    boolean isEmpty();
}
