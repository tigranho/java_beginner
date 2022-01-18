package creatingInterface;

public class MyClass implements A {
    public static void main(String[] args) {
        MyClass m = new MyClass();
        m.meth1();
        m.meth2();
    }
    @Override
    public void meth1() {
        System.out.println(" Java ");

    }

    @Override
    public void meth2() {
        System.out.println(" Developer ");

    }
}
