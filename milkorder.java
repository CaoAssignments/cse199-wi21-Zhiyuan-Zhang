import java.util.*;
import java.io.*;

public class milkorder {

    public static int n;
    public static int[] order;
    public static int m;
    public static int[] priority;
    public static ArrayList<Integer> place;
    public static boolean[] hasPriority;

    public static void main(String[] args) throws Exception {

        // Read in the grid.
        Scanner stdin = new Scanner(new File("milkorder.in"));
        n = stdin.nextInt();
        m = stdin.nextInt();
        int numFixed = stdin.nextInt();
        order = new int[n];
        Arrays.fill(order, -1);

        // Used keeps track of the cows who are in the priority list who have a fixed location.
        boolean[] used = new boolean[m];

        priority = new int[m];
        hasPriority = new boolean[n];
        for (int i=0; i<m; i++) {
            priority[i] = stdin.nextInt();
            hasPriority[priority[i]-1] = true;
        }

        // Place fixed cows.
        for (int i=0; i<numFixed; i++) {
            int cow = stdin.nextInt();
            int pos = stdin.nextInt()-1;
            order[pos] = cow;

            // This is really bad, but I know it'll run in time.
            for (int j=0; j<m; j++)
                if (cow == priority[i])
                    used[j] = true;
        }

        // Store all the cows we need to place.
        place = new ArrayList<Integer>();
        for (int i=0; i<m; i++)
            if (!used[i])
                place.add(priority[i]);

        // Solve and output!
        PrintWriter out = new PrintWriter(new FileWriter("milkorder.out"));
        out.println(solve());
        out.close();
        stdin.close();
    }

    public static int solve() {

        // Try cow #1 in each possible slot.
        for (int i=0; i<n; i++) {
            if (order[i] != -1) continue;

            System.out.println("Trying "+i);

            // Temporarily place i here.
            order[i] = 1;
            if (possible()) return i+1;
            order[i] = -1;
        }

        // Should never get here.
        return -1;
    }

    public static boolean possible() {

        // A new copy so we don't mess up order.
        int[] tmp = Arrays.copyOf(order, n);

        // Place everything we have to in order.
        int j = 0;
        for (int i=0; i<n; i++) {
            if (tmp[i] == -1) {
                tmp[i] = place.get(j);
                j++;
            }
        }

        j = 0;

        // Now check if the priorities are maintained.
        for (int i=0; i<n; i++) {
            if (!hasPriority[i]) continue;

            // Oops, not a match!
            if (tmp[i] != priority[j++])
                return false;
        }

        for (int i=0; i<n; i++)
            System.out.print(tmp[i]+" ");
        System.out.println();

        // Check if any number is listed twice.
        boolean[] tmpUsed = new boolean[n];
        for (int i=0; i<n; i++) {
            if (tmpUsed[tmp[i]-1]) return false;
            tmpUsed[tmp[i]-1] = true;
        }



        // Ok if we get here.
        return true;
    }


/*
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

 */
}
