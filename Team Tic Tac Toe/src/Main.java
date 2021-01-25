import java.util.*;
import java.io.*;

public class Main {
    public static char[][] grid = new char[3][];
    public static HashSet<String> set = new HashSet<>();

    public static void solveOne(String input) {
        if (input.charAt(0) == input.charAt(1) && input.charAt(0) == input.charAt(2)) {
            set.add(Character.toString(input.charAt(0)));
        }
    }

    public static void solveTwo(String input) {
        if (input.charAt(0) == input.charAt(1) && input.charAt(0) != input.charAt(2)) {
            if (input.charAt(0) < input.charAt(2)) {
                set.add("" + input.charAt(0) + input.charAt(2));
            } else {
                set.add("" + input.charAt(2) + input.charAt(0));
            }
        }
        if (input.charAt(0) == input.charAt(2) && input.charAt(0) != input.charAt(1)) {
            if (input.charAt(2) < input.charAt(1)) {
                set.add("" + input.charAt(2) + input.charAt(1));
            } else {
                set.add("" + input.charAt(1) + input.charAt(2));
            }
        }
        if (input.charAt(1) == input.charAt(2) && input.charAt(0) != input.charAt(2)) {
            if (input.charAt(0) < input.charAt(1)) {
                set.add("" + input.charAt(0) + input.charAt(1));
            } else {
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

        String[] arr = {a, b, c, d, e, f, g, h};
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
}