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

public class LQueue<T> implements Queue<T> {

    private Node<T> front;
    private Node<T> rear;
    private int count;

    private void init()
    {
        front = rear = new Node<T>(null);
        count = 0;
    }
    private boolean isEmpty() {
        return front == rear;
    }

    public LQueue(){
        init();
    }

    @Override
    public void clear() {
        init();
    }

    @Override
    public void enqueue(T item) {
        rear = rear.next = new Node<T>(item,null);
        count++;
    }

    @Override
    public T dequeue() {
        if(isEmpty()){
            return null;
        }

        T item = front.next.element;
        if(front.next == rear)
            rear = front;

        front.next = front.next.next;
        count--;

        return item;
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public T frontValue() {
        if(isEmpty())
            return null;

        return front.next.element;
    }

    @Override
    public T rearValue() {
        if(isEmpty())
            return null;

        return rear.element;
    }

    @Override
    public T leaveQueue() {
        if(isEmpty())
            return null;

        Node<T> temp = front;

        while(temp.next != rear){
            temp = temp.next;
        }

        T item = rear.element;

        rear = temp;
        temp.next = null;
        count--;

        return item;

    }
}
