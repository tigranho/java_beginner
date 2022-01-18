package phoneBook;

import phoneBook.model.Contact;
import java.util.Scanner;
import phoneBook.model.Priority;

import phoneBook.warehouse.Warehouse;


public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Warehouse m = new Warehouse();
        Contact c = new Contact("Tigran", "Adams", 5684739, "flying@gmail.com", Priority.high);
        Contact ca = new Contact("Avetis", "Adams", 6787139, "Adams@gmail.com", Priority.medium);
        Contact cb = new Contact("Davit", "Adams", 7145891, "Davit@gmail.com", Priority.high);
        Contact cc = new Contact("Lucy", "Smith", 1276459, "smith@gmail.com", Priority.low);
        Contact cd = new Contact("Lilit", "Adamyan", 6674189, "eagle@gmail.com", Priority.high);
        Contact ce = new Contact("Serine", "Adams", 8567378, "ser.serin@gmail.com", Priority.medium);
        m.createContact(c);
        m.createContact(ca);
        m.createContact(cb);
        m.createContact(cc);
        m.createContact(cd);
        m.createContact(ce);
        System.out.println(m.size());
        System.out.println(c.toString());
        System.out.println(ca.toString());
        System.out.println(cb.toString());
        System.out.println(cc.toString());
        System.out.println(cd.toString());
        System.out.println(ce.toString());
        System.out.println(m.read(sc1.nextInt()));
        m.deleteContact(sc.nextLine());
        System.out.println(m.size());
        m.updateContactNumber(sc.nextInt(), sc.nextInt());
        System.out.println(ce.toString());
        m.updateContactFirstName(sc.nextLine(), sc2.nextLine());
       // System.out.println(cc.toString());


    }
}