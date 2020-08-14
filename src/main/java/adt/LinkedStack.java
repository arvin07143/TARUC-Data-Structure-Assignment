package adt;

/**
 *
 * @author GV62 7RC
 */
public class LinkedStack<T> implements StackInterface<T> {

    //Properties
    private Node topNode;
    private int size;

    //Constructors
    public LinkedStack() {
        this.topNode = null;
        this.size = 0;
    }

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

    @Override
    public boolean isEmpty() {
        return topNode == null && size == 0;
    }

    @Override
    public void clear() {
        topNode = null;
        size = 0;
    }

    public String toString() {
        String outputStr = "";
        Node currentNode = topNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";;
            currentNode = currentNode.next;
        }
        return outputStr;
    }

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
