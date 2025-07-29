package LibraryManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class BorrowBook {
    public static void collectBook(final String book1, final String book2, final String db_roll) {
        try {
            Connection connection = ConnectDB.connectToDB();
            String query = "SELECT b_id, b_name, b_author, current_stock FROM books;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<Book> bookList = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBid(resultSet.getString(1));
                book.setBname(resultSet.getString(2));
                book.setBauthor(resultSet.getString(3));
                book.setCurrentStock(resultSet.getInt(4));
                bookList.add(book);
            }
            System.out.println("Id\tname\tauthor\tcurrent_stock");
            System.out.println("-------------------------------------------");
            for(Book i : bookList) {
                System.out.print(i.getBid()+"\t");
                System.out.print(i.getBname()+"\t");
                System.out.print(i.getBauthor()+"\t");
                System.out.print(i.getCurrentStock()+"\t");
                System.out.println();
            }
            try{
                boolean flag = false;
                System.out.println(book1+" "+book2);
                BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter book id to take : ");
                String bookId = buf.readLine();
                for(Book book : bookList) {
                    if(bookId.equals(book.getBid()) && book.getCurrentStock() > 0) {
                        if(book1 == null) {
                            String updateQuery = "UPDATE students SET book1 = ? WHERE sroll = ?;";
                            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                            preparedStatement.setString(1, book.getBid());
                            preparedStatement.setString(2, db_roll);
                            preparedStatement.executeUpdate();
                            book.setCurrentStock(book.getCurrentStock()-1);
                            String updateStock = "UPDATE books SET current_stock = ? WHERE b_id = ?;";
                            PreparedStatement pstmt = connection.prepareStatement(updateStock);
                            pstmt.setInt(1, book.getCurrentStock());
                            pstmt.setString(2, book.getBid());
                            pstmt.executeUpdate();

                        } else if(book2 == null) {
                            String updateQuery = "UPDATE students SET book2 = ? WHERE sroll = ?;";
                            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                            preparedStatement.setString(1, book.getBid());
                            preparedStatement.setString(2, db_roll);
                            preparedStatement.executeUpdate();
                            book.setCurrentStock(book.getCurrentStock()-1);
                            String updateStock = "UPDATE books SET current_stock = ? WHERE b_id = ?;";
                            PreparedStatement pstmt = connection.prepareStatement(updateStock);
                            pstmt.setInt(1, book.getCurrentStock());
                            pstmt.setString(2, book.getBid());
                            pstmt.executeUpdate();
                        } else {
                            System.out.println("You reached book allocation limit !! we can't allocate more");
                        }
                        flag = true;
                    }
                }
                if(flag) {
                    System.out.println("One book is allocated successfully");
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
    public static void studentAuthentication() {
        try{
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your roll number :");
            String roll = buf.readLine();
            System.out.println("Enter your first name : ");
            String firstName = buf.readLine();
            try {
                Connection connection = ConnectDB.connectToDB();
                String query = "SELECT sroll, sfirstname, book1, book2 FROM students WHERE sroll = ?;";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, roll);
                ResultSet resultSet = preparedStatement.executeQuery();

                String db_roll="", db_firstName="", book1="", book2="";
                while(resultSet.next()) {
                    db_roll = resultSet.getString(1);
                    db_firstName = resultSet.getString(2);
                    book1 = resultSet.getString(3);
                    book2 = resultSet.getString(4);
                }
                System.out.println(db_roll+" "+db_firstName);
                if(roll.equals(db_roll) && db_firstName.equals(firstName)) {
                    System.out.println("Authentication successful ");
                    BorrowBook.collectBook(book1, book2, db_roll);
                } else {
                    System.out.println("Authentication unsucessful");
                }
            }
            catch (SQLException se) {
                System.out.println("Exception : "+se.getMessage());
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
