import java.util.*;
import java.io.*;

/*
    Nested for loop , boolean comparison (logical operators)


 */
public class TicTacToe {
    public static char[][] grid = new char[3][];
    public static HashSet<String> set = new HashSet<>();
    public static void solveOne(String input){
        if(input.charAt(0) == input.charAt(1) && input.charAt(0) == input.charAt(2)){
            set.add(Character.toString(input.charAt(0)));
        }
    }

    public static void solveTwo(String input){
        if(input.charAt(0) == input.charAt(1) && input.charAt(0) != input.charAt(2)){
            if(input.charAt(0) < input.charAt(2)){
                set.add("" + input.charAt(0) + input.charAt(2));
            }else{
                set.add("" + input.charAt(2) + input.charAt(0));
            }
        }
        if(input.charAt(0) == input.charAt(2) && input.charAt(0) != input.charAt(1)){
            if(input.charAt(2) < input.charAt(1)){
                set.add("" + input.charAt(2) + input.charAt(1));
            }else{
                set.add("" + input.charAt(1) + input.charAt(2));
            }
        }
        if(input.charAt(1) == input.charAt(2) && input.charAt(0) != input.charAt(2)){
            if(input.charAt(0) < input.charAt(1)){
                set.add("" + input.charAt(0) + input.charAt(1));
            }else{
                set.add("" + input.charAt(1) + input.charAt(0));
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // Read in the grid.
        Scanner stdin = new Scanner(new File("2.in"));
        PrintWriter out = new PrintWriter(new FileWriter("2.out"));
        for (int i = 0; i < 3; i++)
            grid[i] = stdin.next().toCharArray();
        //Horizontals
        String a = String.valueOf(grid[0][0]) + grid[0][1] + grid[0][2];
        String b = String.valueOf(grid[1][0]) + grid[1][1] + grid[1][2];
        String c = String.valueOf(grid[2][0]) + grid[2][1] + grid[2][2];

        //Verticals
        String d = String.valueOf(grid[0][0]) + grid[1][0] + grid[2][0];
        String e = String.valueOf(grid[0][1]) + grid[1][1] + grid[2][1];
        String f = String.valueOf(grid[0][2]) + grid[1][2] + grid[2][2];

        //Diagonals
        String g = String.valueOf(grid[0][0]) + grid[1][1] + grid[2][2];
        String h = String.valueOf(grid[0][2]) + grid[1][1] + grid[2][0];

        String arr[] = {a, b, c, d, e, f, g, h};
        for (String str : arr) {
            solveOne(str);
        }
        int answerA = set.size();
        out.println(answerA);
        System.out.println(answerA);
        for (String str : arr) {
            solveTwo(str);
        }
        out.println(set.size() - answerA);
        System.out.println(set.size() - answerA);
        out.close();
        stdin.close();
    }
        /*






        // Solve and output!
        PrintWriter out = new PrintWriter(new FileWriter("tttt.out"));
        out.println(solveOne());
        out.println(solveTwo());
        out.close();
        stdin.close();
    }

    // Return how many individual cows win.
    public static int solveOne() {
        int res = 0;
        for (int i=0; i<26; i++)
            if (win(""+(char)('A'+i)))
                res++;
        return res;
    }

    // Return how many pairs of cows win.
    public static int solveTwo() {
        int res = 0;
        for (int i=0; i<26; i++)
            for (int j=i+1; j<26; j++)
                if (win(""+((char)('A'+i))+((char)('A'+j))))
                    res++;
        return res;
    }

    // Returns true iff any combo of letters in possible wins.
    public static boolean win(String possible) {

        // Check rows.
        for (int i=0; i<3; i++) {

            // Store how many of each letter show up.
            int[] freq = new int[possible.length()];
            for (int j=0; j<3; j++)
                for (int k=0; k<possible.length(); k++)
                    if (possible.charAt(k) == grid[i][j])
                        freq[k]++;

            // Check each frequency and total.
            int tot = 0;
            boolean ok = true;
            for (int j=0; j<freq.length; j++) {
                if (freq[j] == 0) ok = false;
                tot += freq[j];
            }

            // This is when we are okay.
            if (ok && tot == 3) return true;
        }

        // Check cols.
        for (int j=0; j<3; j++) {

            // Store how many of each letter show up.
            int[] freq = new int[possible.length()];
            for (int i=0; i<3; i++)
                for (int k=0; k<possible.length(); k++)
                    if (possible.charAt(k) == grid[i][j])
                        freq[k]++;

            // Check each frequency and total.
            int tot = 0;
            boolean ok = true;
            for (int i=0; i<freq.length; i++) {
                if (freq[i] == 0) ok = false;
                tot += freq[i];
            }

            // This is when we are okay.
            if (ok && tot == 3) return true;
        }

        // Forward diagonal.
        int[] freq = new int[possible.length()];
        for (int i=0; i<3; i++)
            for (int k=0; k<possible.length(); k++)
                if (possible.charAt(k) == grid[i][i])
                    freq[k]++;

        int tot = 0;
        boolean ok = true;
        for (int j=0; j<freq.length; j++) {
            if (freq[j] == 0) ok = false;
            tot += freq[j];
        }

        // This is okay.
        if (ok && tot == 3) return true;

        // Backward diagonal
        freq = new int[possible.length()];
        for (int i=0; i<3; i++)
            for (int k=0; k<possible.length(); k++)
                if (possible.charAt(k) == grid[2-i][i])
                    freq[k]++;

        tot = 0;
        ok = true;
        for (int j=0; j<freq.length; j++) {
            if (freq[j] == 0) ok = false;
            tot += freq[j];
        }

        // Or this...
        if (ok && tot == 3) return true;

        // If we get here we didn't win.
        return false;
        }
         */
}
