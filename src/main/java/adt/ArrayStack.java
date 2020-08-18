package adt;
import entity.Player;
import entity.Hedgehog;
public class ArrayStack<T> implements StackInterface<T> {

    private T[] array;
    private int topIndex; // index of top entry
    private static final int DEFAULT_CAPACITY = 100;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
        topIndex = -1;
    }

    public void push(T newEntry) {
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

    @Override
    public T find(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isEmpty() {
        return topIndex < 0; // we assume topIndex as negative value when array is empty
    }

    public void clear() {
        topIndex = -1; //assign to negative value and clear it.
    }    
    public void undoX(Player playerID, int i){
        pop();
        Hedgehog[] x ;
        x = playerID.getHedgehogs(); 
        x[i].setRow((int)array[topIndex]);       
    }
    
    public void undoY(Player playerID, int i){
        pop();
        Hedgehog[] y ;
        y = playerID.getHedgehogs(); 
        y[i].setRow((int)array[topIndex]);       
    }

    public int getSize(){
        return topIndex;

    }

}
