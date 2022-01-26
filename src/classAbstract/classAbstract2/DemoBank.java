package classAbstact.classAbstract2;

public class DemoBank {
    /**
     * Create an abstract class 'Bank' with an abstract method 'getBalance'.
     * $100, $150 and $200 are deposited in banks A, B and C respectively.
     * 'BankA', 'BankB' and 'BankC' are subclasses of class 'Bank',each having a method named 'getBalance'.
     * Call this method by creating an object of each of the three classes.
     */
    public static void main(String[] args) {
        BankA a = new BankA(100);
        a.getBalanced();
        BankB  b = new BankB(150);
        b.getBalanced();
        BankC c = new BankC(200);
        c.getBalanced();
    }
}
