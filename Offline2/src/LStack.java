class Node<T>{
    T element;
    Node<T> next;

    public Node(T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }

    public Node(Node<T> next) {
        this.next = next;
    }
}

public class LStack<T> implements Stack<T> {
    private Node<T> top;
    private int count;              // element count;

    public LStack(){
        top = null;
        count = 0;
    }

    @Override
    public void clear() {
        top = null;
        count = 0;
    }

    @Override
    public void push(T item) {
        top = new Node(item, top);
        count++;
    }

    @Override
    public T pop() {
        if(top == null)
            return null;

        T item = top.element;
        top = top.next;
        count--;

        return item;
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public T topValue() {
        if(top == null)
            return null;

        return top.element;
    }

    @Override
    public void setDirection(int direction) {
        // Not relevant for linked list
    }
}
