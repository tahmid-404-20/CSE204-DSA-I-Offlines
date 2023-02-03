public class Arr<T> implements AL<T> {

    private static final int DEFAULT_CHUNK_SIZE = 100;
    private int arraySize;
    private int chunkSize;
    private int count;      //# elements present
    private int curr;
    private T[] array;

    private void init(int chunkSize) {
        this.arraySize = this.chunkSize = chunkSize;
        curr = 0;
        array = (T[]) new Object[this.chunkSize];
    }

    private void increaseSize() {
        T[] temp = (T[]) new Object[arraySize + chunkSize];
        arraySize += chunkSize;
        for (int i = 0; i < count; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public Arr(int chunkSize) {
        init(chunkSize);
        count = 0;
    }

    public Arr() {                      // default chunkSize constructor
        init(DEFAULT_CHUNK_SIZE);
        count = 0;
    }

    public Arr(T[] a, int chunkSize)
    {
        init(chunkSize);
        for(int i=0;i<a.length;i++)
        {
            array[i] = a[i];
        }
        count = a.length;
    }

    public Arr(T[] a)                   // default chunkSize = array length
    {
        init(a.length);
        for(int i=0;i<a.length;i++)
        {
            array[i] = a[i];
        }
        count = a.length;
    }

    @Override
    public void clear() {
        curr = count = 0;
    }

    @Override
    public void insert(T item) {
        if (count + 1 > arraySize) {
            increaseSize();
        }

        for (int i = count; i > curr; i--) {
            array[i] = array[i - 1];
        }
        array[curr] = item;
        count++;
    }

    @Override
    public void append(T item) {
        if (count + 1 > arraySize) {
            increaseSize();
        }
        array[count++] = item;
    }

    @Override
    public T remove() {
        if(count == 0)
            return null;

        T item = array[curr];

        for (int i = curr; i < count - 1; i++)
            array[i] = array[i + 1];

        if(curr == count-1 && count > 1)         // curr is at the end of the array
            curr--;                              // second condition is to keep curr over zero

        count--;

        return item;
    }

    @Override
    public void moveToStart() {
        curr = 0;
    }

    @Override
    public void moveToEnd() {
        curr = count-1;
    }

    @Override
    public void prev() {
        if (curr != 0) curr--;
    }

    @Override
    public void next() {
        if (curr < count-1)
            curr++;
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public int currPos() {
        return curr;
    }

    @Override
    public void moveToPos(int pos) {
        if (pos >= 0 && pos < count) {
            curr = pos;
        }
    }

    @Override
    public T getValue() {
        if(count == 0) return null;

        return array[curr];
    }

    @Override
    public int Search(T item) {

        for (int i = 0; i < count; i++) {
            if (item.equals(array[i]))
                return i;
        }
        return -1;
    }
}
