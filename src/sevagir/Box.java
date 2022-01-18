package sevagir;

public class Box extends BoxWeight {
    Box(int age, double vol) {
        super(age, vol);
    }

    Box() {

    }


    public static void main(String[] args) {

        BoxWeight w = new BoxWeight(5,5.5);
        Box b = new Box();
        double v;
        v= w.vol;
       b.vol = v; // սուպեր ի փոփոխականները, ընդհանուր են , թույլ է տալիս դրանք յուս անել սաբ ի օբյեկտով
        // կամ արժեք փոխանցել

    }
}
