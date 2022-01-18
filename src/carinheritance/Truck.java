package carinheritance;


public class Truck extends Car {
    private int capacity;

    Truck(int passengerCount, char engineType, int capacity) {
        super(passengerCount, engineType);
        setCapacity(capacity);

    }

    public void engineType() {
        int a = getCapacity() / 2;
        int b = getPassengerCount() * 5;
        System.out.println(a + " " + b);
    }


    public int getCapacity() {

        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public static void main(String[] args) {

        Truck g = new Truck(3, 'A', 30);
        System.out.println(g.getPassengerCount());
        System.out.println(g.getCapacity());
        g.engineType();
    }
}
