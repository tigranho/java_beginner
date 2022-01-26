package mixed_Methods;

public class NumberIsPowerOfTwo {
    public static void main(String[] args) {
        NumberIsPowerOfTwo num = new NumberIsPowerOfTwo();
        System.out.println(num.numberIsPowerOfTwo(789));
        num.StringCastInt("12346");
    }

    /**
     * If number is power of two return true, else return false.
     *
     * @param number - gets number as parameter
     * @return boolean
     */
    public boolean numberIsPowerOfTwo(int number) {

        return number % 2 == 0;
    }

    /**
     * Gets String, then cast it to int, if you give letter instead of number it will throw exception
     *
     * @param word - gets string as parameter
     */
    public void StringCastInt(String word) {
        int temp = 0;
        int number = 0;
        for (int i = 0; i < word.length(); i++) {

            switch (word.charAt(i)) {
                case '0':
                    temp = 0;
                    break;
                case '1':
                    temp = 1;
                    break;
                case '2':
                    temp = 2;
                    break;
                case '3':
                    temp = 3;
                    break;
                case '4':
                    temp = 4;
                    break;
                case '5':
                    temp = 5;
                    break;
                case '6':
                    temp = 6;
                    break;
                case '7':
                    temp = 7;
                    break;
                case '8':
                    temp = 8;
                    break;
                case '9':
                    temp = 9;
                    break;

                default:
                    throw new IllegalArgumentException("Not allowed to write letters");
            }
            number += temp;
            if (i != word.length() - 1) {
                number *= 10;
            }
        }
        System.out.println(number);
    }
}

