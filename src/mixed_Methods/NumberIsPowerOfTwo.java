package mixed_Methods;

public class NumberIsPowerOfTwo {
    public static void main(String[] args) {
        NumberIsPowerOfTwo num = new NumberIsPowerOfTwo();
        System.out.println(num.numberIsPowerOfTwo(789));
        num.StringCastInt("i4g4j2");
    }

    public boolean numberIsPowerOfTwo(int number) {

        return number % 2 == 0;
    }

    public void StringCastInt(String word) {

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);
            switch (ch) {
                case '0':
                    System.out.print(0);
                    break;
                case '1':
                    System.out.print(1);
                    break;
                case '2':
                    System.out.print(2);
                    break;
                case '3':
                    System.out.print(3);
                    break;
                case '4':
                    System.out.print(4);
                    break;
                case '5':
                    System.out.print(5);
                    break;
                case '6':
                    System.out.print(6);
                    break;
                case '7':
                    System.out.print(7);
                    break;
                case '8':
                    System.out.print(8);
                    break;
                case '9':
                    System.out.print(9);
                    break;
                //  default:
                //    System.out.print(" Number does no exist ");

            }


        }
    }
}

