package relation;

public class Address {
    /**
     * Create Address and User  classes using   one-to-many relation
     */
    int streetNum;
    String country;
    String city;

    Address(int street, String city, String country) {
        this.streetNum = street;
        this.city = city;
        this.country = country;
    }
}
