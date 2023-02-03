public class AStack<T> implements Stack<T> {

    private static final int DEFAULT_ARRAY_SIZE = 1000;
    private T[] array;
    int top;
    int arraysize;
    int direction;

    private void increaseSize() {
        T[] temp = (T[]) new Object[2 * arraysize];

        if (direction == 1) {
            for (int i = 0; i < top; i++) {
                temp[i] = array[i];
            }
        } else {
            int j = arraysize - 1;
            for (int i = 2 * arraysize - 1; i >= arraysize; i--, j--) {
                temp[i] = array[j];
            }
            top = arraysize - 1;
        }

        array = temp;
        arraysize *= 2;
    }

    private void init(int arraysize, int direction) {
        this.arraysize = arraysize;
        array = (T[]) new Object[arraysize];
        if(direction == 1) { top = 0; }
        else {top = this.arraysize-1; }
        this.direction = direction;
    }

    AStack() {
        init(DEFAULT_ARRAY_SIZE,1);
    }

    AStack(int chunksize) {
        init(chunksize,1);
    }

    AStack(T[] a, int direction) {
        this.array = a;
        this.direction = direction;
        this.arraysize = a.length;
        if(direction == 1) { top = 0; }
        else { top = this.arraysize-1; }
    }

    @Override
    public void clear() {
        init(this.arraysize,direction);
    }

    @Override
    public void push(T item) {
        if (direction == 1) {
            if (top == arraysize) {
                increaseSize();
            }
            array[top++] = item;
        } else {
            if (top == -1) {
                increaseSize();
            }
            array[top--] = item;
        }
    }

    @Override
    public T pop() {
        if (direction == 1) {
            if (top == 0)
                return null;

            return array[--top];

        } else {
            if (top == arraysize - 1)
                return null;

            return array[++top];
        }
    }

    @Override
    public int length() {
        if(direction == 1)
            return top;
        else
            return arraysize-top-1;
    }

    @Override
    public T topValue() {
        if(direction == 1)
        {
            if(top == 0)
                return null;
            else
                return array[top-1];
        } else {
            if(top == arraysize-1)
                return null;
            else
                return array[top+1];
        }
    }

    @Override
    public void setDirection(int direction) {
        if (this.direction == 1) {
            //check if empty list, if direction need not be altered, no change
            if (top == 0 && direction == -1) {
                top = arraysize - 1;
                this.direction = direction;
            }
        } else {
            if (top == (arraysize - 1) && direction == 1) {
                top = 0;
                this.direction = direction;
            }
        }
    }
}

