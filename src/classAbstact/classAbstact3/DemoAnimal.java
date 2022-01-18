package classAbstact.classAbstact3;

public class DemoAnimal {
    /**
     * animsl abstract class has two abstract methods cats and dogs. Now create a class &#39;
     * Cats with a method cats(); which prints Cats meow quot; Class Dogs has a method dogs();
     * which prints Dogs bark quot; both inheriting the class Animals. Now create an object for each of the
     * subclasses and call their respective methods.
     */
    public static void main(String[] args) {
        Cats c = new Cats();
        c.cats();
        System.out.println("Cats meow");
        Dogs d = new Dogs();
        d.dogs();
        System.out.println("Dogs bark");


    }
}
