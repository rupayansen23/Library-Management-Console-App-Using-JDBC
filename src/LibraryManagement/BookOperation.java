package LibraryManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookOperation {
    public static void collectBook(Student student) {
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
                System.out.println(student.getBook1()+" "+student.getBook2());
                BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter book id to take : ");
                String bookId = buf.readLine();
                for(Book book : bookList) {
                    if(bookId.equals(book.getBid()) && book.getCurrentStock() > 0) {
                        if(student.getBook1() == null) {
                            String updateQuery = "UPDATE students SET book1 = ? WHERE sroll = ?;";
                            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                            preparedStatement.setString(1, book.getBid());
                            preparedStatement.setString(2, student.getRollNumber());
                            preparedStatement.executeUpdate();
                            book.setCurrentStock(book.getCurrentStock()-1);
                            String updateStock = "UPDATE books SET current_stock = ? WHERE b_id = ?;";
                            PreparedStatement pstmt = connection.prepareStatement(updateStock);
                            pstmt.setInt(1, book.getCurrentStock());
                            pstmt.setString(2, book.getBid());
                            pstmt.executeUpdate();

                        } else if(student.getBook2() == null) {
                            String updateQuery = "UPDATE students SET book2 = ? WHERE sroll = ?;";
                            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                            preparedStatement.setString(1, book.getBid());
                            preparedStatement.setString(2, student.getRollNumber());
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
    public static void returnBook(Student student) {
        try {
            Connection connection = ConnectDB.connectToDB();
            String query = """
                            SELECT s.sroll, 
                                   s.sfirstname, 
                                   s.slastname, 
                                   b.b_id, 
                                   b.b_name, 
                                   b.b_author
                            FROM students s
                            LEFT JOIN books b ON b.b_id = s.book1 OR b.b_id = s.book2
                            WHERE s.sroll = ?;
                            """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getRollNumber());
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Roll         | \t | FirstName | \t | LastName | \t | b_id | \t | b_name | \t | b_author");
            System.out.println("-----------------------------------------------------------------------------------");
            while (resultSet.next()) {
                String roll = resultSet.getString(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String b_id = resultSet.getString(4);
                String b_name = resultSet.getString(5);
                String b_author = resultSet.getString(6);
                System.out.println(roll+" | \t | "+firstname+" | \t | "+lastname+" | \t | "+b_id+" | \t | "+b_name+" | \t | "+b_author);
            }
            char yes = 'y';
            try {
                BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                while(yes == 'y' || yes == 'Y') {
                    System.out.print("Enter book id [for return] : ");
                    String book_id = buf.readLine();
                    if(book_id.equals(student.getBook1())) {
                        String query2 = "UPDATE students SET book1 = NULL WHERE sroll = ?;";
                        PreparedStatement pstmt = connection.prepareStatement(query2);
                        pstmt.setString(1, student.getRollNumber());
                        pstmt.executeUpdate();

                        String query3 = "UPDATE books SET current_stock = current_stock + 1 WHERE b_id = ?";
                        PreparedStatement pstmt2 = connection.prepareStatement(query3);
                        pstmt2.setString(1, book_id);
                        pstmt2.executeUpdate();
                    }
                    else if(book_id.equals(student.getBook2())) {
                        String query2 = "UPDATE students SET book2 = NULL WHERE sroll = ?;";
                        PreparedStatement pstmt = connection.prepareStatement(query2);
                        pstmt.setString(1, student.getRollNumber());
                        pstmt.executeUpdate();

                        String query3 = "UPDATE books SET current_stock = current_stock + 1 WHERE b_id = ?";
                        PreparedStatement pstmt2 = connection.prepareStatement(query3);
                        pstmt2.setString(1, book_id);
                        pstmt2.executeUpdate();
                    }
                    else {
                        System.out.println("Invalid Book id");
                        continue;
                    }
                    System.out.println("Would you like to Continue [y/Y] : ");
                    String input = buf.readLine();
                    yes = input.charAt(0);
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException sqlExp) {
            System.out.println(sqlExp.getMessage());
        }
    }
}
