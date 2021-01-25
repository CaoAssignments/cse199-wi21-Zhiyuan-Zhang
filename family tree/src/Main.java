import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        int n;
        // Read in the grid.
        Scanner stdin = new Scanner(new File("1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("1.out"));
        n = stdin.nextInt();
        String[] mother = new String[n];
        String[] kid = new String[n];
        String[] c = {stdin.next(),stdin.next()};
        for(int i = 0; i < n; i++){
            mother[i] = stdin.next();
            kid[i] = stdin.next();
        }
        ArrayList<String> d = new ArrayList<>();
        ArrayList<String> e = new ArrayList<>();

        String curr = c[0];
        d.add(curr);
        while(curr != null){
            int a = find(kid, curr);
            if(a == -1) break;
            d.add(mother[a]);
            curr = mother[a];
        }

        String cur = c[1];
        e.add(cur);
        while(cur != null){
            int a = find(kid, cur);
            if(a == -1) break;
            e.add(mother[a]);
            cur = mother[a];
        }


        Object[] A = d.toArray();
        Object[] B = e.toArray();
        String[] AA = new String[d.size()];
        String[] BB = new String[e.size()];
        for (int i = 0; i < d.size(); i++) {
            AA[i] = (String)A[i];
        }
        for (int i = 0; i < e.size(); i++) {
            BB[i] = (String)B[i];
        }
        int trackA = 0;
        int trackB = 0;

        for(int i = 0; i < AA.length; i++){
            for(int j = 0; j < BB.length; j++){
                if(AA[i].equals(BB[j])){
                    trackA = i;
                    trackB = j;
                }
            }
        }

        String a = "";
        if(trackA == 0 && trackB == 0){
            a = "NOT RELATED";
        }else if(trackB > 1){
            a = "COUSIN";
        }else if(trackA == 1 && trackB == 1){
            a ="SISTER";
        }else if(trackA != 0 && trackB == 0){
            if(trackA == 1){
                a = "Mother";
            }else if(trackA == 2){
                a = "grand-mother";
            }else{
                a = great(trackA - 2) + "grand-mother";
            }
        }else{
            a = great(trackA - 2) + "aunt";
        }
        out.println(c[1]+ " is the " + a + " of " + c[0]);

        out.close();
        stdin.close();
    }

    public static int find(String[] a, String b){
        for(int i = 0; i < a.length; i++){
            if(a[i].equals(b))
                return i;
        }
        return -1;
    }
    public static String great(int b){
        String result = "";
        for(int i = 0; i < b; i++){
            result +="great-";
        }
        return result;
    }

}