import java.util.Scanner;

public class TestClass {

    static void printStack(Stack<Integer> stack) {
        Stack<Integer> temp = new LStack<>();

        System.out.print("<");
        while (stack.length() != 0) {
            temp.push(stack.pop());
        }

        while (temp.length() != 0) {
            Integer item = temp.pop();
            System.out.print(item + " ");
            stack.push(item);
        }

        System.out.println(">");
    }

    public static void main(String[] args) {
        int length;

        Scanner scr = new Scanner(System.in);

        length = scr.nextInt();

        //Initialization type-a [Empty list]
        Stack<Integer> stack = new AStack<>();
        //Stack<Integer> stack = new LStack<>();

        for (int i = 0; i < length; i++) {
            int value = scr.nextInt();
            stack.push(value);
        }

        printStack(stack);

        int q, p;

        while (true) {
            q = scr.nextInt();
            p = scr.nextInt();

            if (q == 0) break;

            Integer ret_value = -1;
            switch (q) {
                case 1:
                    stack.clear();
                    break;
                case 2:
                    stack.push(p);
                    break;
                case 3:
                    ret_value = stack.pop();
                    break;
                case 4:
                    ret_value = stack.length();
                    break;
                case 5:
                    ret_value = stack.topValue();
                    break;
                case 6:
                    stack.setDirection(p);
                    break;
                default:
                    System.out.println("The first input must be in range 0-6");
            }

            printStack(stack);
            System.out.println(ret_value);
        }
    }
}
