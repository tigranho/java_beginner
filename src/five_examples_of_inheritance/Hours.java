package five_examples_of_inheritance;

public class Hours extends Animal {
    public static void main(String[] args) {
        Hours  a = new Hours();
        a.test();
        System.out.println(" Animal " + " -> " + "Hourse");

    }

    @Override
    public void test() {

        System.out.println(" I love riding horse ");

    }
}
