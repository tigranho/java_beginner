package homework_classes;

/**
 * Define the Car class that has
 * fields (phoneBook.model, color, currentSpeed (default value 0), isEngineStart (true or false))
 * methods (stopEngine (), startEngine ())
 * Create Mercedes C203 black and S505 white cars ․
 * Check if the C203 car engine is working then print complete information about the car ․
 * Check if the S505 is in progress or not. Print complete information about the car.
 * (The car can move if the engine is working and the speed is 0)
 */

public class Car {
    String model;
    String colour;
    int currentSpeed;
    boolean isEngineStart;

    Car() {

    }

    public void stopEngine() {
        System.out.println(model);
        System.out.println(colour);
        System.out.println(currentSpeed);
        if (currentSpeed == 0) {

            System.out.println("stop");
        }

    }

    public void startEngine() {
        if (isEngineStart) {
            System.out.println(model);
            System.out.println(colour);
            System.out.println(currentSpeed);
        } else {

            stopEngine();

        }
    }

    public static void main(String[] args) {

        Car c = new Car();
        c.model = "Mercedes C203";
        c.colour = "Black";
        c.currentSpeed = 120;
        c.isEngineStart = true;
        c.startEngine();


        Car b = new Car();
        b.model = "Mercedes S505";
        b.colour = "White";
        b.currentSpeed = 0;
        b.isEngineStart = false;
        b.startEngine();
    }
}