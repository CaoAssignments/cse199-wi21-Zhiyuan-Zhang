import java.util.*;
import java.io.*;

public class milkingorder {

    public static int n;
    public static int[] order;
    public static int m;
    public static int[] priority;


    public static int find(int[] arr, int num){
        for(int i = arr.length - 1; i > -1; i--){
            if (num == arr[i])
                return i;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {

        Scanner stdin = new Scanner(new File("1.in"));
        n = stdin.nextInt();
        m = stdin.nextInt();
        int numFixed = stdin.nextInt();
        order = new int[n];

        priority = new int[m];
        for (int i=0; i<m; i++) {
            priority[i] = stdin.nextInt();

        }

        // Place fixed cows.
        for (int i=0; i<numFixed; i++) {
            int cow = stdin.nextInt();
            int pos = stdin.nextInt() - 1;
            order[pos] = cow;
        }

        int num;
        int curr = order.length - 1;
        for (int i = m - 1; i > -1; i--){
            if((num = find(order,priority[i])) == -1){
                for(int j = curr; j > -1; j--){
                    if(order[j] == 0){
                        order[j] = priority[i];
                        curr = j;
                        break;
                    }
                }
            }else{
                curr = num;
            }
        }

        int result = 0;
        for(int i = 0; i < order.length; i++){
            if(order[i] == 0){
                result = i + 1;
                break;
            }
        }

        PrintWriter out = new PrintWriter(new FileWriter("1.out"));
        out.println(result);
        System.out.println(result);
        out.close();
        stdin.close();
    }

}
