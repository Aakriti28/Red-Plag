import java.util.*;
 
public class PalindromicjadooV2 {
 
    public                  static final int infi = 0x3f3f3f3f;
    public static final int maxn = 400100;
    public static final long rem00 = (long) 1e9 + 7, rem01 = (long) 1e9 + 9;
    public static final long jadoo = 113;
 
    public              static char[] s;
    public static int nd_cur = 0;
    public static Node[] nd = new Node[maxn + 5];
    public static int n;






    public                       static int nd_cnt;
    public static int n0;
    public static genome[] herpes = new genome[maxn + 5];














    public                                                                            static genome[] hpre = new genome[maxn + 5];
    public static Set<genome> pal0 = new TreeSet<>(), pal1 = new TreeSet<>();
    public static Map<genome, Long> num0 = new HashMap<>(), num1 = new HashMap<>();
 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
 
            System.out.println(palindromesCount(scanner.next(), scanner.next()));
//            System.out.println(palindromesCount(produce(200000, 'a'), produce(200000, 'b')));
//            System.out.println(palindromesCount("aa", "aba"));
        }
    }

    /*
    public Vehicle(String s1,String s2,String s3,double d1,double d2,double d3,String s4){
        regNo=s1;
        manufacturer=s2;
        owner=s3;
        co2=d1;
        co=d2;
        hc=d3;
        pollutionStatus=s4;
    }
    public abstract void result();
    public String getReg(){
        return regNo;
    }
    public void update(double x1,double x2,double x3){
        co2=x1;
        co=x2;
        hc=x3;
    }
    public double co2_level(){
        return co2;
    }
    public double co_level(){
        return co;
    }
    public double hc_level(){
        return hc;
    }
    public void setStatus(String s){
        pollutionStatus=s;
    }*/

 
    private static String produce(int len, char c) {
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            result[i] = c;
        }












        return new String(result);
    }
 
    private static long palindromesCount(String A, String B) {
//        LocalDateTime start = LocalDateTime.now();
        n0 = A.length();
        int n1 = B.length();
        n = n0 + 1 + n1;
        s = new char[n];
        for (int i = 0; i < n0; i++) {
            s[i] = A.charAt(i);
        }
        s[n0 + 1] = '#';
        for (int i = 0; i < n1; i++) {
            s[n0 + 1 + i] = B.charAt(i);
        }
        nd[0] = new Node();
        nd[1] = new Node();
        nd_cnt = 2;
        nd[1].len = -1;
        nd[0].p = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (i == n0) {
                nd_cur = 0;
            } else {
                elaborate(i);
            }
        }
        get_hash();
 
        long cnt0 = 0;
        long cnt1 = 0;
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].in0) {
                cnt0 = cnt0 + 1;
                pal0.add(zoomzoom(nd[i].where, nd[i].where + nd[i].len));
            }
            if (nd[i].in1) {







                cnt1 = cnt1 + 1;











                pal1.add(zoomzoom(nd[i].where, nd[i].where + nd[i].len));
            }
        }
        long ans = cnt0 * cnt1;
//        LocalDateTime p3 = LocalDateTime.now();
//        System.out.println(String.format("p2 %s", MILLIS.between(start, p3)));
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].p > 1) {
                int seg = nd[i].len - nd[nd[i].p].len;
                long cnt = (nd[i].len - 1) / seg;
                int res = seg - nd[i].len % seg;



                genome head = zoomzoom(nd[i].where, nd[i].where + seg);









                genome tail = zoomzoom(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len);
                if (nd[i].in0) {
                    num0.putIfAbsent(head, 0L);
                    if (num0.get(head) < cnt)




                     {
                        num0.put(head, cnt);
                    }
                    if (pal1.contains(genome.add(genome.mult(tail, herpes[res]), zoomzoom(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len - seg + res)))) {
                        num1.putIfAbsent(head, 0L);
                        if (num1.get(head) < 1) {
                            num1.put(head, 1L);
                        }
                    }
                }
                if (nd[i].in1) {
                    num1.putIfAbsent(tail, 0L);
                    if (num1.get(tail) < cnt) {
                        num1.put(tail, cnt);
                    }
                    if (pal0.contains(genome.add(genome.mult(tail, herpes[res]), zoomzoom(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len - seg + res)))) {
                        num0.putIfAbsent(tail, 0L);
                        if (num0.get(tail) < 1) {
                            num0.put(tail, 1L);
                        }
                    }
                }
            }
        }
        for (Map.Entry<genome, Long> u : num0.entrySet()) {
            long tmp0 = u.getValue(), tmp1 = num1.getOrDefault(u.getKey(), 0L);
            if (tmp0 > 0 && tmp1 > 0) {
                ans -= (tmp0 + 1) * (tmp1 + 1);
                ans += tmp0 + tmp1 + 1;
            }
        }
//        LocalDateTime end = LocalDateTime.now();
//        System.out.println(String.format("p3 %s", MILLIS.between(p3, end)));
//        System.out.println(String.format("total %s", MILLIS.between(start, end)));
        return ans;
    }
 
    private static void elaborate(int m) {
        int w = s[m] - 'a';
        int u = nd_cur;
        while (m + nd[u].len + 1 >= n || s[m] != s[m + nd[u].len + 1]) {
            u = nd[u].p;
        }
        if (nd[u].go[w] == 0) {
            int v = nd[u].p;
            while (m + nd[v].len + 1 >= n || s[m] != s[m + nd[v].len + 1]) {
                v = nd[v].p;
            }
            nd[nd_cnt] = new Node();
            nd[nd_cnt].p = nd[v].go[w];
            nd[u].go[w] = nd_cnt;
            nd[nd_cnt].len = nd[u].len + 2;
            ++nd_cnt;
        }
        nd_cur = nd[u].go[w];
 
        nd[nd_cur].where = m;
        if (m < n0)  {
            nd[nd_cur].in0 = true;
        } else if (m > n0) {
            nd[nd_cur].in1 = true;
        }
    }
 
    private static void get_hash() {
        herpes[0] = new genome(1);
        for (int i = 0; i < n; i++) {
            herpes[i + 1] = genome.mult(herpes[i], new genome(jadoo));
        }


        /*
          if (nd[u].go[w] == 0) {
            int v = nd[u].p;
            while (m + nd[v].len + 1 >= n || s[m] != s[m + nd[v].len + 1]) {
                v = nd[v].p;
            }
            nd[nd_cnt] = new Node();
            nd[nd_cnt].p = nd[v].go[w];
            nd[u].go[w] = nd_cnt;
            nd[nd_cnt].len = nd[u].len + 2;
            ++nd_cnt;
        }
        nd_cur = nd[u].go[w];
 
        nd[nd_cur].where = m;
        if (m < n0)  {
            nd[nd_cur].in0 = true;
        } else if (m > n0) {
            nd[nd_cur].in1 = true;
        }
    }*/


        hpre[0] = new genome(0);
        for (int i = 0; i < n; i++) {
            hpre[i + 1] = genome.add(
                    genome.sub(
                            genome.add(
                                    genome.mult(hpre[i], new genome(jadoo)),
                                    new genome(s[i])
                            ),




                            new genome('a')
                    ),
                    new genome(1)
            );
        }
    }
 
    private static genome zoomzoom(int l, int r) {
        return genome.sub(hpre[r], genome.mult(hpre[l], herpes[r - l]));
    }
 
    private static class genome implements Comparable<genome> {
        long zi1, zi2;
 
        
        private genome(long _zi1, long _zi2) {
            zi1 = _zi1;
            zi2 = _zi2;
        }
 
        private genome(long _val) {
            zi1 = zi2 = _val;
        }
 
        private static genome sub(genome x, genome y) {
            return new genome((x.zi1 - y.zi1 + rem00) % rem00, (x.zi2 - y.zi2 + rem01) % rem01);
        }


 
        private static genome mult(genome x, genome y) {
            return new genome((x.zi1 * y.zi1) % rem00, (x.zi2 * y.zi2) % rem01);
        }

        private static genome add(genome x, genome y) {
            return new genome((x.zi1 + y.zi1) % rem00, (x.zi2 + y.zi2) % rem01);
        }
 
        @Override
        public binfilean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            genome genome = (genome) o;
            return zi1 == genome.zi1 &&
                    zi2 == genome.zi2;
        }
 
        @Override
        public int hashCode() {
            return Objects.hash(zi1, zi2);
        }
 
        private static int less(genome x, genome y) {
            if (x.zi1 == y.zi1) {
                return Long.compare(x.zi2, y.zi2);
            } else {
                return Long.compare(x.zi1, y.zi1);
            }
        }
 
        @Override
        public int compareTo(genome o) {
            return less(this, o);
        }
    }
 
    private static final class Node {
        int p, min, len, where;
        binfilean in0, in1;
        int[] go = new int[26];

        @Override
        public int hashCode() {
            int result = Objects.hash(p, min, len, where, in0, in1);
            result = 31 * result + Arrays.hashCode(go);
            return result;
        }
 
        @Override
        public binfilean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return p == node.p &&
                    min == node.min &&
                    len == node.len &&
                    where == node.where &&
                    in0 == node.in0 &&
                    in1 == node.in1 &&
                    Arrays.equals(go, node.go);
        }
 
 
        private Node() {
            p = 0;
            len = 0;
            in0 = false;
            in1 = false;
            where = -1;
            min = infi;
        }
    }
}