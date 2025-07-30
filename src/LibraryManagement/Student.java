package LibraryManagement;

public class Student {
    private String firstName, lastName, rollNumber, dept;
    private String book1, book2;

    public Student() {

    }

    public Student(String firstName, String lastName, String rollNumber, String dept) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
        this.dept = dept;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getDept() {
        return dept;
    }

    public String getBook1() {
        return book1;
    }

    public String getBook2() {
        return book2;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setBook1(String book1) {
        this.book1 = book1;
    }

    public void setBook2(String book2) {
        this.book2 = book2;
    }
}
