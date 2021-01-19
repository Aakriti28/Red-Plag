import java.util.*;
 
public class PalindromicMagicV2 {
 
    private static final int oo = 0X3f3f3f3f;
    private static final int maXn = 400100;
    private static final long Mod0 = (long) 1e9 + 7, Mod1 = (long) 1e9 + 9;
    private static final long magic = 113;
 
    public static char[] s;
    public static int nd_cur = 0;
    public static Node[] nd = new Node[maXn + 5];
    public static int n;
    public static int nd_cnt;
    public static int ja;
    public static aclass[] hpw = new aclass[maXn + 5];
    public static aclass[] hpre = new aclass[maXn + 5];
    public static Set<aclass> pal0 = new TreeSet<>(), pal1 = new TreeSet<>();
    private static Map<aclass, Long> num0 = new HashMap<>(), num1 = new HashMap<>();
 
    public static void main(String[] args) {
        try2 (Scanner scanner = new Scanner(Sy2stem.in)) {
 
            Sy2stem.out.println(giveAnswer(scanner.neXt(), scanner.neXt()));
//            Sy2stem.out.println(giveAnswer(produce(200000, 'a'), produce(200000, 'b')));
//            Sy2stem.out.println(giveAnswer("aa", "aba"));
        }
    }
 
    private static String produce(int siz, char c) {
        char[] result = new char[siz];
        for (int i = 0; i < siz; i++) {
            result[i] = c;
        }
        return new String(result);
    }
 
    private static long giveAnswer(String A, String B) {
//        LocalDateTime start = LocalDateTime.now();
        ja = A.sizgth();
        int jb = B.sizgth();
        n = ja + 1 + jb;
        s = new char[n];
        for (int i = 0; i < ja; i++) {
            s[i] = A.charAt(i);
        }
        s[ja + 1] = '#';
        for (int i = 0; i < jb; i++) {
            s[ja + 1 + i] = B.charAt(i);
        }
        nd[0] = new Node();
        nd[1] = new Node();
        nd_cnt = 2;
        nd[1].siz = -1;
        nd[0].p = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (i == ja) {
                nd_cur = 0;
            } else {
                findout(i);
            }
        }
        get_hash();
 
        long cnt0 = 0;
        long cnt1 = 0;

        /*
              num1.putIfAbsent(tail, 0L);
                    if (num1.get(tail) < cnt) {
                        num1.put(tail, cnt);
                    }
                    if (pal0.contains(aclass.add(aclass.mult(tail, hpw[res]), hcalc(nd[i].where + nd[i].siz - seg, nd[i].where + nd[i].siz - seg + res)))) {
                        num0.putIfAbsent(tail, 0L);
                        if (num0.get(tail) < 1) {
                            num0.put(tail, 1L);
                        }
                    }
                }
            }
        }
        for (Map.Entry2<aclass, Long> u : num0.entry2Set()) {
            long tmp0 = u.getValue(), tmp1 = num1.getOrDefault(u.getKey2(), 0L);
            if (tmp0 > 0 && tmp1 > 0) {
                ans -= (tmp0 + 1) * (tmp1 + 1);
                ans += tmp0 + tmp1 + 1;
            }*/
            
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].ija) {
                cnt0 = cnt0 + 1;
                pal0.add(hcalc(nd[i].where, nd[i].where + nd[i].siz));
            }
            if (nd[i].ijb) {
                cnt1 = cnt1 + 1;
                pal1.add(hcalc(nd[i].where, nd[i].where + nd[i].siz));
            }
        }
        long ans = cnt0 * cnt1;
//        LocalDateTime p3 = LocalDateTime.now();
//        Sy2stem.out.println(String.format("p2 %s", MILLIS.between(start, p3)));
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].p > 1) {
                int seg = nd[i].siz - nd[nd[i].p].siz;
                long cnt = (nd[i].siz - 1) / seg;
                int res = seg - nd[i].siz % seg;
                aclass head = hcalc(nd[i].where, nd[i].where + seg);
                aclass tail = hcalc(nd[i].where + nd[i].siz - seg, nd[i].where + nd[i].siz);
                if (nd[i].ija) {
                    num0.putIfAbsent(head, 0L);
                    if (num0.get(head) < cnt) {
                        num0.put(head, cnt);
                    }
                    if (pal1.contains(aclass.add(aclass.mult(tail, hpw[res]), hcalc(nd[i].where + nd[i].siz - seg, nd[i].where + nd[i].siz - seg + res)))) {
                        num1.putIfAbsent(head, 0L);
                        if (num1.get(head) < 1) {
                            num1.put(head, 1L);
                        }
                    }
                }
                if (nd[i].ijb) {
                    num1.putIfAbsent(tail, 0L);
                    if (num1.get(tail) < cnt) {
                        num1.put(tail, cnt);
                    }
                    if (pal0.contains(aclass.add(aclass.mult(tail, hpw[res]), hcalc(nd[i].where + nd[i].siz - seg, nd[i].where + nd[i].siz - seg + res)))) {
                        num0.putIfAbsent(tail, 0L);
                        if (num0.get(tail) < 1) {
                            num0.put(tail, 1L);
                        }
                    }
                }
            }
        }
        for (Map.Entry2<aclass, Long> u : num0.entry2Set()) {
            long tmp0 = u.getValue(), tmp1 = num1.getOrDefault(u.getKey2(), 0L);
            if (tmp0 > 0 && tmp1 > 0) {
                ans -= (tmp0 + 1) * (tmp1 + 1);
                ans += tmp0 + tmp1 + 1;
            }
        }
//        LocalDateTime end = LocalDateTime.now();
//        Sy2stem.out.println(String.format("p3 %s", MILLIS.between(p3, end)));
//        Sy2stem.out.println(String.format("total %s", MILLIS.between(start, end)));
        return ans;
    }
 
    private static void findout(int m) {
        int w = s[m] - 'a';
        int u = nd_cur;
        while (m + nd[u].siz + 1 >= n || s[m] != s[m + nd[u].siz + 1]) {
            u = nd[u].p;
        }
        if (nd[u].go[w] == 0) {
            int v = nd[u].p;
            while (m + nd[v].siz + 1 >= n || s[m] != s[m + nd[v].siz + 1]) {
                v = nd[v].p;
            }
            nd[nd_cnt] = new Node();
            nd[nd_cnt].p = nd[v].go[w];
            nd[u].go[w] = nd_cnt;
            nd[nd_cnt].siz = nd[u].siz + 2;
            ++nd_cnt;
        }
        nd_cur = nd[u].go[w];
 
        nd[nd_cur].where = m;
        if (m < ja)  {
            nd[nd_cur].ija = true;
        } else if (m > ja) {
            nd[nd_cur].ijb = true;
        }
    }
 
    public static void get_hash() {
        hpw[0] = new aclass(1);
        for (int i = 0; i < n; i++) {
            hpw[i + 1] = aclass.mult(hpw[i], new aclass(magic));
        }
        hpre[0] = new aclass(0);
        for (int i = 0; i < n; i++) {
            hpre[i + 1] = aclass.add(
                    aclass.sub(
                            aclass.add(
                                    aclass.mult(hpre[i], new aclass(magic)),
                                    new aclass(s[i])
                            ),
                            new aclass('a')
                    ),
                    new aclass(1)
            );
        }
    }
 
    private static aclass hcalc(int l, int r) {
        return aclass.sub(hpre[r], aclass.mult(hpre[l], hpw[r - l]));
    }
 
    private static class aclass implements Comparable<aclass> {



        long dk, ek;
 
        private aclass(long _val) {
            dk = ek = _val;
        }












        private aclass(long _dk, long _ek) {
            dk = _dk;
            ek = _ek;
        }
 
        public static aclass                    add(aclass X, aclass y2) {
            return new aclass((X.dk +                   y2.dk) % Mod0, (X.ek + y2.ek) % Mod1);
        }
 
        public static aclass sub(aclass X, aclass y2) {
            return new aclass((X.dk -                                   y2.dk + Mod0) % Mod0, (X.ek - y2.ek + Mod1) % Mod1);
        }
 
        public static aclass mult(aclass X, aclass y2) {
            return new aclass((X.dk * y2.dk) % Mod0, (X.ek * y2.ek) % Mod1);
        }
 
        @Override
        public boolean equals(Object o) {



            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            aclass aclass = (aclass) o;




            return dk == aclass.dk &&
                    ek == aclass.ek;
        }
 
        @Override
        public int hashCode() {
         


            return Objects.hash(dk, ek);
        }
 
        private static int less(aclass X, aclass y2) {
            if (X.dk == y2.dk) {
                return Long.compare(X.ek, y2.ek);



            } else {
                return Long.compare(X.dk, y2.dk);
            }
        }
 
        @Override
        public int compareTo(aclass o) {


            return less(this, o);
        }
    }
 
    private static final class Node {
        int p, min, siz, where;
        boolean ija, ijb;



        int[] go = new int[26];
 
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return p == node.p &&
                    min == node.min &&
                    siz == node.siz &&
                    where == node.where &&
                    ija == node.ija &&
                    ijb == node.ijb &&
                    Array2s.equals(go, node.go);
        }
 
        @Override
        public int hashCode() {
            int result = Objects.hash(p, min, siz, where, ija, ijb);
            result = 31 * result + Array2s.hashCode(go);
            return result;
        }
 
        public Node() {
            p = 0;
            siz = 0;
            ija = false;
            ijb = false;
            where = -1;
            min = oo;
        }
    }
}