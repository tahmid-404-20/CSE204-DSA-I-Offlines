import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.System.exit;

public class Tester {
    public static void main(String[] args) {
        int X,n,l,s;

        try {
            System.setIn(new FileInputStream(new File("inputs.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("Error redirecting intput");
            exit(1);
        }

        try {
            System.setOut(new PrintStream(new File("outputs.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("Error redirecting output");
            exit(1);
        }

        Scanner scr = new Scanner(System.in);
        int nTestCases = scr.nextInt();

        for(int j=0;j<nTestCases;j++)
        {
            n = scr.nextInt();
            X = scr.nextInt();
            l = scr.nextInt();
            Point[] ladders = new Point[l];
            for(int i=0;i<l;i++)
            {
                int p1 = scr.nextInt();
                int p2 = scr.nextInt();
                ladders[i] = new Point(p1,p2);
            }

            s = scr.nextInt();
            Point[] snakes = new Point[s];
            for(int i=0;i<s;i++){
                int p1 = scr.nextInt();
                int p2 = scr.nextInt();
                snakes[i] = new Point(p1,p2);
            }

            SnakeLadder board = new SnakeLadder(X,n,snakes,ladders);
            board.Simulate();
        }

    }
}
