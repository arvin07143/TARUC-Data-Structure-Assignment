package adt;

public class ArrayList<T> implements ListInterface<T> {

    private T[] currentArray;
    private int arraySize;

    public ArrayList() {
        arraySize = 0;
        this.currentArray = (T[]) new Object[arraySize];
    }

    @Override
    public void add(T newObject) {
        if (isEmpty()) {
            currentArray = (T[]) new Object[arraySize + 1];
            arraySize++;
        } else {
            makeSpace();
        }
        currentArray[arraySize - 1] = newObject;
    }

    @Override
    public void add(T newObject, int index) {
        if (index == arraySize) add(newObject);

        else if (index > -1 && index < arraySize) {
            makeSpace();
            T[] tempArray = (T[]) new Object[arraySize]; //arraySize already incremented in makeSpace()
            for(int i = 0 ; i < index ; i++){
                tempArray[i] = currentArray[i];//copy content before index
            }
            for(int j = index+1 ; j < arraySize ; j++){
                tempArray[j] = currentArray[j-1];
            }
            tempArray[index] = newObject;

            currentArray = tempArray;
        }
    }

    @Override
    public void set(int index, T changeObject) {
        if (index > -1 && index < arraySize) {
            currentArray[index] = changeObject;
        }
    }

    @Override
    public void remove(int index) {
        if (index > -1 && index < arraySize) {
            currentArray[index] = null;
            reduceSpace(index);
        }
    }

    @Override
    public void remove(T removedObject) {
        for (int i = 0; i < arraySize; i++) {
            if (currentArray[i].equals(removedObject)) {
                currentArray[i] = null;
                reduceSpace(i);
            }
        }
    }

    @Override
    public T get(int index) {
        return currentArray[index];
    }

    @Override
    public T get(T getObject) {
        for(int i = 0 ; i < arraySize ; i++){
            if(currentArray[i].equals(getObject)) return (T)currentArray[i];
        }
        return null;
    }

    @Override
    public int search(T searchObject) {
        for(int i = 0 ; i < arraySize ; i++){
            if(currentArray[i].equals(searchObject)) return i;
        }
        return -1;
    }

    @Override
    public void removeDuplicate() {
        T tempObject;
        for(int i = 0 ; i < arraySize ; i++){
            tempObject = currentArray[i];
            int tempIndex = i;
            for(int j = tempIndex+1 ; j < arraySize ; j++){
                if(currentArray[j].equals(tempObject)){
                    remove(j);
                }
            }
        }
    }

    public boolean isEmpty() {
        return arraySize == 0;
    }

    @Override
    public int size() {
        return arraySize;
    }

    private void makeSpace() {
        T[] tempArray = (T[]) new Object[arraySize + 1];
        for(int i = 0 ; i < arraySize ; i++){
            tempArray[i] = currentArray[i];
        }

        currentArray = tempArray;
        arraySize++;
    }

    private void reduceSpace(int startPos) {
        T[] tempArray = (T[]) new Object[arraySize - 1];
        for(int i = 0 ; i < startPos ; i++){
            tempArray[i] = currentArray[i];
        }

        for(int j = startPos+1 ; j < arraySize ; j++){
            tempArray[j-1] = currentArray[j];
        }
        currentArray = tempArray;

        arraySize--;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arraySize; i++) {
            str.append(i + 1).append(". ").append(currentArray[i]).append("\n");
        }

        return str.toString();
    }
}
