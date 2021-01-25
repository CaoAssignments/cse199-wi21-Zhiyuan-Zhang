import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
	// write your code here
        Scanner scan = new Scanner(new File("1.in"));
        PrintWriter p = new PrintWriter(new FileWriter("1.out"));
        int num = scan.nextInt();
        int money = scan.nextInt();
        int[] ascend = new int[num];
        int[] P = new int[num];
        int[] S = new int[num];
        for (int i = 0; i < num; i++) {
            P[i] = scan.nextInt();
            S[i] = scan.nextInt();
            ascend[i] =P[i] + S[i];
        }
        Arrays.sort(ascend);
        int i = 0;
        int numCow = 0;
        while(money - ascend[i] > 0){
            money -= ascend[i];
            i++;
            numCow++;
        }
        int[] coup = new int[num - i];
        for (int j = i; j < num; j++) {
            coup[j - i] = P[j]/2 + S[j];
        }
        Arrays.sort(coup);
        if(money - coup[0] >= 0)
            numCow++;

        p.println(numCow);
        System.out.println(numCow);

        scan.close();
        p.close();
    }
}
