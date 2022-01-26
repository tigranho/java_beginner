package arrays;
import java.util.Scanner;

public class TwoDimensionalArray {
    public static void main(String[] args) {


        //  int[][] array = {{14, 7, 45, 36}, {8, 59, 63, 8}};

        TwoDimensionalArray ta = new TwoDimensionalArray();
        ta.array();
        /**
         * Gets a 2dimensional  arrays,
         * prints the main elements of the  diagonal
         * @param a - Gets a 2dimensional  arrays from scanner and prints the main elements of the  diagonal
         */
    }

    public void array() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of square matrix ");
        int row = sc.nextInt();
        int col = sc.nextInt();

            int[][] a = new int[row][col];
            System.out.println(" Enter a matrix elements");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    a[i][j] = sc.nextInt();
                    if (row == col){

                    }
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (i == j) {
                        System.out.println(a[i][j]);
                    }

                }
            }
        }
    }
