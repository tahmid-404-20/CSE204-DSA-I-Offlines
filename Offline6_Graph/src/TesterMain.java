

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.System.exit;

public class TesterMain {
    private static final String INPUT_FILE_NAME = "inputs.txt";
    private static final String OUTPUT_FILE_NAME = "outputs.txt";

    public static void main(String[] args) {
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
            int nDice = scr.nextInt();
            int nCell = scr.nextInt();
            int nLadders = scr.nextInt();
            Point[] ladders = new Point[nLadders];

            for(int i=0;i<nLadders;i++)
            {
                int p1 = scr.nextInt();
                int p2 = scr.nextInt();
                ladders[i] = new Point(p1,p2);
            }

            int nSnakes = scr.nextInt();
            Point[] snakes = new Point[nSnakes];
            for(int i=0;i<nSnakes;i++){
                int p1 = scr.nextInt();
                int p2 = scr.nextInt();
                snakes[i] = new Point(p1,p2);
            }

            Graph graph = new Graph(nCell,nDice,snakes,ladders);
            graph.Simulate();
            System.out.println();
        }
    }
}
