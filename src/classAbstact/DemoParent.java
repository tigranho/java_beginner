package classAbstact;

public class DemoParent {
    /**
     * Create an abstract class 'Parent' with a method 'message'. It has two subclasses each having a method
     * with the same name 'message' that prints "This is first subclass" and "This is second subclass" respectively.
     * Call the methods 'message' by creating an object for each subclass.
     */
    public static void main(String[] args) {
        First s = new First();
        s.message();
        Second st = new Second();
        st.message();
    }
}
