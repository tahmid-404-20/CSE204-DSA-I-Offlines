public class AQueue<T> implements Queue<T> {

    private static final int DEFAULT_ARRAY_SIZE = 100;
    int front;
    int rear;
    int arraySize;
    T [] array;

    private void init(int initialSize) {
        front = 1;
        rear = 0;
        arraySize = initialSize + 1;
        array = (T[]) new Object[arraySize];
    }

    private void increaseSize() {
        T[] temp = (T[]) new Object[2*arraySize];

        int i,j;

        for(j=1, i = front; i!=rear;i=(i+1)%arraySize,j++)
        {
            temp[j] = array[i];
        }
        temp[j] = array[rear];      // insert the last value

        rear = j;
        front = 1;
        array = temp;
        arraySize *=2;
    }

    private boolean isEmpty() {
        return length() == 0;
    }

    public AQueue(int initialSize) {
        init(initialSize);
    }

    public AQueue() {
        init(DEFAULT_ARRAY_SIZE);
    }

    public AQueue(T[] a) {
        array = a;
        front = 1;
        rear = 0;
        arraySize = a.length;
    }

    @Override
    public void clear() {
        rear = 0;
        front = 1;
    }

    @Override
    public void enqueue(T item) {
        if((rear+2)%arraySize == front) {
            increaseSize();
        }

        rear = (rear+1)%arraySize;
        array[rear] = item;
    }

    @Override
    public T dequeue() {
        if(isEmpty())
            return null;

        T item = array[front];
        front = (front+1)%arraySize;
        return item;
    }

    @Override
    public int length() {
        return ((rear+arraySize)-front+1)%arraySize;
    }

    @Override
    public T frontValue() {
        if(isEmpty())
            return null;

        return array[front];
    }

    @Override
    public T rearValue() {
        if(isEmpty())
            return null;

        return array[rear];
    }

    @Override
    public T leaveQueue() {
        if(isEmpty())
            return null;

        T item = array[rear];
        rear = (rear+arraySize-1)%arraySize;                // circular array

        return item;
    }
}
