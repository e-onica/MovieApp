package controller;

import model.dao.Configuration;
import model.dao.Generic;
import model.dto.ActorModel;
import model.dto.MovieModel;
import model.dto.RatingModel;
import model.dto.ReviewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MovieServices {
    Generic<MovieModel> genericObject = new Generic<>();

    private MovieModel movie = new MovieModel();

    public void addMovie(MovieModel movie) {

        genericObject.add(movie);
    }

    public MovieModel searchMovieByName(String movieName) {

        MovieModel foundMovie = genericObject.searchByName(movieName, movie);

        if (foundMovie != null) {
            System.out.println(foundMovie);
        } else {
            System.out.println("Sorry we couldn't find anything");
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
            movie.setRating(sum / counter);
            session.save(movie);
            session.getTransaction().commit();
            return sum / counter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }




}
