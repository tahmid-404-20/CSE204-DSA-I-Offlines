import java.util.Scanner;

public class TestClass {

    static void printQueue(Queue<Integer> queue) {
        Queue<Integer> temp = new LQueue<>();

        System.out.print("<");
        while (queue.length() != 0) {
            Integer value = queue.dequeue();
            temp.enqueue(value);
            System.out.print(value + " ");
        }
        System.out.println(">");

        while (temp.length() != 0) {
            queue.enqueue(temp.dequeue());
        }
    }

    public static void main(String[] args) {
        int length;

        Scanner scr = new Scanner(System.in);

        length = scr.nextInt();

        //Initialization type-a [Empty list]
        //Queue<Integer> queue = new LQueue<>();
        Queue<Integer> queue = new AQueue<>();

        for (int i = 0; i < length; i++) {
            int value = scr.nextInt();
            queue.enqueue(value);
        }

        printQueue(queue);

        int q, p;

        while (true) {
            q = scr.nextInt();
            p = scr.nextInt();

            if (q == 0) break;

            Integer ret_value = -1;
            switch (q) {
                case 1:
                    queue.clear();
                    break;
                case 2:
                    queue.enqueue(p);
                    break;
                case 3:
                    ret_value = queue.dequeue();
                    break;
                case 4:
                    ret_value = queue.length();
                    break;
                case 5:
                    ret_value = queue.frontValue();
                    break;
                case 6:
                    ret_value = queue.rearValue();
                    break;
                case 7:
                    ret_value = queue.leaveQueue();
                    break;
                default:
                    System.out.println("The first input must be in range 0-7");
            }

            printQueue(queue);
            System.out.println(ret_value);
        }
    }
}