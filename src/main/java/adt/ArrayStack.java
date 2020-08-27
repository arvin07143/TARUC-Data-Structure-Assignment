package adt;

public class ArrayStack<T> implements StackInterface<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int topIndex; // index of top entry

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
        topIndex = -1;
    }

    public void push(T newEntry) {
        if (isFull())
            doubleArray();
        if (!isEmpty()) {
            topIndex++;
            array[topIndex] = newEntry;
        } else {
            topIndex = 0;
            array[topIndex] = newEntry; //record all the movement of hedgehogs
        }
    }

    public T pop() {
        T popNum = null;

        if (!isEmpty()) {
            popNum = array[topIndex];
            array[topIndex] = null;
            topIndex--;
        }

        return popNum; // return the last movement and remove it(undo)
    }

    public T peek() {
        T topNum = null;

        if (!isEmpty()) {
            topNum = array[topIndex]; // assign the 
        }

        return topNum; // used to show the last movement of the player
    }

    public boolean isEmpty() {
        return topIndex < 0; // we assume topIndex as negative value when array is empty
    }

    public void clear() {
        topIndex = -1; //assign to negative value and clear it.
    }

    public int getSize() {
        return topIndex + 1;
    }

    @Override
    public StackInterface<T> reverse() {
        StackInterface<T> reverseStack = new ArrayStack<>();
        if (topIndex >= 0) {
            for (int i = topIndex + 1; i >= 0; i--) {
                reverseStack.push(array[i]);
            }
        } else {
            return null;
        }
        return reverseStack;
    }
    
    public boolean isFull(){
        return topIndex == (array.length-1);
    }
    
    public T[] doubleArray(){
        T[] oldList = array;
        int oldSize = oldList.length;

        array = (T[]) new Object[2 * oldSize];

        for (int index = 0; index < oldSize; index++) {
            array[index] = oldList[index];
        }
        
        return array;
    }

}
