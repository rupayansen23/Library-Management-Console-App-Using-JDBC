package LibraryManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLOutput;

public class Registration {
    public static void registerName() {
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(isr);
            System.out.println("Enter first name : ");
            String firstName = buf.readLine();
            System.out.println("Enter last name : ");
            String lastName = buf.readLine();
            System.out.println("Enter Roll number : ");
            String rollNumber = buf.readLine();
            System.out.println("Enter Dept. Name : ");
            String deptName = buf.readLine();
            Student student = new Student(firstName, lastName,rollNumber, deptName);
            boolean ans = StudentDao.insertStudentToDB(student);
            if(ans) {
                System.out.println("Registration Successful");
            } else {
                System.out.println("Something went wrong");
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
