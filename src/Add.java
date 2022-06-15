import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Tigran
 */
public class Add {

    public static void main(String[] args) {
        Stream.generate(() -> "Elsa")
                
                .limit(2)
                .forEach(System.out::println);




    }

}
