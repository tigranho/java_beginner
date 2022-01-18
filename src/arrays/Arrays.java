package arrays;

import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {
        int[] array = {14, 5, 63, 2, 14, 78, 96};
        int[] arrayTwo = {4, 74, 63, 4, 88, 5, 27};
        Arrays a = new Arrays();
        System.out.println("Print Positive Elements");
        a.printPositiveElements(array);
        System.out.println();
        System.out.println("Reverse Array");
        a.reverseArray(array);
        System.out.println();
        System.out.println("Max & Min elements");
        a.maxMin(array);
        System.out.println();
        System.out.println("Three Digit Number");
        a.threeDigit();
        System.out.println();
        System.out.println("Gets the size of an array");
        a.getArrays();
        System.out.println();
        System.out.println("Sum Elements");
        a.sumElements(array, arrayTwo);
        System.out.println();
        System.out.println("Find Elements");
        a.findElement(14, array);
        System.out.println();
        System.out.println("Three Digit Array");
        a.threeDigitArray(array);

    }

    /**
     * Print the positive elements of an array ․
     * @param array - Gets positive elements of an array and print them.
     */
    public void printPositiveElements(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] > 0){
                System.out.println(array[i] + " ");
            }

        }

    }
    /**
     * Print the elements of an array in reverse order, For example: (257 -> 752) ․
     * @param array - Gets array, then print it in a reverse order,
     */
    public void reverseArray(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
    }
    /**
     * Print Max & Min elements of an array
     * @param myArray - Gets array and print max and min values
     */
    public void maxMin(int[] myArray) {
        int max = myArray[0];
        int min = myArray[0];
        for (int i = 1; i < myArray.length; i++) {
            if (max < myArray[i]) {
                max = myArray[i];
            }
            if (min > myArray[i]) {
                min = myArray[i];
            }
        }
        System.out.println(" max " + max + " min " + min);
    }
    /**
     * Imports from console of 10 elements with three-digit numbers array ․
     */
    public void threeDigit() {

        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            int a = scanner.nextInt();
            if (a > 99 && a < 1000) {
                array[i] = a;
                System.out.println(array[i]);
            } else {
                System.out.println("Please enter three digit number");
            }
        }
    }

    /**
     * Gets the size of an array from the Console,
     * initializes the elements of the array & move to another array.
     */
    public void getArrays() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int[] array1 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
            array1[i] = array[i];
            System.out.println(array1[i]);
        }
    }

    /**
     * Given arrays of the same size,
     * adds up the corresponding elements of the  array and writes it in the third one
     * @param arrayOne - gets the same array elements and concatenated with arrayTwo,then writes it in the third one
     * @param arrayTwo - does the same as arrayOne
     */
    public void sumElements(int[] arrayOne, int[] arrayTwo) {

        int[] arrayThree = new int[arrayOne.length];
        for (int i = 0; i < arrayThree.length; i++) {
            arrayThree[i] = arrayOne[i] + arrayTwo[i];
            System.out.println(arrayOne[i] + " + " + arrayTwo[i] + " = " + arrayThree[i]);
        }
    }

    /**
     * Calculates how many times number k
     * can be found in the elements of the array
     * @param k - number k is an element of an array, we need to find out how many times it meets in an array
     * @param array - gets an array where number k is repeated several times
     */
    public void findElement(int k, int[] array) {
        int count = 0;
        for (int j : array) {
            if (k == j) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * Creates the third array
     * by three-digit odd numbers of the first array and divider elements of number three
     * @param array - gets three-digit odd numbers of the first array and divider elements of number three
     */
    public void threeDigitArray (int[] array) {

        int[] newArray = new int[array.length];
        int j = 0;
        for (int k : array) {
            if (k % 2 == 1 && k >= 100 && k <= 999 && k % 3 == 0) {
                newArray[j] = k;
                System.out.println(newArray[j]);
                j++;
            }
        }
        if (j == 0)
        System.out.println("There is no such kind of elements");
    }


}





