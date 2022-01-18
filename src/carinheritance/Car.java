package carinheritance;

public class Car {
    /**
     * Given the Car class which has the following fields:
     * passengerCount //   it could not be less than 2
     * engineType // expected values  one letter (‘a-z , A-Z’)
     * methods: getter & setter of passengerCount & engineType fields
     * Create a subclass of Truck class. The Truck class has the following fields:
     * capacity  // default value should be 0
     * methods:  getter & setter of capacity field
     * Use super(...) method in the constructor for initializing the fields of the super class.
     */
    private int passengerCount;
    private char engineType;

    Car(int passengerCount, char engineType) {
        setPassengerCount(passengerCount);
        setEngineType(engineType);


    }

    public int getPassengerCount() {

        return passengerCount;
    }

    public char getEngineType() {

        return engineType;
    }

    public void setEngineType(char engineType) {


        if((engineType>='a' && engineType<='z') || (engineType>='A'&& engineType<= 'Z')){
            this.engineType = engineType;
        }

    }

    public void setPassengerCount(int passengerCount) {
        if (passengerCount > 2) {
            this.passengerCount = passengerCount;
        }else{
            System.out.println("Please set passenger count more than two");
        }
    }
}
