public class StackOnline<T>{
    Stack<T> mainStack;
    Stack<T> temp;

    public StackOnline() {
        mainStack = new LStack<>();
        temp = new LStack<>();

//        mainStack = new AStack<>();
//        temp = new AStack<>();
    }

    public void clear() {
        mainStack.clear();
        temp.clear();
    }
    public void push(T item){
        mainStack.push(item);
    }
    public T pop() {
        return mainStack.pop();
    }
    public int length() {
        return mainStack.length();
    }
    public T topValue() {
        return mainStack.topValue();
    }
    public void setDirection(int direction){
        mainStack.setDirection(direction);
    }

    public void insert(T item, int offset) {
        if(offset < mainStack.length() && offset >= 0) {
            for(int i=0;i<=offset;i++)
            {
                temp.push(mainStack.pop());
            }

            mainStack.push(item);

            while(temp.length()!=0)
            {
                mainStack.push(temp.pop());
            }
        }
    }

    public T remove(int offset) {
        if(offset <= mainStack.length() && offset >= 0){
            for(int i=0;i<offset;i++) {

                temp.push(mainStack.pop());
            }
            T item = mainStack.pop();

            while(temp.length()!=0)
            {
                mainStack.push(temp.pop());
            }
            return item;
        }
        return null;
    }
}
