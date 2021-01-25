import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static int n;
    static boolean track[][];
    static boolean bull[][];
    static int cow = 0;
    static int man = 0;
    static Character[][] curr;
    static Character[][] Cow;
    public static void main(String[] args) throws Exception{
        // write your code here
        Scanner scan = new Scanner(new File("1.in"));
        PrintWriter p = new PrintWriter(new FileWriter("1.out"));
        n = scan.nextInt();
        track= new boolean[n][n];
        bull = new boolean[n][n];
        curr = new Character[n][n];
        Cow = new Character[n][n];
        for (int i = 0; i < n; i++) {
            String a = scan.next();
            for (int j = 0; j < n; j++) {
                curr[i][j] = a.charAt(j);
                bull[i][j] = false;
                track[i][j] = false;
                System.out.print(curr[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(curr[i][j].equals('R'))
                    Cow[i][j] = 'G';
                else
                    Cow[i][j] = curr[i][j];

                System.out.print(Cow[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!track[i][j]){ man++;
                    System.out.println(i+ " " + j);
                }
                Try(track,curr,i,j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!bull[i][j]) cow++;
                Try(bull,Cow,i,j);
            }
        }
        System.out.println(man + "  " + cow);
        p.println(man + "  " + cow);
        scan.close();
        p.close();
    }

    public static void Try(boolean[][]c, Character[][]d, int a, int b){
        if (c[a][b]) return;

        c[a][b] = true;
        if(!invalid(a+1,b) && d[a][b].equals(d[a+1][b])) Try(c,d,a+1,b);
        if(!invalid(a,b+1) && d[a][b].equals(d[a][b+1])) Try(c,d, a,b+1);
        if(!invalid(a-1,b) && d[a][b].equals(d[a-1][b])) Try(c,d,a-1,b);
        if(!invalid(a,b-1) && d[a][b].equals(d[a][b-1])) Try(c,d, a,b-1);

    }


    public static boolean invalid(int a, int b){
        return a < 0 || b < 0 || a == n || b == n;
    }
}
