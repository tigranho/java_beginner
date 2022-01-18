package string_methods;

import java.util.Scanner;

public class StringMethods {

    public static void main(String[] args) {

        StringMethods sm = new StringMethods();

        Scanner scanner = new Scanner(System.in);
        System.out.println(" Reverse String ");
        sm.reverseString("Armenia");
        System.out.println(" Concatenation ");
        sm.concatString("java", "Developer");
        sm.getResult(scanner.next());
        System.out.println();
        System.out.println(" Capital letters ");
        sm.capitalLetters();
        System.out.println(" Find ա in the text ");
        sm.findAinTheText();
        System.out.println(" Index ");
        sm.findIndex();
        System.out.println(" Remove characters ");
        sm.removeCharacters();
        System.out.println(" Find String ");
        sm.findPrintString();
        System.out.println(" Create string from characters ");
        sm.createString();
    }

    /**
     * Concatinate  two strings using a string. Same with StringBuilder
     *
     * @param s    - Gets a parameter String from the scanner, concat, append  to the next word parameter
     * @param word - Gets a parameter String from the scanner, concat, append  to  the previous s parameter
     */
    public void concatString(String s, String word) {

        String s1 = "java";
        String s2 = s1.concat(" Developer ");
        System.out.println(s2);
        System.out.println();
        StringBuilder str1 = new StringBuilder(s);
        StringBuilder str2 = str1.append(word);
        System.out.println(str2);
    }

    /**
     * Reads a string of 20 characters from the console (Scanner.in).if the line is less than 20, print"*"
     * Example Armenia:
     * Armenia *************
     *
     * @param result - will return multiplication symbol with word
     */
    public void getResult(String result) {
        char[] g = new char[20];

        for (int i = 0; i < g.length; i++) {

            if (i >= result.length()) {
                g[i] = '*';

            } else {
                g[i] = result.charAt(i);
            }
            System.out.print(g[i]);
        }

    }

    /**
     * Reverse the line.  Example “sample” -> “elpmis”
     *
     * @param rev - word or string trat will be reversed
     */
    public void reverseString(String rev) {

        String result = "";
        char ch;
        for (int i = 0; i < rev.length(); i++) {
            ch = rev.charAt(i);
            result = ch + result;

        }

        System.out.println(result);

        StringBuilder հայ = new StringBuilder("Հայաստան");
        հայ.reverse();
        System.out.println(" հայ " + հայ);
    }

    /**
     * Checks how many times the "ա" is found in a given text.
     */
    public void findAinTheText() {
        String Arm = "Հայաստանը թանգարան է բաց երկնքի տակ";
        char arm = 'ա';
        int count = 0;
        for (int i = 0; i < Arm.length(); i++) {
            if (Arm.charAt(i) == arm)
                count++;


        }
        System.out.println(count);
    }

    /**
     * Creates text written in capital letters using String.
     */
    public void capitalLetters() {

        String avo = " Մոնթե Մելքոնյան ";
        String avo1 = avo.toUpperCase();

        System.out.println(avo1);


    }

    /**
     * Remove all "a" characters from a
     * given text using String. Same with StringBuilder.
     */
    public void removeCharacters() {


        String s = "Armenia is museum in an open-air";
        char ak = 'a';
        System.out.println(s.replace(ak, ' '));

        StringBuilder al = new StringBuilder("Հայաստան");
        System.out.println(al.delete(1, 2)); // 1 hat a
    }

    /**
     * finds out in which index the "ժ" symbol was first encountered
     */
    public void findIndex() {

        String heros1 = "Գարեգին Նժդեհ";
        System.out.println(heros1.indexOf('ժ')); //  9


    }

    /**
     * Finds and prints from the beginning of a
     * given string to 10th index, using String.
     */
    public void findPrintString() {

        String str = "Հզոր  հայրենիք"; //Հզոր հայր
        System.out.println(str.substring(0, 10));

    }

    /**
     * Creates a new String object from the char array.
     * expected result will be Մեծ Հայք
     */

    public void createString() {

        char[] ch = {'Մ', 'ե', 'ծ', ' ', 'Հ', 'ա', 'յ', 'ք'};
        String str = new String(ch);
        String str2 = String.valueOf(ch);
        System.out.println(str2);
    }


}

