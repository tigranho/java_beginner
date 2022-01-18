package homework_classes;

/**
 *Define a Student class that has:
 * fields` (name, surname, gender, data, proffesion, graduated, jobExperiance)
 * methods`setPassingGrade (), getYearOfEmployment ()
 * If the exam is lower than 95, do not hire/ reject.
 */
public class Student {
    String name;
    String surname;
    String gender;
    int data;
    String proffession;
    int graduated;
    int yearOfEmployment;
    int passingGrade;

    Student(String name, String surname, String gender, int data, String proffession, int graduated,int passingGrade, int yearOfEmployment) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.data = data;
        this.proffession = proffession;
        this.graduated = graduated;
        this.yearOfEmployment = yearOfEmployment;
        setPassingGrade(passingGrade);


    }

    public void setPassingGrade(int passingGrade) {
        if(passingGrade > 95){
            this.passingGrade = passingGrade;
        }else{
            throw new IllegalArgumentException("Less than 95 not allowed");

        }

    }

    public int getYearOfEmployment() {
        return yearOfEmployment;
    }

    public static void main(String[] args) {
        Student al = new Student("Lilit", "Adamyan", "female", 1994, "Psychologist, QA Engineer", 2019, 100,4);
        al.setPassingGrade(98);
        System.out.println(al.getYearOfEmployment());


    }
}
