package inheritance;

public class Child extends Parent {
    @Override
    public void showMessage() {


    }

    public static void main(String[] args) {
        Child ch = new Child();
        ch.showMessage();
        Parent c = new Parent();
        c.showMessage();
        System.out.println("Hello Child class");
    }
}
