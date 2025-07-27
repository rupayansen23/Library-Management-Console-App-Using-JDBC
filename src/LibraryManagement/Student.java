package LibraryManagement;

public class Student {
    private String firstName, lastName, rollNumber, dept;
    private String book1, book2;

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
}
