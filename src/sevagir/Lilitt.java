package sevagir;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Lilitt {
    public static void snore() throws Exception {
        try {
            String sheep[] = new String[3];
            System.out.print(sheep[3]);
        } catch (RuntimeException e) {
            System.out.print("Awake!");
        } finally {
            throw new Exception(); // x1
        }
    }
    public static void main(String... sheep) throws Exception { // x2
        new Lilitt().snore(); // x3
    }
}