package relation;

public class User {


    int streetNum;
    String userName;
    Address user;

    User(int streetNum, String addr,Address user) {
        this.streetNum = streetNum;
        this.userName = addr;
        this.user = user;
    }

    public static void main(String[] args) {

        Address add = new Address(95, "Abovyan", "Armenia");
        User u = new User(95, "Halabyan",add);

        System.out.println(add.streetNum);
        System.out.println(add.city);
        System.out.println(add.country);

    }


}

