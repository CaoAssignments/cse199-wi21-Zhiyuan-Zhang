import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("1.in"));
        PrintWriter p = new PrintWriter(new File("1.out"));
        int max = 0;
        int n = scan.nextInt();
        int k = scan.nextInt()*2 +1;
        int size = 0;
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
            b[i] = scan.nextInt();
            size = Math.max(size,b[i]) + 1;
        }

        int[] c = new int[size];
        for (int i = 0; i < n; i++) {
            c[b[i]] = a[i];
        }
        for (int i = 0; i < size - k; i++) {
            int sum  = 0;
            for (int j = 0; j < k; j++) {
                sum += c[i + j];
            }
            max = Math.max(max,sum);
        }
        System.out.println(max);
        p.println(max);
        scan.close();
        p.close();
    }
}
