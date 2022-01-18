package homework_classes;

public class Bycicle {
    String name;
    long year;
    String colour;
    float speed;

    public void start () {

    }
    public void stop(){

    }
    public void setSpeed(int i){
        float f = 7f;
        if( i < 30) {
            speed = f;
        }

    }
    public String toString(){

        return "\n" + name +
               "\n" + year +
                "\n" + colour;
    }
    public static void main(String[] args) {
        Bycicle b = new Bycicle();
        b.name = "Rambo";
        b.year = 2020;
        b.colour = "black";
        b.start();
        b.stop();
        b.setSpeed(20);
        b.start();
        b.stop();
        System.out.println(b);

        }


    }
