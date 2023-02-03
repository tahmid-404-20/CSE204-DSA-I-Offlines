import java.util.Scanner;

class Booth{
    int nextServiceTime;
    boolean isServicing;
    Customer teller;
    Queue<Customer> queue;

    private void init() {
        nextServiceTime = 0;
        teller = null;
        isServicing = false;
    }

    Booth() {
        init();
        queue = new LQueue<>();
    }

    Booth(int nCustomers) {
        init();
        queue = new AQueue<Customer>(nCustomers+1);
    }
}

class Customer{
    int customerNo;
    int enterTime;
    int service;

    public Customer(int customerNo, int enterTime, int service) {
        this.customerNo = customerNo;
        this.enterTime = enterTime;
        this.service = service;
    }
}

public class Bank {

    static void printCustomersInQueue(Queue<Customer> customerQueue) {
        Queue<Customer> temp = new LQueue<>();

        while (customerQueue.length() != 0) {
            Customer customer = customerQueue.dequeue();
            temp.enqueue(customer);
            System.out.print("C"+customer.customerNo + ",");
        }

        while (temp.length() != 0) {
            customerQueue.enqueue(temp.dequeue());
        }
    }
    static void simulateDequeue(Booth booth1,Booth booth2, int unitTime) {
        if(booth1.nextServiceTime == unitTime)
        {
            if(booth1.queue.length() == 0) {
                booth1.teller = null;
                booth1.isServicing = false;
            } else {
                booth1.teller = booth1.queue.dequeue();
                booth1.nextServiceTime  = unitTime + booth1.teller.service;
                booth1.isServicing = true;
            }
        }

        if(booth2.nextServiceTime == unitTime)
        {
            if(booth2.queue.length() == 0) {
                booth2.teller = null;
                booth2.isServicing = false;
            } else {
                booth2.teller = booth2.queue.dequeue();
                booth2.nextServiceTime  = unitTime + booth2.teller.service;
                booth2.isServicing = true;
            }
        }
    }
    static void simulateQueueSwitching(Booth booth1,Booth booth2, int unitTime) {
        if(!booth1.isServicing && booth2.queue.length()>0)
        {
            booth1.teller = booth2.queue.leaveQueue();
            booth1.nextServiceTime = unitTime + booth1.teller.service;
            booth1.isServicing = true;
        }

        if(!booth2.isServicing && booth1.queue.length()>0)
        {
            booth2.teller = booth1.queue.leaveQueue();
            booth2.nextServiceTime = unitTime + booth2.teller.service;
            booth2.isServicing = true;
        }

        if((booth1.queue.length()-1)>booth2.queue.length()) {
            booth2.queue.enqueue(booth1.queue.leaveQueue());
        }

        if((booth2.queue.length()-1)>booth1.queue.length()) {
            booth1.queue.enqueue(booth2.queue.leaveQueue());
        }
    }

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);

        int nCustomers = scr.nextInt();

        //Linked List Based
        Booth booth1 = new Booth();
        Booth booth2 = new Booth();

        //Array Based
//        Booth booth1 = new Booth(nCustomers);
//        Booth booth2 = new Booth(nCustomers);

        int t,s;
        int unitTime = 0;


        //Printing related
        //System.out.println("t\tT1\t\tT2\t\tQ1\t\tQ2");

        for(int i=1;i<=nCustomers;i++)
        {
            t = scr.nextInt();
            s = scr.nextInt();

            Customer customer = new Customer(i,t,s);

            if(t-unitTime == -1)
                unitTime--;

            while(unitTime<=t) {  //priority -> dequeue, newCustomer, queue switching

                //Dequeue process
                simulateDequeue(booth1,booth2,unitTime);

                //Customer Intake
                if(unitTime == t) {
                    boolean customerIntake = false;

                    // Go straight to service if possible
                    if(!booth1.isServicing) {
                        booth1.teller = customer;
                        booth1.nextServiceTime = unitTime + booth1.teller.service;
                        booth1.isServicing = true;
                        customerIntake = true;
                    } else if(!booth2.isServicing) {
                        booth2.teller = customer;
                        booth2.nextServiceTime = unitTime + booth2.teller.service;
                        booth2.isServicing = true;
                        customerIntake = true;
                    }

                    //Join the queue
                    if(!customerIntake) {
                        if(booth1.queue.length() <= booth2.queue.length()) {
                            booth1.queue.enqueue(customer);
                        } else {
                            booth2.queue.enqueue(customer);
                        }
                    }
                }

                //Queue Switching
                simulateQueueSwitching(booth1,booth2,unitTime);

                //Printing the simulation
                /*System.out.print(unitTime+"\t");
                if(booth1.teller != null)
                    System.out.print("C"+booth1.teller.customerNo+"\t");
                else
                    System.out.print("--");
                System.out.print("\t");
                if(booth2.teller != null)
                    System.out.print("C"+booth2.teller.customerNo+"\t\t");
                else
                    System.out.print("--\t");

                printCustomersInQueue(booth1.queue);
                System.out.print("\t\t");
                printCustomersInQueue(booth2.queue);

                System.out.println();*/
                //Printing ends


                unitTime++;
            }
        }

        //After All entries, processing done here
        while(booth1.queue.length()>0 || booth2.queue.length() >0) {
            simulateDequeue(booth1,booth2,unitTime);
            simulateQueueSwitching(booth1,booth2,unitTime);

            // Printing the simulation
            /*System.out.print(unitTime+"\t");
            if(booth1.teller != null)
                System.out.print("C"+booth1.teller.customerNo+"\t");
            else
                System.out.print("--");
            System.out.print("\t");
            if(booth2.teller != null)
                System.out.print("C"+booth2.teller.customerNo+"\t\t");
            else
                System.out.print("--\t");

            printCustomersInQueue(booth1.queue);
            System.out.print("\t\t");
            printCustomersInQueue(booth2.queue);
            System.out.println();*/
            //Printing ends


            unitTime++;
        }

        System.out.println("Booth 1 finishes service at t="+booth1.nextServiceTime);
        System.out.println("Booth 2 finishes service at t="+booth2.nextServiceTime);

    }
}
