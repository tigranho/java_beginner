package homework_classes;

public class Employee {
    /**
     * Define the Employee class that has:
     * fields (id, name, department, age, gender)
     * default constructor:
     * constructor that contains all the fields
     * methods which print complete information about Employee.
     * Create 5 employees (3 boys and 2 girls),
     * three of them work in the same department and two are the same age.
     */
    int id;
    String name;
    String department;
    int age;
    String gender;

    Employee() {

    }

    public Employee(int id, String name, String department, int age, String gender) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
        this.gender = gender;

    }

    public void printEmployeeInformation() {
        System.out.println(" id " + id);
        System.out.println(" name " + name);
        System.out.println(" department " + department);
        System.out.println(" age " + age);
        System.out.println(" gender " + gender);

    }

    public static void main(String[] args) {
        Employee a = new Employee(12587454, "Anna", "Quality Assurance", 28, "female");
        Employee l = new Employee(12368745, "Lilit", "Quality Assurance", 45, "female");
        Employee s = new Employee(78965255, "Samuel", "Quality Assurance", 24, "male");
        Employee av = new Employee(98745587, "Avetis", "Java programmer", 39, "male");
        Employee t = new Employee(785456, "Tigran", "engineer", 45, "male");


        a.printEmployeeInformation();
        System.out.println();
        l.printEmployeeInformation();
        System.out.println();
        s.printEmployeeInformation();
        System.out.println();
        av.printEmployeeInformation();
        System.out.println();
        t.printEmployeeInformation();

    }
}




