package view.ui;

import controller.MovieServices;
import controller.ReviewServices;
import model.dao.Configuration;
import model.dto.MovieModel;
import model.dto.RatingModel;
import model.dto.ReviewModel;
import model.dto.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieUi {

        MovieServices movieServices = new MovieServices();
        private void printMenu(){

            List<String> menuOptions = new ArrayList<>();
            menuOptions.add("Enter 1 for Top Movies");
            menuOptions.add("Enter 2 for New Movies");
            menuOptions.add("Enter 3 to search an actor");
            menuOptions.add("Enter 4 to search a movie");
            menuOptions.add("Enter 5 to exit the menu: ");
            for(String option: menuOptions){
                System.out.println(option);
            }
    }

    private void printMovieMenu(){
        List<String> movieOptions = new ArrayList<>();
        movieOptions.add("Enter 1 if you want to rate this movie: ");
        movieOptions.add("Enter 2 if you want to write a review: ");
        movieOptions.add("Enter 3 to see all the reviews: ");
        movieOptions.add("Enter 4 to exit the menu: ");
        for(String option: movieOptions){
            System.out.println(option);
        }

    }

    public void viewHomeMenu(UserModel user){
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Welcome to Home Menu:");
        printMenu();
        int choice = scanner.nextInt();
        switch(choice){
            case 4:{
                System.out.println("Enter the name of the movie: ");
                String movieName = scanner.next();
                MovieModel movie = movieServices.searchMovieByName(movieName);
                printMovieMenu();
                int option = scanner.nextInt();
                switch(option){
                    case 1:{
                        System.out.println("Please rate from 1 to 5 stars");
                        int stars = scanner.nextInt();
                        RatingModel rating = new RatingModel(stars, user, movie);
                        try (Session session = Configuration.getSessionFactory().openSession()) {
                            Transaction transaction = session.beginTransaction();
                            session.save(rating);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        movieServices.updateMovieRating(movie.getId());
                        viewHomeMenu(user);
                    }
                    case 2:{
                        System.out.println("Please write your review");
                        String review = scanner.next();
                        ReviewModel reviewModel = new ReviewModel(review, user, movie);
                        try (Session session = Configuration.getSessionFactory().openSession()) {
                            Transaction transaction = session.beginTransaction();
                            session.save(reviewModel);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        viewHomeMenu(user);
                    }
                    case 3:{
                        ReviewServices reviewServices = new ReviewServices();
                        reviewServices.printAllReviews(movie.getId());
                        viewHomeMenu(user);
                    }
                }

            }
        }
    }
}
