package adt;

public class ArrayList<T> implements ListInterface<T> {

    private T[] currentArray;
    private int arraySize;

    public ArrayList() {
        arraySize = 0;
        this.currentArray = (T[])new Object[arraySize];
    }

    @Override
    public void add(T newObject) {
        if(isEmpty()) {
            currentArray = (T[])new Object[arraySize+1];
            arraySize++;
        }
        else{
            makeSpace();
        }
        currentArray[arraySize-1] = newObject;
    }

    @Override
    public void add(T newObject, int index) {
        if(index == arraySize) add(newObject);

        else if (index > -1 && index < arraySize){
            makeSpace();

            T[] tempArray = (T[]) new Object[arraySize]; //arraySize already incremented in makeSpace()
            System.arraycopy(currentArray,0,tempArray,0,index); //copy content before index
            System.arraycopy(currentArray,index,tempArray,index+1,arraySize-index-1); //copy content after index
            tempArray[index] = newObject;

            currentArray = tempArray;

        }
    }

    @Override
    public void set(int index, T changeObject) {
        if(index > -1 && index < arraySize){
            currentArray[index] = changeObject;
        }
    }

    @Override
    public void remove(int index) {
        if(index > -1 && index < arraySize){
            currentArray[index] = null;
            reduceSpace(index);
        }
    }

    @Override
    public void remove(T removedObject) {
        for (int i = 0 ; i < arraySize ; i++){
            if (currentArray[i].equals(removedObject)){
                currentArray[i] = null;
                reduceSpace(i);
            }
        }
    }

    public int getArraySize() {
        return arraySize;
    }

    public T[] toArray() {
        return (T[])currentArray;
    }

    @Override
    public T get(int index) {
        return currentArray[index];
    }

    public boolean isEmpty(){
        return arraySize == 0;
    }

    public void makeSpace(){
        T[] tempArray = (T[])new Object[arraySize+1];
        System.arraycopy(currentArray,0,tempArray,0,arraySize);

        currentArray = tempArray;
        arraySize++;
    }

    public void reduceSpace(int startPos){
        T[] tempArray = (T[])new Object[arraySize-1];
        System.arraycopy(currentArray,0,tempArray,0,startPos);
        System.arraycopy(currentArray,startPos + 1,tempArray,startPos,arraySize-1-startPos);
        currentArray = tempArray;

        arraySize --;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < arraySize ; i++){
            str.append(i + 1).append(". ").append(currentArray[i]).append("\n");
        }

        return str.toString();
    }
}
