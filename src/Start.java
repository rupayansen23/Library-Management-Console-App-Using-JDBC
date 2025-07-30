import LibraryManagement.*;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        System.out.println("-------RCCIIT Library Welcomes you----------");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1 For Register your Name");
            System.out.println("2 for Borrow Books");
            System.out.println("3 for Return Books");
            System.out.println("4 for show Your Information");
            System.out.println("5 for Admin Login");
            System.out.println("6 for exit");
            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Registration.registerName();
                    break;
                case 2 :
                    Student student = StudentLogin.studentAuthentication();
                    if(student != null) {
                        System.out.println("Authentication Successfull");
                        BookOperation.collectBook(student);
                    } else {
                        System.out.println("Authentication Unsucessfull");
                    }
                    break;
                case 3 :
                    Student student1 = StudentLogin.studentAuthentication();
                    if(student1 != null) {
                        System.out.println("Authentication Sucessful");
                        BookOperation.returnBook(student1);
                    }
                    break;
                case 5:
                    AdminLogin.login();
                    break;
                case 6 :
                    System.exit(choice);
                default:
                    System.out.println("Give a correct command");
            }
        }
    }
}
