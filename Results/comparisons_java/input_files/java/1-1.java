import java.util.*;
 
public class PalindromicMagicV2 {
 
    private static final int oo = 0x3f3f3f3f;
    private static final int maxn = 400100;
    private static final long Mod0 = (long) 1e9 + 7, Mod1 = (long) 1e9 + 9;
    private static final long magic = 113;
 
    private static char[] s;
    private static int nd_cur = 0;
    private static Node[] nd = new Node[maxn + 5];
    private static int n;
    private static int nd_cnt;
    private static int n0;
    private static hd[] hpw = new hd[maxn + 5];
    private static hd[] hpre = new hd[maxn + 5];
    private static Set<hd> pal0 = new TreeSet<>(), pal1 = new TreeSet<>();
    private static Map<hd, Long> num0 = new HashMap<>(), num1 = new HashMap<>();
 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
 
            System.out.println(palindromesCount(scanner.next(), scanner.next()));
//            System.out.println(palindromesCount(generate(200000, 'a'), generate(200000, 'b')));
//            System.out.println(palindromesCount("aa", "aba"));
        }
    }
 
    private static String generate(int len, char c) {
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
                extend(i);
            }
        }
        get_hash();
 
        long cnt0 = 0;
        long cnt1 = 0;
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].in0) {
                cnt0 = cnt0 + 1;
                pal0.add(hcalc(nd[i].where, nd[i].where + nd[i].len));
            }
            if (nd[i].in1) {
                cnt1 = cnt1 + 1;
                pal1.add(hcalc(nd[i].where, nd[i].where + nd[i].len));
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
                hd head = hcalc(nd[i].where, nd[i].where + seg);
                hd tail = hcalc(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len);
                if (nd[i].in0) {
                    num0.putIfAbsent(head, 0L);
                    if (num0.get(head) < cnt) {
                        num0.put(head, cnt);
                    }
                    if (pal1.contains(hd.add(hd.mult(tail, hpw[res]), hcalc(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len - seg + res)))) {
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
                    if (pal0.contains(hd.add(hd.mult(tail, hpw[res]), hcalc(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len - seg + res)))) {
                        num0.putIfAbsent(tail, 0L);
                        if (num0.get(tail) < 1) {
                            num0.put(tail, 1L);
                        }
                    }
                }
            }
        }
        for (Map.Entry<hd, Long> u : num0.entrySet()) {
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
 
    private static void extend(int m) {
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
        hpw[0] = new hd(1);
        for (int i = 0; i < n; i++) {
            hpw[i + 1] = hd.mult(hpw[i], new hd(magic));
        }
        hpre[0] = new hd(0);
        for (int i = 0; i < n; i++) {
            hpre[i + 1] = hd.add(
                    hd.sub(
                            hd.add(
                                    hd.mult(hpre[i], new hd(magic)),
                                    new hd(s[i])
                            ),
                            new hd('a')
                    ),
                    new hd(1)
            );
        }
    }
 
    private static hd hcalc(int l, int r) {
        return hd.sub(hpre[r], hd.mult(hpre[l], hpw[r - l]));
    }
 
    private static class hd implements Comparable<hd> {
        long a0, a1;
 
        private hd(long _val) {
            a0 = a1 = _val;
        }
        private hd(long _a0, long _a1) {
            a0 = _a0;
            a1 = _a1;
        }
 
        private static hd add(hd x, hd y) {
            return new hd((x.a0 + y.a0) % Mod0, (x.a1 + y.a1) % Mod1);
        }
 
        private static hd sub(hd x, hd y) {
            return new hd((x.a0 - y.a0 + Mod0) % Mod0, (x.a1 - y.a1 + Mod1) % Mod1);
        }
 
        private static hd mult(hd x, hd y) {
            return new hd((x.a0 * y.a0) % Mod0, (x.a1 * y.a1) % Mod1);
        }
 
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            hd hd = (hd) o;
            return a0 == hd.a0 &&
                    a1 == hd.a1;
        }
 
        @Override
        public int hashCode() {
            return Objects.hash(a0, a1);
        }
 
        private static int less(hd x, hd y) {
            if (x.a0 == y.a0) {
                return Long.compare(x.a1, y.a1);
            } else {
                return Long.compare(x.a0, y.a0);
            }
        }
 
        @Override
        public int compareTo(hd o) {
            return less(this, o);
        }
    }
 
    private static final class Node {
        int p, min, len, where;
        boolean in0, in1;
        int[] go = new int[26];
 
        @Override
        public boolean equals(Object o) {
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
 
        @Override
        public int hashCode() {
            int result = Objects.hash(p, min, len, where, in0, in1);
            result = 31 * result + Arrays.hashCode(go);
            return result;
        }
 
        private Node() {
            p = 0;
            len = 0;
            in0 = false;
            in1 = false;
            where = -1;
            min = oo;
        }
    }
}