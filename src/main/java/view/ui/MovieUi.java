package view.ui;

import controller.ActorServices;
import controller.MovieServices;
import controller.ReviewServices;
import model.dao.Configuration;
import model.dto.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovieUi {

    MovieServices movieServices = new MovieServices();
    ActorServices actorServices = new ActorServices();

    private void printHomeMenu() {

        List<String> menuOptions = new ArrayList<>();
        menuOptions.add("Enter 1 for Top Movies");
        menuOptions.add("Enter 2 for New Movies");
        menuOptions.add("Enter 3 to search an actor");
        menuOptions.add("Enter 4 to search a movie");
        menuOptions.add("Enter 5 to exit the app: ");
        for (String option : menuOptions) {
            System.out.println(option);
        }
    }

    public void goBackToHomeMenu(Scanner scanner, UserModel user) {
        System.out.println("Enter 1 to go back to the Home Menu");
        int exit = scanner.nextInt();
        while (exit != 1) {
            System.out.println("Enter 1 to go back to Home Menu");
            exit = scanner.nextInt();
        }
        viewHomeMenu(user);
    }

    public void viewHomeMenu(UserModel user) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Welcome to Home Menu: ");
        System.out.println("----------------------------------------------");
        printHomeMenu();

        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("Top Movies: \n");
                movieServices.viewTopMovies();
                goBackToHomeMenu(scanner, user);
                break;
            }
            case 2: {
                System.out.println("New Movies: \n");
                movieServices.viewNewMovies();
                goBackToHomeMenu(scanner, user);
                break;
            }
            case 3: {
                System.out.println("Enter the name of the actor: ");
                String actorName = scanner.next();
                ActorModel actor = actorServices.searchActorByName(actorName, user);
                actorServices.getActorSMovies(actor);
                goBackToHomeMenu(scanner, user);
                break;
            }
            case 4: {
                System.out.println("Enter the name of the movie: ");
                String movieName = scanner.next();
                MovieModel movie = movieServices.searchMovieByName(movieName);
                if (movie == null) {
                    viewHomeMenu(user);
                } else {
                    viewMovieMenu(movie, user, scanner);
                }
                break;
            }
            case 5: {
                System.out.println("See you soon!");
                break;
            }
        }
    }

    private void printMovieMenu() {
        List<String> movieOptions = new ArrayList<>();
        movieOptions.add("Enter 1 if you want to rate this movie: ");
        movieOptions.add("Enter 2 if you want to write a review: ");
        movieOptions.add("Enter 3 to see all the reviews: ");
        movieOptions.add("Enter 4 to exit the menu: ");
        for (String option : movieOptions) {
            System.out.println(option);
        }
    }

    public void viewMovieMenu(MovieModel movie, UserModel user, Scanner scanner) {
        printMovieMenu();
        int userOption = scanner.nextInt();
        switch (userOption) {
            case 1: {
                System.out.println("Please rate from 1 to 5 stars");
                int stars = scanner.nextInt();
                while (stars > 5 || stars == 0) {
                    System.out.println("Please rate from 1 to 5 stars");
                    stars = scanner.nextInt();
                }
                System.out.println("Your rating has been successfully registered \n");
                RatingModel rating = new RatingModel(stars, user, movie);
                try (Session session = Configuration.getSessionFactory().openSession()) {
                    Transaction transaction = session.beginTransaction();
                    session.save(rating);
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                movieServices.updateMovieRating(movie.getId());
                viewMovieMenu(movie, user, scanner);
                break;
            }
            case 2: {
                System.out.println("Please write your review");
                String review = scanner.next();
                System.out.println("Your review was added to this movie's reviews \n");
                ReviewModel reviewModel = new ReviewModel(review, user, movie);
                try (Session session = Configuration.getSessionFactory().openSession()) {
                    Transaction transaction = session.beginTransaction();
                    session.save(reviewModel);
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                viewMovieMenu(movie, user, scanner);
                break;
            }
            case 3: {
                ReviewServices reviewServices = new ReviewServices();
                reviewServices.printAllReviews(movie.getId());
                viewMovieMenu(movie, user, scanner);
                break;
            }
            case 4: {
                viewHomeMenu(user);
                break;
            }
            default: {
                System.out.println("We could not find any result to your request \n");
                viewMovieMenu(movie, user, scanner);
                break;
            }
        }
    }
}
