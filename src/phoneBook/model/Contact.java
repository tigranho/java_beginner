package phoneBook.model;

public class Contact {
    private String firstName;
    private String lastName;
    private int number;
    private String emailId;
    private final Priority p;

    public Contact(String firstName, String lastName, int phoneNumber, String emailId, Priority p) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmailId(emailId);
        this.p = p;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public Priority getP() {
        return p;
    }


    public long getPhoneNumber() {
        return number;
    }

    public void setPhoneNumber(int phoneNumber) {
        if(phoneNumber / 10_00000 < 10 && phoneNumber / 10_00000 > 0 ){
            this.number = phoneNumber;
        }else{
            throw new IllegalArgumentException(" Less than 7 numbers is not allowed ");
        }


    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
      if(emailId.length() > 10){
          this.emailId = emailId;
      }else{
          throw new RuntimeException(" Email must be more than 10 characters. Please enter right email address");
      }

    }
    @Override
    public String toString() {
        return "\n\t" + " Flying Eagle: My contact's book " +
                "\n\t" +
                "\n\t firstName: " + firstName +
                "\n\t lastName: " + lastName +
                "\n\t number:  " + number +
                "\n\t emailId: " + emailId +
                "\n\t ";

    }
}

