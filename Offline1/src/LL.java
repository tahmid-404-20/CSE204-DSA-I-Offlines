class Node<E> {
    E element;
    Node<E> next;

    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }

    public Node(Node<E> next) {
        this.next = next;
    }
}

public class LL<T> implements AL<T>{

    private Node<T> head;
    private Node<T> tail;
    private Node<T> curr;
    private int count;

    private void init()
    {
        head = tail = curr = new Node<T>(null);
    }

    public LL()
    {
        init();
        count = 0;
    }

    public LL(T[] a)
    {
        init();
        for(int i=0;i<a.length;i++)
        {
            append(a[i]);
        }
        count = a.length;
    }

    @Override
    public void clear() {
        init();
        count = 0;
    }

    @Override
    public void insert(T item) {
        curr.next = new Node<T>(item,curr.next);
        if(tail == head)        // inserting in empty list
            tail = head.next;

        count++;
    }

    @Override
    public void append(T item) {
        tail = tail.next = new Node<T>(item, null);
        count++;
    }

    @Override
    public T remove() {
        if(count == 0)
            return null;

        if(curr.next == tail)       // tail updated as last element will be removed
        {
            tail = curr;
        }

        T item = curr.next.element;
        curr.next = curr.next.next;
        count--;

        if(curr == tail)            // Brings back curr one step when tail element was removed
        {
            if(head == tail)        // The list has become empty, so head == tail == curr
            {
                return item;
            }

            Node<T> temp = head;
            while(temp.next != curr)
            {
                temp = temp.next;
            }
            curr = temp;
        }
        return item;
    }

    @Override
    public void moveToStart() {
        curr = head;
    }

    @Override
    public void moveToEnd() {
        if(head == tail) return;  // the list is empty

        Node<T> temp = head;
        while(temp.next!=tail)    // move to the previous node of tail, that's the curr
        {
            temp = temp.next;
        }
        curr = temp;
    }

    @Override
    public void prev() {
        if(head == curr)
            return;

        Node<T> temp = head;
        while(temp.next != curr) temp = temp.next;
        curr = temp;
    }

    @Override
    public void next() {
        if(curr.next != tail)
            curr = curr.next;
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public int currPos() {
        Node<T> temp = head;
        int i;
        for(i=0;temp!=curr;i++)
        {
            temp = temp.next;
        }
        return i;
    }

    @Override
    public void moveToPos(int pos) {

        if(pos>=0 && pos<count) {
            curr = head;
            for (int i = 0; i < pos; i++) {
                curr = curr.next;
            }
        }
    }

    @Override
    public T getValue() {
        if(count == 0) return null;

        return curr.next.element;
    }

    @Override
    public int Search(T item) {
        Node<T> temp = head;

        for(int i=0; i<count ;i++)
        {
            temp = temp.next;
            if(item.equals(temp.element))
            {
                return i;
            }
        }
        return -1;
    }
}
