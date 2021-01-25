import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Scanner scan = new Scanner(new File("2.in"));
        PrintWriter p = new PrintWriter(new FileWriter("2.out"));
        int n = scan.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = scan.nextInt();
        for (int i = 0; i < n; i++) b[i] = scan.nextInt();
        ArrayList<Integer> c = new ArrayList<>();
        for (int i = 0; i < n; i++) c.add(a[i]);
        for (int i = 0; i < n; i++) if(a[i] == b[i]) c.remove((Integer)a[i]);
        int numOfCycles = 0;
        int cyclic = -1;
        while(c.size() != 0){
            numOfCycles++;
            int curr = 0;
            int now = c.get(0);
            int index = find(now, a);
            while(c.remove((Integer)a[index])){
                curr++;
                index = find(b[index], a);
            }
            cyclic = Math.max(curr, cyclic);
        }
        System.out.println(numOfCycles + " " + cyclic);
        p.println(numOfCycles + " " + cyclic);
        scan.close();
        p.close();
    }

    public static int find(int a, int[] b){
        for (int i = 0; i < b.length; i++) {
            if(a == b[i]) return i;
        }
        return -1;
    }


}
