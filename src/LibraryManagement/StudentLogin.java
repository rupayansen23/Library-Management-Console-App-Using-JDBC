package LibraryManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentLogin {
    public static Student studentAuthentication() {
        try{
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your roll number :");
            String roll = buf.readLine();
            System.out.println("Enter your first name : ");
            String firstName = buf.readLine();
            try {
                Connection connection = ConnectDB.connectToDB();
                String query = "SELECT * FROM students WHERE sroll = ?;";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, roll);
                ResultSet resultSet = preparedStatement.executeQuery();

                Student student = new Student();

                while(resultSet.next()) {
                    student.setRollNumber(resultSet.getString(1));
                    student.setFirstName(resultSet.getString(2));
                    student.setLastName(resultSet.getString(3));
                    student.setDept(resultSet.getString(4));
                    student.setBook1(resultSet.getString(5));
                    student.setBook2(resultSet.getString(6));
                }
//                System.out.println(student.getRollNumber()+" "+student.getFirstName());
                if(roll.equals(student.getRollNumber()) && student.getFirstName().equals(firstName)) {
                    return student;
                }
            }
            catch (SQLException se) {
                System.out.println("Exception : "+se.getMessage());
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void showYourInfo(Student student) throws IOException{

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-------------Welcome Sir------------");
        System.out.println("Name : "+student.getFirstName()+" "+student.getLastName());
        System.out.println("Roll NO : "+student.getRollNumber());
        System.out.println("Department :"+student.getDept());
        System.out.println("-------------------------------------");

    }
}
