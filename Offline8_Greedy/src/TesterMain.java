import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TesterMain {
    public static void main(String[] args) {

        int k,n;

        Scanner scr = new Scanner(System.in);
        n = scr.nextInt();
        k = scr.nextInt();
        Integer [] price = new Integer[n];
        for(int i=0;i<n;i++)
        {
            price[i] = scr.nextInt();
        }

        Arrays.sort(price, Collections.reverseOrder());

        long sum = 0;
        int factor = 0;
        for(int i=0;i<n;i++)
        {
            if(i%k == 0)
                factor++;

            sum += factor*price[i];

        }

        System.out.println(sum);

    }

}
