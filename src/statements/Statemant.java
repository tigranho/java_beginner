package statements;

import java.util.Scanner;

public class Statemant {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Statemant s = new Statemant();

        s.printVariables();
        s.printTheFollowings();
        s.multiplyVariables(5, 4);
        System.out.println();
        System.out.println(" Circle Coordinates ");
        s.circleCoordinates(scanner.nextInt(), scanner.nextInt());
        System.out.println();
        s.checksInteger(45);
        System.out.println(" Positive and negative ");
        System.out.println();
        s.positiveNegative(4);
        System.out.println(" Greater and smaller ");
        System.out.println();
        s.greaterSmaller(12);
        System.out.println(" Division number ");
        System.out.println();
        s.divide(45);
        System.out.println();
        s.max(4, 45, 52);
        System.out.println();
        System.out.println(" Seven number ");
        s.numberIsSeven(73);
        System.out.println(" Leap Year ");
        s.leapYear(1980);
    }

    /**
     * Print the following in the console ․
     * a & b, a ^ b, a | b, ~a, true || (a < 4), (b >= 6) || (++a <= 7);, (a<b)?a:b, b^b
     */
    public void printVariables() {
        int a = 8;
        int b = 3;
        System.out.println(a & b);
        System.out.println(a ^ b);
        System.out.println(a | b);
        System.out.println(~a);
        System.out.println(true || (a < 4));
        System.out.println((b >= 6) || (++a <= 7));
        System.out.println(Math.min(a, b));
        System.out.println(b ^ b);

    }

    /**
     * Print the following in the console
     */
    public void printTheFollowings() {

        System.out.println(10 << 2);
        System.out.println(-10 << 3);
        System.out.println(20 >> 2);
        System.out.println(15 >> 3);
    }

    /**
     * Multiply the given variable by 8 and 16 without using the * sign.
     *
     * @param a - given variable will be multiple with shift operator
     * @param e - given variable will be multiple with shift operator too
     */
    public void multiplyVariables(int a, int e) {

        a <<= 3;
        e <<= 4;
        System.out.printf("a = %d e = %d", a, e);
    }

    /**
     * Checks whether a point with (x, y) coordinates is in a circle K (0,5).
     * just check (x, y) the numbers are in the [-5 5] range or not
     *
     * @param x - Circle coordinate
     * @param y - Circle coordinate
     */
    public void circleCoordinates(int x, int y) {

        if (x > -5 && x < 5 && y > -5 && y < 5) {
            System.out.println("True");
        } else {
            System.out.println(" False");
        }
    }

    /**
     * Checks a given x integer is even or odd.
     *
     * @param n - it is our integer number
     */
    public void checksInteger(int n) {

        if (n % 2 == 0) {
            System.out.println("number" + n + " is even number");
        } else {
            System.out.println(n);
        }
    }

    /**
     * Checks the given x number is positive or negative.
     *
     * @param d - the method will check weather is positive or negative
     */
    public void positiveNegative(int d) {

        if (d > 0) {
            System.out.println(d + " is a positive number");
        } else {
            System.out.println(d + " is a negative number");
        }
    }

    /**
     * Checks the given number x, or less than 8 and greater than 5
     * or smaller than 20 and larger than 15 ․
     *
     * @param x - it's the given number, we will check the problem  requirement
     */
    public void greaterSmaller(int x) {

        if ((x < 8 && x > 5) || (x < 20 && x > 15)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    /**
     * Checks the given integer can be divided by 5 and 7 without remainder
     *
     * @param g - given number will be divided without remainder
     */
    public void divide(int g) {

        if ((g % 5 == 0) && (g % 7 == 0)) {
            System.out.println(g);
        }
    }

    /**
     * Checks that the second digit of a
     * given three-digit number is 7 or not. 974 -> true, 846 -> false:
     *
     * @param num- checking number
     */
    public void numberIsSeven(int num) {


        if (((num - (num % 10)) / 10) % 10 == 7) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    /**
     * Finds Max of the given 3 numbers.
     *
     * @param x - we must find max of these  three numbers
     * @param f -this
     * @param p - and this
     */
    public void max(int x, int f, int p) {
        if ((x >= f) && (x >= p)) {
            System.out.println(x + " is the maximum number.");
        } else if ((f >= x) && (f >= p)) {
            System.out.println(f + "is the maximum number.");
        } else {
            System.out.println(p + " is the maximum number.");
        }
    }

    /**
     * Checks if a given year is Leap year or not
     *
     * @param y - it is our leap year point
     */
    public void leapYear(int y) {

        if ((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0)) {
            System.out.println(y + " is a leap year");
        } else {
            System.out.println(y + " a leap year");
        }
    }
}
