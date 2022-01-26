package classAbstact.classAbstract2;

public class BankC extends Bank {
    int deposit;

    BankC(int deposit) {
        this.deposit = deposit;

    }

    @Override
    void getBalanced() {
        System.out.println(" BankC " + " deposit is 200$ ");
    }
}
