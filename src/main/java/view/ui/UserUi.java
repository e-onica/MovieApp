package view.ui;

import controller.UserServices;
import model.dao.Configuration;
import model.dto.UserDetailModel;
import model.dto.UserModel;
import org.hibernate.Session;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserUi {

    static UserModel loggedInUser = new UserModel();

    public void logIn() {

        System.out.println("Enter 1 for registration");
        System.out.println("Enter 2 for Log in, if you already have an account");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int choice = scanner.nextInt();
        while(choice != 1 && choice !=2){
            System.out.println("Your choice is not valid");
            System.out.println("Enter 1 for registration");
            System.out.println("Enter 2 for Log in, if you already have an account");
            choice = scanner.nextInt();
        }

        UserServices userServices = new UserServices();

        switch (choice) {
            case 1: {
                Scanner sc = new Scanner(System.in).useDelimiter("\n");
                System.out.println("Enter e-mail");
                String email = sc.next();
                System.out.println("Enter password");
                String password = sc.next();
                System.out.println("Enter country");
                String country = sc.next();
                System.out.println("Enter age");
                int age = sc.nextInt();
                System.out.println("Enter gender ");
                String gender = sc.next();
                UserDetailModel userDetail = new UserDetailModel(country, age, gender);
                UserModel user = new UserModel(email, password, userDetail);
                userServices.addUser(user);
                logIn();
            }
            case 2: {
                boolean isDataValid = false;
                while (!isDataValid) {
                    Scanner sc = new Scanner(System.in).useDelimiter("\n");
                    System.out.println("Enter e-mail");
                    String email = sc.next();
                    System.out.println("Enter password");
                    String password = sc.next();
                    isDataValid = isUserDataValid(email, password);

                    if(isDataValid){
                        System.out.println("Enter 1 to view your account");
                        System.out.println("Enter 2 to go to Home Menu");
                        try {
                            int option = sc.nextInt();
                            MovieUi movieUi = new MovieUi();
                            switch (option) {
                                case 1: {
                                    System.out.println(loggedInUser + "\n");
                                    movieUi.goBackToHomeMenu(scanner, loggedInUser);
                                }
                                case 2: {
                                    movieUi.viewHomeMenu(loggedInUser);
                                }
                                default: {
                                    System.out.println("Your choice is not valid \n");
                                    break;
                                }
                            }
                        }catch(InputMismatchException e){
                            System.out.println("Your choice is not valid \n");
                            break;
                        }

                    }
                }
                break;
            }
        }
    }

    public boolean isUserDataValid(String email, String password) {
        try (Session session = Configuration.getSessionFactory().openSession()) {
            List<UserModel> users = session.createQuery("from UserModel").getResultList();
            for (UserModel user : users) {
                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    this.loggedInUser = user;
                    return true;
                }
            }
            System.out.println("Incorrect log in data, please try again");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
