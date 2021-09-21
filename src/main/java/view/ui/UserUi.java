package view.ui;

import controller.UserServices;
import model.dao.Configuration;
import model.dto.UserDetailModel;
import model.dto.UserModel;
import org.hibernate.Session;

import java.util.*;

public class UserUi {

    static UserModel loggedInUser = new UserModel();
    static List<String> countries = Arrays.asList("Romania", "Brazil", "Russia", "Australia", "Moldova", "SUA", "France",
            "England", "Germany");

    public void logIn() {

        System.out.println("Enter 1 for registration");
        System.out.println("Enter 2 for Log in, if you already have an account");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Your choice is not valid");
            System.out.println("Enter 1 for registration");
            System.out.println("Enter 2 for Log in, if you already have an account");
            choice = scanner.nextInt();
        }


        switch (choice) {
            case 1: {
                if (isRegistrationValid()) {
                    break;
                }
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

                    if (isDataValid) {
                        System.out.println("Enter 1 to view your account");
                        System.out.println("Enter 2 to go to Home Menu");
                        try {
                            int option = sc.nextInt();
                            MovieUi movieUi = new MovieUi();
                            switch (option) {
                                case 1: {
                                    System.out.println(loggedInUser + "\n");
                                    movieUi.goBackToHomeMenu(scanner, loggedInUser);
                                    break;
                                }
                                case 2: {
                                    movieUi.viewHomeMenu(loggedInUser);
                                    break;
                                }
                                default: {
                                    System.out.println("Your choice is not valid \n");
                                    break;
                                }
                            }
                        } catch (InputMismatchException e) {
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
            UserModel user = (UserModel) session.createQuery(
                    "from UserModel where email=" + "\'" + email + "\'" +
                            " and password=" + "\'" + password + "\'").getSingleResult();
            this.loggedInUser = user;
            return true;

        } catch (Exception e) {
            System.out.println("Incorrect log in data, please try again");
        }

        return false;
    }

    public boolean isRegistrationValid() {

        UserServices userServices = new UserServices();

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter e-mail");
        String email = sc.next(); //2 cond - 1. sa contina cel putin 11 caract 2. sa contina @gmail.com @yahoo.com @
        while (email.length() < 11 || (!email.endsWith("@gmail.com") && !email.endsWith("@yahoo.com"))) {
            System.out.println("Your email is less than 11 characters, please re-enter again ");
            email = sc.next();
        }
        System.out.println("Enter password four characters minimum");
        String password = sc.next();
        while (password.length() < 4) {
            password = sc.next();
            System.out.println("Please re-enter password");
        }
        System.out.println("Enter country");
        String country = sc.next();
        boolean isCountryValid = false;
        for (String countryAux : countries) {
            if (countryAux.equals(country)) {
                isCountryValid = true;
            }
        }
        if (!isCountryValid) {
            System.out.println("The website is not available in your country");

        }
        System.out.println("Enter age");
        int age = sc.nextInt();
        if (age < 14) {
            System.out.println("Minimum age for registration is 14 years old");

        }
        System.out.println("Enter gender F/M");
        String gender = sc.next();
        if (!(gender.equals("F") || gender.equals("M"))) {
            System.out.println("Please re-enter your gender");
            gender = sc.next();
        }
        System.out.println("NOTHING");

        UserDetailModel userDetail = new UserDetailModel(country, age, gender);
        UserModel user = new UserModel(email, password, userDetail);
        userServices.addUser(user);


        logIn();

        return isCountryValid;
    }

}
