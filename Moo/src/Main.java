import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception{
	// write your code here
        Scanner scan = new Scanner(new File("1.in"));
        PrintWriter p = new PrintWriter(new File("1.out"));
        int c = scan.nextInt();
        p.println(check(c));
        System.out.println(check(c));
        scan.close();
        p.close();
    }

    public static char check(int a){
        int n = 3;
        int numO = 3;
        int curr = 0;
        while(a > n){
            curr = n;
            numO++;
            n = 2 * n + numO;
        }
        if(a - curr < numO){
            if(a - curr == 1){
                return 'm'; // m
            }else
                return 'o'; // o
        }

        return check(a - curr - numO);
    }
}
