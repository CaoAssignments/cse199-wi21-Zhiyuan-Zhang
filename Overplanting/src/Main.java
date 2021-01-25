import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(new File("1.in"));
        PrintWriter P = new PrintWriter(new File("1.out"));
        int n = scan.nextInt();
        int[][] a = new int[n][4];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j] = scan.nextInt();
                max = Math.max(a[i][j], max);
            }
        }
        boolean [][] boo = new boolean[max][max];
        for (int i = 0; i < n; i++) {
            for (int j = a[i][0]; j < a[i][2]; j++) {
                for (int k = a[i][3]; k < a[i][1]; k++) {
                    boo[j][k] = true;
                }
            }
        }
        int num = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if(boo[i][j]) num++;
            }
        }
        P.println(num);
        System.out.println(num);
        P.close();
        scan.close();
    }
}
