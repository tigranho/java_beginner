package phoneBook.warehouse;

import phoneBook.model.Contact;

import java.util.ArrayList;
import java.util.Objects;


public class Warehouse {
    ArrayList<Contact> warehouse = new ArrayList<>();


    public void createContact(Contact contact) {

        warehouse.add(contact);

    }

    public int size() {
        return warehouse.size();

    }

    public String read(int number) {
        for (Contact contact : warehouse) {
            if (number == contact.getPhoneNumber()) {
                System.out.println(" expected number " + number);
                return contact.getFirstName();
            }
        }

        return null;
    }

    public void updateContactNumber(int number, int newNumber) {
        for ( Contact contact : warehouse) {
            if(number == contact.getPhoneNumber()){
                contact.setPhoneNumber(newNumber);
            }

        }


    }
    public void updateContactFirstName(String name, String newName) {
        for ( Contact contact : warehouse) {
            if(Objects.equals(name, contact.getFirstName())){
                contact.setFirstName(newName);
            }

        }


    }

    public void deleteContact(String email) {
        for (int i = 0; i < warehouse.size(); i++) {
            if (Objects.equals(email, warehouse.get(i).getEmailId())) {
                warehouse.remove(i);
                System.out.println(email);
            }
        }
    }
}


