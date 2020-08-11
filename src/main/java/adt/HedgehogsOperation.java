package adt;

public class HedgehogsOperation<T> implements StackInterface<T> {
    private T[] array;
    private int topIndex; // index of top entry
    private static final int DEFAULT_CAPACITY = 40;
    
    public HedgehogsOperation(){
        this(DEFAULT_CAPACITY);
    }
    
    public HedgehogsOperation(int initialCapacity){
        array = (T[]) new Object [initialCapacity];
        topIndex = -1;
    }
    
    public void push(T newEntry){
        if(!isEmpty()){
            topIndex++;
            array[topIndex] = newEntry;
        }
        else array[0] = newEntry; //record all the movement of hedgehogs
    }
    
    public T pop(){
        T popNum = null;
        
        if(!isEmpty()){
            popNum = array[topIndex];
            array[topIndex] = null;
            topIndex--;
        }
        
        return popNum; // return the last movement and remove it(undo)
    }
    
    public T peek(){
        T topNum = null;
        
        if (!isEmpty()){
            topNum = array[topIndex]; // assign the 
        }
        
        return topNum; // used to show the last movement of the player
    }
    
    public boolean isEmpty(){
        return topIndex < 0; // we assume topIndex as negative value when array is empty
    }
    
    public void clear(){
        topIndex = -1; //assign to negative value and clear it.
    }
    
}

