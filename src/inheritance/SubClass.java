package inheritance;

public class SubClass extends SuperClass {

    public static void main(String[] args) {

        SuperClass sc = new SuperClass();
        sc.message = "Hello SuperClass class";
        SubClass sb = new SubClass();
        sb.message = "Hello SubClass class";
        System.out.println(sc.message);
        System.out.println(sb.message);


    }

}
