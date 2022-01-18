package tigran_aren;

import java.util.Arrays;

public class ArraysExamples {

    /**
     * sum of proper elements of array1 & array2
     */
    public static void exercise7() {
        int[] array1 = {4,-8,5};
        int[] array2 = {7,8,6};
        int[] array3 = new int[3];

        for (int i = 0; i < array3.length; i++) {
            array3[i] = array1[i]+array2[i];
            System.out.print(array3[i] + "\t");
        }
    }

    /**
     *
     * @param array1
     * @param array2
     * @return sum of proper elements of array1 & array2 (with parameters)
     */
    public static int[] exercise7(int[] array1, int[] array2) {
        int[] array3 = new int[array1.length];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = array1[i] + array2[i];
        }
        return array3;
    }

    /**
     *
     * @param array
     * @return
     */
    public static int[] exercise9(int[] array) {
        int[] newArray = new int[array.length];
        int j = 0;
        for (int k : array) {
            if (k % 2 == 1 && k >= 100 && k <= 999 && k % 3 == 0) {
                newArray[j++] = k;
            }
        }
        return Arrays.copyOf(newArray, j);
    }

    public static void main(String[] args) {
        exercise7();
        int[] array = exercise7(new int[]{1, 2, 3}, new int[]{3, 4, 5});
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(exercise9(new int[]{1, 333, 3})));
    }
}
