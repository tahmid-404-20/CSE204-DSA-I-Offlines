import java.util.Scanner;

class Dish {
    int courseNo;
    int size;
    int friendId;
    int pushTime;

    public Dish(int courseNo, int size, int friendId, int pushTime) {
        this.courseNo = courseNo;
        this.size = size;
        this.friendId = friendId;
        this.pushTime = pushTime;
    }
}

public class Dishwasher {

    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);

        int nInvitees = scr.nextInt();
        int nCourses = scr.nextInt();

        //Initialization:
        //Type-1 (Linked List Based)
//        Stack<Dish> dirty = new LStack<>();
//        Stack<Dish> clean = new LStack<>();

        //Type-2 (Array Based)
//        Stack<Dish> dirty = new AStack<>(nInvitees*nCourses);
//        Stack<Dish> clean = new AStack<>(nInvitees*nCourses);


        //Type-3 (One Array Two Stack)
        Dish []sharedArray = new Dish[nCourses*nInvitees];
        Stack<Dish> dirty = new AStack<>(sharedArray,1);
        Stack<Dish> clean = new AStack<>(sharedArray,-1);


        int courseSize[] = new int[nCourses];
        int cleanPushingTime[] = new int[nCourses * nInvitees];     // contains the ending time of washing of dishes
        Stack<Integer> fullMealID = new LStack<>();

        for (int i = 0; i < nCourses; i++) {
            courseSize[i] = scr.nextInt();
        }

        int washingEndingTime, popTime = 0;

        int friendID, pushTime, courseNo;

        while (true) {
            friendID = scr.nextInt();
            pushTime = scr.nextInt();
            courseNo = scr.nextInt();

            if(friendID == 0) {
                while(dirty.length() != 0) {
                    Dish d = dirty.pop();
                    washingEndingTime = popTime + d.size-1;
                    clean.push(d);
                    cleanPushingTime[clean.length() - 1] = washingEndingTime;
                    popTime = washingEndingTime + 1;
                    //System.out.println("Popped pushTime: " + d.pushTime + " size: " + d.size + " ending: " + washingEndingTime + " pop: " + popTime);
                }
                break;
            }

            Dish dish = new Dish(courseNo, courseSize[courseNo - 1], friendID, pushTime);

            while(popTime < pushTime) {
                if (dirty.length() != 0) {
                    Dish d = dirty.pop();
                    washingEndingTime = popTime + d.size-1;
                    popTime = washingEndingTime + 1;
                    clean.push(d);
                    //System.out.println("Popped Here pushTime: " + d.pushTime + " size: " + d.size + " ending: " + washingEndingTime+ " pop: " + popTime);
                    cleanPushingTime[clean.length() - 1] = washingEndingTime;
                }
                else {
                    popTime = pushTime;
                }
            }

            if (pushTime == popTime) {
                dirty.push(dish);
                if(dish.courseNo == nCourses)
                {
                    fullMealID.push(dish.friendId);
                }
                //System.out.println("Pushed pushTime: " + dish.pushTime + " size: " + dish.size);
                Dish d = dirty.pop();
                washingEndingTime = popTime + d.size-1;
                clean.push(d);
                cleanPushingTime[clean.length() - 1] = washingEndingTime;
                popTime = washingEndingTime + 1;
                //System.out.println("Popped pushTime: " + d.pushTime + " size: " + d.size + " ending: " + washingEndingTime+ " pop: " + popTime);

            } else {  // pushTime < popTime
                dirty.push(dish);
                if(dish.courseNo == nCourses)
                {
                    fullMealID.push(dish.friendId);
                }
                //System.out.println("Pushed pushTime: " + dish.pushTime + " size: " + dish.size);
            }

        }

        System.out.println(cleanPushingTime[clean.length() - 1]);
        for (int i = 0; i < clean.length(); i++) {
            System.out.print(cleanPushingTime[i]);
            if(i!=clean.length()-1)
                System.out.print(",");
        }
        System.out.println();

        if(fullMealID.length() == nInvitees){
            System.out.println("Y");
        } else {
            System.out.println("N");
        }

        int len = fullMealID.length();
        for(int i=0;i<len;i++)
        {
            System.out.print(fullMealID.pop());
            if(i != len-1)
            {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}
