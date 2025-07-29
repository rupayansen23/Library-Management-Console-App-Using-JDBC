package LibraryManagement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminLogin {
    public static String userName;
    public static String passwd;

    public static void addBook() {
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println("1 for insert book | 2 for back to main menu");
                int choice = Integer.parseInt(buf.readLine());
                switch (choice) {
                    case 1 :
                        System.out.print("Enter Book Id : ");
                        String id = buf.readLine();
                        System.out.print("Enter book title : ");
                        String title = buf.readLine();
                        System.out.print("Enter author name : ");
                        String author = buf.readLine();
                        System.out.println("Enter quantity : ");
                        int quantity = Integer.parseInt(buf.readLine());
                        Book book = new Book(id, title, author, quantity);
                        boolean answer = BookDao.insertBooktoDB(book);
                        if(answer) {
                            System.out.println("Inserted book info to db");
                        } else {
                            System.out.println("Something went wrong");
                        }
                        break;
                    case 2 :
                        return;
                    default:
                        System.out.println("Wrong command ! try again");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void login() {
        try{
            BufferedReader buf =  new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter username : ");
            userName = buf.readLine();
            System.out.print("Enter password : ");
            passwd = buf.readLine();

            try{
                Connection connection = ConnectDB.connectToDB();
                String query = "SELECT * FROM admin;";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                String db_userName="", db_password="";
                while(resultSet.next()) {
                    db_userName = resultSet.getString(1);
                    db_password = resultSet.getString(2);
                }
//                System.out.println(db_userName+" "+db_password);
                if(userName.equals(db_userName) && db_password.equals(passwd)) {
                    System.out.println("Authentication successful ");
                    AdminLogin.addBook();

                } else {
                    System.out.println("Authentication unsucessful");
                }
            }
            catch (SQLException sqlexp) {
                System.out.println(sqlexp.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
