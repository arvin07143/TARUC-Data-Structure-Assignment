package adt;

public class ArrayStack<T> implements StackInterface<T> {
    private T[] array;
    private int topIndex; // index of top entry
    private static final int DEFAULT_CAPACITY = 100;
    
    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayStack(int initialCapacity){
        array = (T[]) new Object [initialCapacity];
        topIndex = -1;
    }
    
    public void push(T newMovement){
        if(!isEmpty()){
            topIndex++;
            array[topIndex] = newMovement;
        }
        else array[0] = newMovement; //record all the movement of hedgehogs
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
            topNum = array[topIndex]; 
        }
        
        return topNum; // used to show the last movement of the player
    }
    
    public boolean isEmpty(){
        return topIndex < 0; // we assume topIndex as negative value when array is empty
    }
    
    public void clear(){
        topIndex = -1; //assign to negative value and clear it.
    }
    
    /*public void showMovement(T newMovement){
        push(newMovement);
        peek(); // after push in then show the movement
    }
    
    public void undo(){
        pop(); // pop then reverse the operation
        
    }*/
}

