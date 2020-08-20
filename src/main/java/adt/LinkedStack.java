package adt;
import entity.*;

/**
 *
 * @author GV62 7RC
 */
public class LinkedStack<T> implements StackInterface<T> {

    //Properties
    private Node topNode;
    private int size;

    //Constructor
    public LinkedStack() {
        this.topNode = null;
        this.size = 0;
    }

    //Setter and getter
    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    //Methods
    @Override
    public void push(T newEntry) {
        Node oldTop = topNode;
        topNode = new Node(newEntry, oldTop);
        size++;
    }

    @Override
    public T pop() {
        T top = null;
        if (!isEmpty()) {
            top = topNode.data;
            topNode = topNode.next;
            size--;
        }
        return top;
    }

    @Override
    public T peek() {
        T top = null;
        if (!isEmpty()) {
            top = topNode.data;
        }
        return top;
    }
    
    public StackInterface<T> reverse(){
        StackInterface<T> tempStack = new LinkedStack<T>();
        StackInterface<T> reversedStack = new LinkedStack<T>();
        if (!isEmpty()){
            Node currentNode = topNode;
            for (int i = 0; i < size; i++){
                tempStack.push(currentNode.data);    
                currentNode = currentNode.next;
            }
            while (tempStack.getSize() != 0){
                reversedStack.push(tempStack.pop());
            }
        }
        else 
            return null;
        return reversedStack;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null && size == 0;
    }

    @Override
    public void clear() {
        topNode = null;
        size = 0;
    }
    
    //toString
    public String toString() {
        String outputStr = "";
        Node currentNode = topNode;
        for (int i = 0; i < size; i++) {
            /*
            if (currentNode.data.getClass().equals(Player.class)){
                outputStr += "Player " + (i+1) + "\n";
            }
            */
            outputStr += currentNode.data + "\n";;
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    //Private node class
    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
