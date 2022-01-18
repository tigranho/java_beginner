package homework_classes;

/**
 * Define the Helicopter class that has
 * fields - (phoneBook.model, year, color, maxSpeed, currentSpeed, engineState (true or false))
 * methods - (startEngine (), stopEngine (), setSpeed () և height ())
 * Create a white Airbus 2019 helicopter. Bring the engine to a working mode,
 * to give  an initial speed of 5 km / h. Increase the speed to 500 km / h,
 * then stop the helicopter.Increase speed by +30 , Reduce speed by -20
 * Limit that the helicopter can not stop if its speed is not 0.
 * The maximum speed of the helicopter is 600 km / h.
 */
public class Helicopter {
    String model;
    int year;
    String colour;
    int maxSpeed;
    int currentSpeed;
    boolean engineState;

    Helicopter(String model, int year, String colour, int maxSpeed, int currentSpeed, boolean engineState) {
        this.model = model;
        this.year = year;
        this.colour = colour;
        this.maxSpeed = maxSpeed;
        this.currentSpeed = currentSpeed;

    }

    public void startEngine() {

        engineState = true;

    }

    public void stopEngine() {

            engineState = false;
        }


    public void setSpeed(int speed) {
        currentSpeed = speed;


        System.out.println("Have a nice trip");
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void height() {
    }

    public static void main(String[] args) {
        Helicopter h = new Helicopter("Airbus", 2019, "white", 600, 5, true);

        h.startEngine();
        h.setSpeed(5);
        h.setSpeed(500);
        h.currentSpeed += 30;
        h.currentSpeed -= 20;

        h.stopEngine();

      //  h.currentSpeed > 600;

    }
}
