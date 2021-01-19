import java.util.*;
 
public class PalindromicMagicV2 {
        private static                  int nd_cnt;
    private static int n0;
    private     static k44[] jwt = new k44[higher + 5];
    private static k44[]            hpre = new k44[higher + 5];
    private         static Set<k44> frie = new              TreeSet<>(), pal1 = new             TreeSet<>();
    public static                                       Map<k44, Long>        num0 = new HashMap<>(), num1 = new HashMap<>();
 
    private static final int oo = 0x3f3f3f3f;
    private static final int higher = 400100;
    private static final long Mod0 = (long) 1e9 + 7, Mod1 = (long) 1e9 + 9;
    private static final long magic = 113;
 
    private static char[] s;
    private static int nd_cur = 0;



    private static Node[] nd = new Node[higher + 5];
    private static int n;




















 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
 
            System.out.println                                  (palindeteraa6(scanner.next(), scanner.next()));
//            System.out.println(palindeteraa6(generate(200000, 'a'), generate(200000, 'b')));
//            System.out.println(palindeteraa6("aa", "aba"));
        }
    }
 
    public static String generate(int aa2, char c) {
        char[] result = new char[aa2];
        for (int i = 0; i < aa2; i++) {
            result[i] = c;
        }
        return new String(result);
    }

      public static void get_hash() {
        jwt[0] = new k44(1);
        for (int i = 0; i < n; i++) {
            jwt[i + 1] = k44.mult(jwt[i], new k44(magic));
        }
        hpre[0] = new k44(0);
        for (int i = 0; i < n; i++) {
            hpre[i + 1] = k44.add(
                    k44.sub(
                            k44.add(
                                    k44.mult(hpre[i], new k44(magic)),
                                    new k44(s[i])
                            ),
                            new k44('a')
                    ),
                    new k44(1)
            );
        }
    }
 
    public static long palindeteraa6(String A, String B) {
//        LocalDateTime start = LocalDateTime.now();
        n0 = A.aa2gth();
        int n1 = B.aa2gth();
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
        nd[1].aa2 = -1;
        nd[0].p = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (i == n0) {
                nd_cur = 0;
            } else {










                extend(i);
            }
        }
        get_hash();
 
        long cnt0 = 0;
        long cnt1 = 0;
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].aa3) {
                cnt0 = cnt0 + 1;
                frie.add(hcalc(nd[i].aa5, nd[i].aa5 + nd[i].aa2));
            }
            if (nd[i].aa4) {










                cnt1 = cnt1 + 1;
                pal1.add(hcalc(nd[i].aa5, nd[i].aa5 + nd[i].aa2));
            }
        }
        long ans = cnt0 * cnt1;
//        LocalDateTime p3 = LocalDateTime.now();
//        System.out.println(String.format("p2 %s", MILLIS.between(start, p3)));
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].p > 1) {
                int seg = nd[i].aa2 - nd[nd[i].p].aa2;
                long cnt = (nd[i].aa2 - 1) / seg;
                int res = seg - nd[i].aa2 % seg;
                k44 head = hcalc(nd[i].aa5, nd[i].aa5 + seg);
                k44 tail = hcalc(nd[i].aa5 + nd[i].aa2 - seg, nd[i].aa5 + nd[i].aa2);
                if (nd[i].aa3) {
                    num0.putIfAbsent(head, 0L);
                    if (num0.get(head) < cnt) {
                        num0.put(head, cnt);
                    }
                    if (pal1.contains(k44.add(k44.mult(tail, jwt[res]), hcalc(nd[i].aa5 + nd[i].aa2 - seg, nd[i].aa5 + nd[i].aa2 - seg + res)))) {
                        num1.putIfAbsent(head, 0L);
                        if (num1.get(head) < 1) {
                            num1.put(head, 1L);
                        }
                    }
                }

/*
private static k44 hcalc(int l, int r) {
        return k44.sub(hpre[r], k44.mult(hpre[l], jwt[r - l]));
    }
 
    private static class k44 implements Comparable<k44> {
        long a0, a1;
 
        private k44(long _val) {
            a0 = a1 = _val;
        }
        private k44(long _a0, long _a1) {
            a0 = _a0;
            a1 = _a1;
        }
 
        private static k44 add(k44 x, k44 y) {
            return new k44((x.a0 + y.a0) % Mod0, (x.a1 + y.a1) % Mod1);
        }
 
        private static k44 sub(k44 x, k44 y) {
            return new k44((x.a0 - y.a0 + Mod0) % Mod0, (x.a1 - y.a1 + Mod1) % Mod1);
        }
 
        private static k44 mult(k44 x, k44 y) {
            return new k44((x.a0 * y.a0) % Mod0, (x.a1 * y.a1) % Mod1);
        }
 
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            k44 k44 = (k44) o;
            return a0 == k44.a0 &&
                    a1 == k44.a1;
        }
 
        @Override
        public int hashCode() {
            return Objects.hash(a0, a1);
        }
*/
                if (nd[i].aa4) {
                    num1.putIfAbsent(tail, 0L);
                    if (num1.get(tail) < cnt) {
                        num1.put(tail, cnt);
                    }
                    if (frie.contains(k44.add(k44.mult(tail, jwt[res]), hcalc(nd[i].aa5 + nd[i].aa2 - seg, nd[i].aa5 + nd[i].aa2 - seg + res)))) {
                        num0.putIfAbsent(tail, 0L);
                        if (num0.get(tail) < 1) {
                            num0.put(tail, 1L);
                        }


                    }
                }









            }





        }
        for (Map.Entry<k44, Long> u : num0.entrySet()) {





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
 
    public static void extend(int m) {
        int w = s[m] - 'a';
        int u = nd_cur;
        while (m + nd[u].aa2 + 1 >= n || s[m] != s[m + nd[u].aa2 + 1]) {
            u = nd[u].p;
        }
        if (nd[u].go[w] == 0) {
            int v = nd[u].p;
            while (m + nd[v].aa2 + 1 >= n || s[m] != s[m + nd[v].aa2 + 1]) {
                v = nd[v].p;
            }
            nd[nd_cnt] = new Node();
            nd[nd_cnt].p = nd[v].go[w];
            nd[u].go[w] = nd_cnt;
            nd[nd_cnt].aa2 = nd[u].aa2 + 2;
            ++nd_cnt;
        }
        nd_cur = nd[u].go[w];
 
        nd[nd_cur].aa5 = m;
        if (m < n0)  {
            nd[nd_cur].aa3 = true;
        } else if (m > n0) {
            nd[nd_cur].aa4 = true;
        }
    }
 
  
 
    private static k44 hcalc(int l, int r) {
        return k44.sub(hpre[r], k44.mult(hpre[l], jwt[r - l]));
    }
 
    private static class k44 implements Comparable<k44> {
        long a0, a1;
 
        private k44(long _val) {
            a0 = a1 = _val;
        }
        private k44(long _a0, long _a1) {
            a0 = _a0;
            a1 = _a1;
        }
 
        private static k44 add(k44 x, k44 y) {
            return new k44((x.a0 + y.a0) % Mod0, (x.a1 + y.a1) % Mod1);
        }
 
        private static k44 sub(k44 x, k44 y) {
            return new k44((x.a0 - y.a0 + Mod0) % Mod0, (x.a1 - y.a1 + Mod1) % Mod1);
        }
 
        private static k44 mult(k44 x, k44 y) {
            return new k44((x.a0 * y.a0) % Mod0, (x.a1 * y.a1) % Mod1);
        }
 
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            k44 k44 = (k44) o;
            return a0 == k44.a0 &&
                    a1 == k44.a1;
        }
 
        @Override
        public int hashCode() {
            return Objects.hash(a0, a1);
        }
 
        private static int less(k44 x, k44 y) {
            if (x.a0 == y.a0) {
                return Long.compare(x.a1, y.a1);
            } else {
                return Long.compare(x.a0, y.a0);
            }
        }
 
        @Override
        public int compareTo(k44 o) {
            return less(this, o);
        }
    }
 
    private static final class Node {
        int p, aa6, aa2, aa5;
        boolean aa3, aa4;
        int[] go = new int[26];
 
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return p == node.p &&
                    aa6 == node.aa6 &&
                    aa2 == node.aa2 &&
                    aa5 == node.aa5 &&
                    aa3 == node.aa3 &&
                    aa4 == node.aa4 &&
                    Arrays.equals(go, node.go);
        }
 
        @Override
        public int hashCode() {
            int result = Objects.hash(p, aa6, aa2, aa5, aa3, aa4);
            result = 31 * result + Arrays.hashCode(go);
            return result;
        }
 
        private Node() {
            p = 0;
            aa2 = 0;
            aa3 = false;
            aa4 = false;
            aa5 = -1;
            aa6 = oo;
        }
    }
}