package loops;

public class Loops {
    public static void main(String[] args) {
        Loops l = new Loops();
        System.out.println(" Add the missing ");
        l.add();
        System.out.println(" Find errors ");
        l.findErrors();
        System.out.println();
        System.out.println(" Numbers ");
        l.printNumbers(20);
        System.out.println();
        System.out.println(" First digit ");
        l.firstDigit(245);
        System.out.println();
        System.out.println(" Expression ");
        l.printExpression();
        System.out.println();
        System.out.println(" Square ");
        l.square();
        System.out.println();
        System.out.println(" Sum ");
        l.sum(55);
        System.out.println();
        System.out.println(" Even numbers ");
        l.evenNumbers(35);
        System.out.println();
        System.out.println(" The largest and the smallest ");
        l.averageOfNumbers(15);
        System.out.println();
        System.out.println(" Average ");
        l.average(5577);
    }

    /**
     * What to add ___ in the missing position to print 8
     */
    public void add() {

        int x = 0;
        while (x++ < 10) {
            if (x == 8) {
                System.out.println(x);
                break;
            }
        }
    }

    /**
     * Find errors and correct them
     */
    public void findErrors() {

        int x = 2;
        //  int y = 5;
        while (x < 10) {
            x++;
            System.out.println(x);
        }

        int ok = 15;
        while (ok > 10) {
            ok--;// x = x--;
        }
        System.out.println(ok);
    }

    /**
     * Print numbers from 1 to N in Console
     *
     * @param num - Gets numbers 1 to N and prints.
     */
    public void printNumbers(int num) {

        for (int i = 1; i <= num; i++) {
            System.out.println(i);
        }

    }

    /**
     * Prints the first digit of a given number.
     *
     * @param digit - is the given number, needs to print first number of digit
     */
    public void firstDigit(int digit) {

        while (digit > 10) {
            digit /= 10;
        }
        System.out.println(digit);
    }

    /**
     * Given int x = 1, int y = 0. As long as x <5 and
     * y <10 print the expression x + y.
     */
    public void printExpression() {
        int x = 1;
        int y = 0;
        while (x < 5 && y < 10) {
            x++;
            y++;
            System.out.println(x + y);
        }
    }

    /**
     * Prints a square in Console, use the * character.
     */
    public void square() {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }
    }

    /**
     * Gets the number N from the Console and
     * calculates [1-N] the sum of all 5 divisible numbers
     *
     * @param num - Gets the number N from the Console
     */
    public void sum(int num) {

        int sum = 0;
        for (int i = 0; i < num; i++) {
            if (i % 5 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        System.out.println(sum);
    }

    /**
     * Calculates all two-digit even numbers of [1-N].
     *
     * @param num - sum of two digit even numbers
     */
    public void evenNumbers(int num) {

        int sum = 1;
        for (int i = 1; i < num; i++) {
            if (i % 2 == 0 && i >= 10) {
                sum = sum + i;
            }
        }
        System.out.println(sum);
    }

    /**
     * Finds the largest and the smallest elements
     * in [1-N] sequential numbers and calculates their average.
     *
     * @param num - 1 to N numbers
     */
    public void averageOfNumbers(int num) {

        int sum = 0;

        for (int i = 1; i <= num; i++) {

            sum += i;

        }

        System.out.println(sum / num);
    }

    /**
     * Receives 5 digit-number from the Console and calculate their average.
     *
     * @param digit - getting this number from the Console
     */
    public void average(int digit) {

        int sum = 0;

        for (int i = 1; i < digit; i++) {
           int temp = digit % 10;
            sum += temp;
            digit /= 10;
        }
        System.out.println(sum);
    }
}




