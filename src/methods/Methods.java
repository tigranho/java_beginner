package methods;

public class Methods {
    public static void main(String[] args) {
        int[] array = {14, 7, 8, 529, 636, 8};
        int[] array1 = {14, 7, 785, 9, 62, 85};

        Methods m = new Methods();
        System.out.println(" Returns the largest element ");
        m.returnLargest(45, 70);
        System.out.println(m.returnLargest(45, 70));
        System.out.println();

        System.out.println(" Returns Digit ");
        m.returnDigits(877);
        System.out.println(m.returnDigits(877));
        System.out.println();
        System.out.println("Calculate the sum of digits");
        System.out.println(m.sumNumbers(200));
        m.sumDigits(7598412);
        System.out.println(" The element that meets in array ");
        m.metInArray(8, array);
        System.out.println();
        System.out.println(" The largest element of an array ");
        System.out.println(m.largestElement(array));
        System.out.println();
        System.out.println(" Common elements of 2 declared arrays ");
        m.printAllArrays(array1, array);
        System.out.println();
        System.out.println(" Reverse numbers ");
        System.out.println(m.returnReverseNumber(2254));
        System.out.println(" Factorial ");
        m.factorial(7);
        System.out.println();
        System.out.println(" Fibonacci ");
        System.out.println();
        System.out.println(m.recursiaFibonachi(6));
    }

    /**
     * Get two integers and  returns the largest of them
     *
     * @param a - the first integer that gets for comparing to another variable
     * @param l - the second  integer that gets for comparing to another variable
     * @return int - it is the largest element that will be returned
     */
    public int returnLargest(int a, int l) {

        if (a > l) {
            return a;
        }
        return l;
    }

    /**
     * Returns the first two digits of a given number
     * if the number is one-digit  returns 0.
     *
     * @param l- it is the given number
     * @return int -  the given number will return the first two digits
     */
    public int returnDigits(int l) {

        while (l > 10) {
            if (l < 100) {
                return l;
            }
            l /= 10;
        }
        return 0;
    }

    /**
     * Returns the sum of the 1- N numbers.
     *
     * @param n - the given numbers from 1 to N
     * @return int will return the sum of the given numbers
     */
    public int sumNumbers(int n) {

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += n;
        }
        return sum;
    }

    /**
     * Gets a 7-digit integer number and
     * calculate the sum of the digits of that number ․
     *
     * @param num -it's the 7-digit integer number
     */
    public void sumDigits(int num) {

        int sum = 0;
        int temp;
        while (num > 0) {
            temp = num % 10;
            sum = sum + temp;
            num = num / 10;
        }
        System.out.println(sum);
    }

    /**
     * Calculate how many times
     * a given number i was met in an array.
     *
     * @param a     - a given number that will be met in an array
     * @param array - an array where is our i element
     */
    public void metInArray(int a, int[] array) {

        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if (a == array[i]) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * Returns the index of the largest element in an array.
     *
     * @param array - given array, from where we will find the largest one
     * @return array - it returns our the  largest element of an array
     */
    public int largestElement(int[] array) {

        int b = 0;
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
                b = i;

            }

        }
        return b;
    }

    /**
     * Finds the common elements of 2 declared arrays:
     * build a new array from them, print all 3 arrays.
     *
     * @param array1 - compare with an array2, finds the common elements
     * @param array2 -  compare with an array1, finds the common elements
     */
    public void printAllArrays(int[] array1, int[] array2) {


        int[] array3 = new int[array1.length];
        int ankap = 0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]) {

                    array3[ankap] = array2[j];

                    System.out.println(array3[ankap]);
                    ankap++;
                    break;
                }
            }
            System.out.println(array2[i]);
            System.out.println(array1[i]);

        }
    }

    /**
     * Returns reverse number. Example: 251 -> 152:
     *
     * @param num - given number that will be reversed
     * @return int - will return the given number's reverse form
     */
    public int returnReverseNumber(int num) {

        int temp = 0;
        while (num > 0) {
            temp += num % 10;
            num /= 10; //1*10+5
            if (num != 0) {
                temp *= 10;
            }
        }
        return temp;
    }

    /**
     * Write a function that calculates the factorial of a number. Example 5! = (5 * 4 * 3 * 2 * 1)
     *
     * @param factorial - given number the method will calculate
     * that number's factorial like that 2*1,3*2,4*6,5*24 = 120
     */
    public void factorial(int factorial) {
        int sum = 1;
        for (int i = 2; i <= factorial; i++) {
            sum *= i; //2*1,3*2,4*6,5*24 = 120
        }
        System.out.println(sum);

    }

    /**
     * Fibonacci is the sequence of numbers where the first two numbers are 0 and 1,
     * with each subsequent number being defined as the sum of the previous two numbers in the sequence.
     * The Fibonacci sequence looks like this: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233 and so on.
     *
     * @param index - gets the index as parameter
     * @return - returns Fibonacci sequence in reverse order
     */

    public int recursiaFibonachi(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Negative index is not allowed");
        }
        if (index == 0 || index == 1) {
            return index; // 0,1,1,2,3,5 ,8,13
        } else if (index == 2 || index == 3) {
            return 1;
        }
        return recursiaFibonachi(index - 1) + recursiaFibonachi(index - 2);
    }


}






