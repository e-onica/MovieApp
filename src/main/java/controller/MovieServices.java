package controller;

import model.dao.Configuration;
import model.dao.Generic;
import model.dto.ActorModel;
import model.dto.MovieModel;
import model.dto.RatingModel;
import model.dto.ReviewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import view.ui.MovieUi;

import java.util.List;

import static java.lang.Math.round;

public class MovieServices {
    Generic<MovieModel> genericObject = new Generic<>();

    private MovieModel movie = new MovieModel();

    public void addMovie(MovieModel movie) {

        genericObject.add(movie);
    }

    public MovieModel searchMovieByName(String movieName) {

        MovieModel foundMovie = genericObject.searchByName(movieName, movie);

        if (foundMovie != null) {
            System.out.println("----------------------------------------------");
            System.out.println(foundMovie);
            System.out.println("----------------------------------------------");
        }
        return foundMovie;
    }

    public double updateMovieRating(int movieId) {
        try (Session session = Configuration.getSessionFactory().openSession()) {
            List<Integer> stars = session.createQuery("select stars from RatingModel where id_movie=" + movieId).getResultList();
            double sum = 0.0;
            int counter = 0;
            for (int star : stars) {
                sum = sum + star;
                counter++;
            }
            session.beginTransaction();
            MovieModel movie = session.get(MovieModel.class, movieId);
            movie.setRating((Math.floor((sum / counter) * 10)) / 10);
            session.save(movie);
            session.getTransaction().commit();
            return (Math.floor((sum / counter) * 10)) / 10;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void viewTopMovies() {
        try (Session session = Configuration.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<MovieModel> topMovies = session.createQuery("FROM MovieModel where rating >= 4 ").list();
            for (MovieModel movie : topMovies) {
                System.out.println(movie);
            }
            System.out.println("----------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewNewMovies() {
        try (Session session = Configuration.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<MovieModel> newMovies = session.createQuery("FROM MovieModel where year > 2020 ").list();
            for (MovieModel movie : newMovies) {
                System.out.println(movie);
            }
            System.out.println("----------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
