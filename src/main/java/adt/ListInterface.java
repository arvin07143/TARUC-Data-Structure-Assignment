package adt;

public interface ListInterface<T> {

    void add(T newObject);

    void add(T newObject, int index);

    void set(int index, T changeObject);

    void remove(int index);

    void remove(T removedObject);

    T get(int index);

    T get(T getObject);

    int search(T searchObject);

    void removeDuplicate();

    boolean isEmpty();

    int size();


}
