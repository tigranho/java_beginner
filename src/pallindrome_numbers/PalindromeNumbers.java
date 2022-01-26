package pallindrome_numbers;

import java.util.Scanner;

public class PalindromeNumbers {
    /**
     * Gets number from scanner, and checked if it is palindrome number or not
     * examples 454, 898,888,5665 ...
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PalindromeNumbers num = new PalindromeNumbers();
        num.palindromeNumbers(scanner.nextInt());


    }

    public void palindromeNumbers(int number) {
        int i;
        int sum = 0;
        int temp = number;
        while (number > 0) {
            i = number % 10; // 4
            sum = (sum * 10) + i; //4
            number = number / 10;
        }
        if (temp == sum) {
            System.out.println("Number is palindrome");
        } else {
            System.out.println("Number is not Palindrome");
        }
    }
}
