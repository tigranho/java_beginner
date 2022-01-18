package operators;

public class Operators {
    /**
     * Created variables (int a = 8, int b = 6), print the following in the console
     */
    public static void main(String[] args) {

        int fa = 8;
        int db = 6;
        System.out.println("a += b " + (fa + db));
        System.out.println("a *= b " + (fa * db));
        System.out.println("a /= b " + (fa / db));
        System.out.println("a - b = " + (fa - db));
        System.out.println("a % b = " + (fa % db));
/**
 * Create 2 variables (double c = 6.4, double d = 0.03), print the following in the console.
 */
        double c = 6.4;
        double d = 0.03;
        System.out.println("c + d = " + (c + d));
        System.out.println("c * d = " + (c * d));
        System.out.println("c / d = " + (c / d));
        System.out.println("c -= d " + (c - d));
        System.out.println("c %= d " + (c % d));
        System.out.println();
        int xx;
        xx = 2 * ((5 + 3) * 4 - 8);
        System.out.println(xx);
        int yy;
        yy = 2 * 5 + 3 * 4 - 8;
        System.out.println(yy);
        System.out.println();
        int e = 10;
        int f = -88;
        System.out.println("e - e = " + (e - e));
        System.out.println("e - f = " + (e - f));
        System.out.println("e += e  " + (e + e));
        System.out.println("e - e + 92 = " + (e - e + 92));

        boolean n1 = true;
        System.out.println(!n1);

        System.out.println(!(!n1));

        System.out.println("e + e++ = " + (e + e++));
        System.out.println("e + (--f) = " + (e + (--f)));

        int x = 3;
        int y = ++x * 5 / x-- + --x;
        System.out.println("x is " + x);
        System.out.println("y is " + y);
        System.out.println();
        int xs = (int) 1.0;
        System.out.println(xs);

        short yt = (short) 1921222;
        System.out.print(yt);

        System.out.print(2147483647 + 1);

        long qy = (x + 3);
        System.out.println(qy);

        boolean yd ;
        boolean xd = (yd = true);
        System.out.println(xd + " " + yd);
        System.out.println();
        int aa = 10;
        int bb = 20;
        System.out.println(aa < bb);
        System.out.println(true);
        System.out.println(aa >= bb);
        System.out.println(aa > bb);
        float yyy = 2.1f;
        System.out.println(yyy);
        byte mx = 5;
        byte my = 10;
        byte z = (byte) (mx + my);
        System.out.println(z);

        short fx = 10;
        short fy = 3;
        short fz = (short) (fx * fy);
        System.out.println(fz);

        long kx = 10L;
        int yl = 5;
        y = (int) (yl * kx);
        System.out.println(y);
        System.out.println();
        int ra = 10;
        int rb = 5;
        int rc = ra;
        ra = rb;
        rb = rc;
        System.out.println(ra + " " + rb);

        int a = 15;
        int b = 5;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a);


    }
}