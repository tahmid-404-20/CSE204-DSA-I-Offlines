class QNode<T>{
    T element;
    QNode<T> next;

    public QNode(T element, QNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public QNode(QNode<T> next) {
        this.next = next;
    }
}

public class Queue<T>{

    private QNode<T> front;
    private QNode<T> rear;
    private int count;

    private void init()
    {
        front = rear = new QNode<T>(null);
        count = 0;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public Queue(){
        init();
    }

    public void enqueue(T item) {
        rear = rear.next = new QNode<T>(item,null);
        count++;
    }

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
}
