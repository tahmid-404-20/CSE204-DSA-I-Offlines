import java.util.Scanner;

public class OnlineTester {
    static void printStack(StackOnline<Integer> stack) {
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
        StackOnline<Integer> stack = new StackOnline<>();

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
                case 7:
                    stack.insert(p,scr.nextInt());
                    break;
                case 8:
                    stack.remove(p);
                    break;
                default:
                    System.out.println("The first input must be in range 0-8");
            }

            printStack(stack);
            System.out.println(ret_value);
        }
    }
}
