package LibraryManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDao {
    public static boolean insertStudentToDB(Student student) {
        boolean flag = false;
        try {
            Connection connection = ConnectDB.connectToDB();
            String query = "INSERT INTO students(sroll, sfirstname, slastname, dept)VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getRollNumber());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getDept());
            preparedStatement.executeUpdate();
            flag = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
