package LibraryManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/library";
    private static final String userName = "root";
    private static final String passWord = "1234";

    public static Connection connectToDB() {
        //load the driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, userName, passWord);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}

