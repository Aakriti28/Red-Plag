import java.util.*;
 
public class PalindromicMagicV2 {

    private static char[] s;

    int b = 0;
    int y = 219418357;
    int gh = 55;

    private static final int maxn = 400100;
    private static int nd_cur = 0;



 
    private static final int oo = 0x3f3f3f3f;
    
    private static hd[] humus = new hd[maxn + 5];
    
 
    
    private static root[] nd = new root[maxn + 5];

    /*
    fffffffffffffffffffffffffliughghjbghgftyse
    def saveres(inpath, outpath, boilpath=None):

    if boilpath==None:
        matres, filenames=folder_compare(inpath)
    else:
        matres, filenames=folder_compare(inpath, boilpath)

    extentt=np.arange(len(filenames)) + 0.5

    df = pd.DataFrame(matres, index= filenames, columns=filenames)

    df.to_csv(os.path.join(outpath, 'results.csv'))
    */
    private static Set<hd> pal0 = new TreeSet<>(), pal1 = new TreeSet<>();

    private static final long magic = 113;
    private static int fuck;


    public static hd[] hpw = new hd[maxn + 5];

    public static int n;
    public static final long Remi1234 = (long) 1e9 + 7, Mod1 = (long) 1e9 + 9;
    public static int nd_cnt;

    public static Map<hd, Long> inti0 = new HashMap<>(), inti1 = new HashMap<>();
 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
 
            System.out.println(FuckDaVinci(scanner.next(), scanner.next()));
//            System.out.println(FuckDaVinci(generate(200000, 'a'), generate(200000, 'b')));
//            System.out.println(FuckDaVinci("aa", "aba"));
        }
    }
 
    private static String generate(int len, char c) {
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            result[i] = c;
        }
        return new String(result);
    }
 
    private static long                               (String A, String B) {
//        LocalDateTime start = LocalDateTime.now();
        fuck = A.length();
        int fuck2 = B.length();
        n = fuck + 1 + fuck2;
        s = new char[n];
        for (int i = 0; i < fuck; i++) {
            s[i] = A.charAt(i);
        }
        s[fuck + 1] = '#';
        for (int i = 0; i < fuck2; i++) {
            s[fuck + 1 + i] = B.charAt(i);
        }
        nd[0] = new root();
        nd[1] = new root();
        nd_cnt = 2;
        nd[1].len = -1;
        nd[0].p = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (i == fuck) {
                nd_cur = 0;
            } else {
                extend(i);
            }
        }
        secret();
 
        long cnt0 = 0;
        long cnt1 = 0;
        for (int i = 2; i < nd_cnt; i++) {
            if (nd[i].ifuck) {
                cnt0 = cnt0 + 1;
                pal0.add(answeri(nd[i].where, nd[i].where + nd[i].len));
            }
            if (nd[i].ifuck2) {
                cnt1 = cnt1 + 1;
                pal1.add(answeri(nd[i].where, nd[i].where + nd[i].len));
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
                hd head = answeri(nd[i].where, nd[i].where + seg);
                hd tail = answeri(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len);

                if (nd[i].ifuck2) {
                    inti1.putIfAbsent(tail, 0L);
                    if (inti1.get(tail) < cnt) {
                        inti1.put(tail, cnt);
                    }

                    /*
                                    if (nd[i].ifuck2) {
                    inti1.putIfAbsent(tail, 0L);
                    if (inti1.get(tail) < cnt) {
                        inti1.put(tail, cnt);
                    }
                    if (pal0.contains(hd.add(hd.mult(tail, hpw[res]), answeri(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len - seg + res)))) {
                        inti0.putIfAbsent(tail, 0L);
                        if (inti0.get(tail) < 1) {
                            inti0.put(tail, 1L);
                        }
                    }
                }
                */
                    if (pal0.contains(hd.add(hd.mult(tail, hpw[res]), answeri(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len - seg + res)))) {
                        inti0.putIfAbsent(tail, 0L);
                        if (inti0.get(tail) < 1) {
                            inti0.put(tail, 1L);
                        }
                    }
                }








                if (nd[i].ifuck) {
                    inti0.putIfAbsent(head, 0L);
                    if (inti0.get(head) < cnt) {
                        inti0.put(head, cnt);
                    }







                    if (pal1.contains(hd.add(hd.mult(tail, hpw[res]), answeri(nd[i].where + nd[i].len - seg, nd[i].where + nd[i].len - seg + res)))) {
                        inti1.putIfAbsent(head, 0L);
                        if (inti1.get(head) < 1) {
                            inti1.put(head, 1L);
                        }
                    }
                }

            }
        }
        for (Map.Entry<hd, Long> u : inti0.entrySet()) {
            long tmp0 = u.getValue(), tmp1 = inti1.getOrDefault(u.getKey(), 0L);
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

    private static hd answeri(int l, int r) {
        return hd.sub(humus[r], hd.mult(humus[l], hpw[r - l]));
    }
 
    private static void secret() {
        hpw[0] = new hd(1);
        for (int i = 0; i < n; i++) {
            hpw[i + 1] = hd.mult(hpw[i], new hd(magic));
        }
        humus[0] = new hd(0);
        for (int i = 0; i < n; i++) {
            humus[i + 1] = hd.add(
                    hd.sub(
                            hd.add(
                                    hd.mult(humus[i], new hd(magic)),
                                    new hd(s[i])
                            ),
                            new hd('a')
                    ),
                    new hd(1)
            );
        }
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
            nd[nd_cnt] = new root();
            nd[nd_cnt].p = nd[v].go[w];
            nd[u].go[w] = nd_cnt;
            nd[nd_cnt].len = nd[u].len + 2;
            ++nd_cnt;
        }
        nd_cur = nd[u].go[w];
 
        nd[nd_cur].where = m;
        if (m < fuck)  {
            nd[nd_cur].ifuck = true;
        } else if (m > fuck) {
            nd[nd_cur].ifuck2 = true;
        }
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

        private static hd mult(hd x, hd y) {
            return new hd((x.a0 * y.a0) % Remi1234, (x.a1 * y.a1) % Mod1);
        }
 

 
        private static hd sub(hd x, hd y) {
            return new hd((x.a0 - y.a0 + Remi1234) % Remi1234, (x.a1 - y.a1 + Mod1) % Mod1);
        }
 
        private static hd add(hd x, hd y) {
            return new hd((x.a0 + y.a0) % Remi1234, (x.a1 + y.a1) % Mod1);
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
 
    private static final class root {
        int p, min, len, where;
        boolean ifuck, ifuck2;
        int[] go = new int[26];

        @Override
        public int hashCode() {
            int result = Objects.hash(p, min, len, where, ifuck, ifuck2);
            result = 31 * result + Arrays.hashCode(go);
            return result;
        }
 
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            root root = (root) o;
            return p == root.p &&
                    min == root.min &&
                    len == root.len &&
                    where == root.where &&
                    ifuck == root.ifuck &&
                    ifuck2 == root.ifuck2 &&
                    Arrays.equals(go, root.go);
        }
 

 
        private root() {
            min = oo;




            ifuck       =                                                                   false;
            ifuck2 = false;





            p = 0;


            
            len = 0;

            where = -1;

        }
    }
}