import java.util.Scanner;

public class TestClass {

    static void printList(AL<Integer> list) {
        int count = list.length();
        int curr = list.currPos();

        System.out.print("<");

        list.moveToStart();
        for (int i = 0; i < count; i++) {
            if (i == curr) {
                System.out.print("| ");
            }

            System.out.print(list.getValue() + " ");
            list.next();
        }
        list.moveToPos(curr);
        System.out.println(">");
    }

    public static void main(String[] args) {
        int length, chunkSize;

        Scanner scr = new Scanner(System.in);

        length = scr.nextInt();
        chunkSize = scr.nextInt();

        //Initialization type-a [Empty list]
        AL<Integer> list = new Arr<>(chunkSize);
        //AL<Integer> list = new LL<>();

        for (int i = 0; i < length; i++) {
            int value = scr.nextInt();
            list.append(value);
        }

        //Initialization type-b [Initialize with an Array]
        /*Integer []a = new Integer[length];
        for(int i=0;i<length;i++)
        {
            a[i] = scr.nextInt();
        }

        //AL<Integer> list = new Arr<>(a,chunkSize);
        AL<Integer> list = new LL<>(a);*/

        printList(list);

        int q, p;

        while (true) {
            q = scr.nextInt();
            p = scr.nextInt();

            if (q == 0) break;

            Integer ret_value = -1;
            switch (q) {
                case 1:
                    list.clear();
                    break;
                case 2:
                    list.insert(p);
                    break;
                case 3:
                    list.append(p);
                    break;
                case 4:
                    ret_value = list.remove();
                    break;
                case 5:
                    list.moveToStart();
                    break;
                case 6:
                    list.moveToEnd();
                    break;
                case 7:
                    list.prev();
                    break;
                case 8:
                    list.next();
                    break;
                case 9:
                    ret_value = list.length();
                    break;
                case 10:
                    ret_value = list.currPos();
                    break;
                case 11:
                    list.moveToPos(p);
                    break;
                case 12:
                    ret_value = list.getValue();
                    break;
                case 13:
                    ret_value = list.Search(p);
                    break;

                default:
                    System.out.println("The first input must be in range 0-13");
            }

            printList(list);
            System.out.println(ret_value);
        }
    }
}
