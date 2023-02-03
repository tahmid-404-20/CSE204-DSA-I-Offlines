import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.exit;

public class TestClass {
    private static final String INPUT_FILE_NAME = "inputs.txt";
    public static void main(String[] args)  {

        BST<Integer,Object> bst = new BST<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            exit(1);
        }

        while(true)
        {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                System.out.println("Failed to read data");
                exit(1);
            }
            if(line == null) break;

            String[] tokens = line.split(" ");

            if(tokens[0].equalsIgnoreCase("I")) {
                Integer i = Integer.parseInt(tokens[1]);
                bst.insert(i);

            } else if(tokens[0].equalsIgnoreCase("D")) {
                Integer i = Integer.parseInt(tokens[1]);
                bst.delete(i);

            } else if(tokens[0].equalsIgnoreCase("F")) {
                Integer i = Integer.parseInt(tokens[1]);
                if(bst.find(i)){
                    System.out.println("True");
                } else{
                    System.out.println("False");
                }

            } else if(tokens[0].equalsIgnoreCase("T")) {
                if(tokens[1].equalsIgnoreCase("Pre"))
                {
                    bst.traversePreOrder();
                } else if(tokens[1].equalsIgnoreCase("In"))
                {
                    bst.traverseInOrder();
                } else if(tokens[1].equalsIgnoreCase("Post"))
                {
                    bst.traversePostOrder();
                } else {
                    System.out.print("Traversal argument not defined");
                }

            } else {
                System.out.println("Invalid Command");
            }
        }

        try {
            br.close();
        } catch (IOException e) {
            System.out.print("Error closing file");
        }
    }
}
