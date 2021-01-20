import java.util.*;
import java.io.*;


public class family {

    public static int n;
    public static String[][] input;
    public static HashMap<String, Integer> map;
    public static node[] cows;
/*      public static void main(String[] args) throws Exception {

        // Read in the grid.
        Scanner stdin = new Scanner(new File("family.in"));
        PrintWriter out = new PrintWriter(new FileWriter("family.out"));
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


        String[] AA = (String[])d.toArray();
        String[] BB = (String[])e.toArray();

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
        out.println(c[1]+ "is the " + a + " of " + c[0]);

        out.close();
        stdin.close();
    }

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

 */


public static void main(String[] args) throws Exception {
        // Read in the grid.
        Scanner stdin = new Scanner(new File("family.in"));
        n = stdin.nextInt();
        // Set up Hash Map of Strings to IDs.
        map = new HashMap<String,Integer>();
        map.put(stdin.next(), 0);
        map.put(stdin.next(), 1);

        // Read in relationships, adding names to map.
        int id = 2;
        input = new String[n][2];
        for (int i=0; i<n; i++) {
            for (int j=0; j<2; j++) {
                input[i][j] = stdin.next();
                if (!map.containsKey(input[i][j]))
                    map.put(input[i][j], id++);
            }
        }

        // Set up initial nodes.
        cows = new node[id];
        Arrays.fill(cows, null);
        for (int i=0; i<n; i++)
            for (int j=0; j<2; j++)
                if (cows[map.get(input[i][j])] == null)
                    cows[map.get(input[i][j])] = new node(input[i][j]);

        // Add links for each relationship.
        for (int i=0; i<n; i++) {
            int momI = map.get(input[i][0]);
            int kidI = map.get(input[i][1]);
            cows[momI].kids.add(cows[kidI]);
            cows[kidI].mom = cows[momI];
        }

        // Solve and output!
        PrintWriter out = new PrintWriter(new FileWriter("family.out"));
        out.println(solve());
        out.close();
        stdin.close();
    }

    public static String solve() {

        // Get first mother of Bessie
        ArrayList<node> bList = new ArrayList<node>();
        node bStart = cows[1];
        bList.add(bStart);
        int iter = 0;
        while (true) {

            // Direct ancestor.
            if (bStart == cows[0]) {
                if (iter == 1) return cows[0]+" is the mother of "+cows[1];
                if (iter == 2) return cows[0]+" is the grand-mother of "+cows[1];
                String ret = "";
                for (int i=2; i<iter; i++) ret = ret + "great-";
                return cows[0]+" is the "+ret+"grand-mother of "+cows[1];
            }

            // Go up, if we can.
            node tmp = bStart.mom;
            if (tmp == null) break;
            else {
                bStart = tmp;
                bList.add(bStart);
            }
            iter++;
        }

        ArrayList<node> eList = new ArrayList<node>();
        node eStart = cows[0];
        eList.add(eStart);
        int iter2 = 0;
        while (true) {

            // Direct ancestor.
            if (eStart == cows[1]) {
                if (iter2 == 1) return cows[1]+" is the mother of "+cows[0];
                if (iter2 == 2) return cows[1]+" is the grand-mother of "+cows[0];
                String ret = "";
                for (int i=2; i<iter2; i++) ret = ret + "great-";
                return cows[1]+" is the "+ret+"grand-mother of "+cows[0];
            }

            // Go up if we can.
            node tmp = eStart.mom;
            if (tmp == null) break;
            else {
                eStart = tmp;
                eList.add(eStart);
            }
            iter2++;
        }

        // Unrelated.
        if (eStart != bStart) return "NOT RELATED";

        // Siblings case.
        if (cows[0].mom != null && cows[0].mom == cows[1].mom) return "SIBLINGS";

        // Start at the common relative, going back down the paths to last common "mom".
        Collections.reverse(eList);
        Collections.reverse(bList);
        int i = 0;
        while (eList.get(i) == bList.get(i)) i++;
        i--;

        // How far down you have to go to get Elsie and Bessie.
        int eDown = eList.size() - i - 1;
        int bDown = bList.size() - i - 1;

        // cow[0] is an aunt of cow[1].
        if (eDown == 1) {
            if (bDown == 2) return cows[0]+" is the aunt of "+cows[1];
            String ret = "";
            for (int j=2; j<bDown; j++) ret = ret + "great-";
            return cows[0]+" is the "+ret+"aunt of "+cows[1];
        }

        // cow[1] is an aunt of cow[0]
        else if (bDown == 1) {
            if (eDown == 2) return cows[1]+" is the aunt of "+cows[0];
            String ret = "";
            for (int j=2; j<eDown; j++) ret = ret + "great-";
            return cows[1]+" is the "+ret+"aunt of "+cows[0];
        }

        // Finally, I am done...
        else return "COUSINS";
    }
}

class node {

    public String name;
    public ArrayList<node> kids;
    public node mom;

    public node(String n) {
        name = n;
        kids = new ArrayList<node>();
        mom = null;
    }

    public String toString() {
        return name;
    }

}