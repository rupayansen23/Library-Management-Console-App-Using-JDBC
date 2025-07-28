package LibraryManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookDao {
    public static boolean insertBooktoDB(Book book) {
        boolean flag = false;
        try {
            Connection connection = ConnectDB.connectToDB();
            String query = "INSERT INTO books(b_id, b_name, b_author, total_stock, current_stock)VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getBid());
            preparedStatement.setString(2, book.getBname());
            preparedStatement.setString(3, book.getBauthor());
            preparedStatement.setInt(4, book.getTotalStock());
            preparedStatement.setInt(5, book.getCurrentStock());
            preparedStatement.executeUpdate();
            flag = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
