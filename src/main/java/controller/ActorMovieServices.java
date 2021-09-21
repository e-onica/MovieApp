package controller;

import model.dao.Configuration;
import model.dao.Generic;
import model.dto.ActorModel;
import model.dto.ActorMovieModel;
import model.dto.MovieModel;
import org.hibernate.Session;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class ActorMovieServices {
    Generic<ActorMovieModel> genericObject = new Generic<>();

//    public void addMovieIfAbsent(boolean isMoviePresent, MovieModel movie){
//        if(!isMoviePresent){
//            MovieServices movieServices = new MovieServices();
//            movieServices.addMovie(movie);
//        }
//
//    }

    public void addActorMovie(ActorModel actor, MovieModel movie) {
        try (Session session = Configuration.getSessionFactory().openSession()) {

            List<ActorModel> actors = session.createQuery("SELECT u FROM ActorModel u").getResultList();
            boolean isActorPresent = false;
            for (ActorModel actorFromList : actors) {
                if (actorFromList.getName().equals(actor.getName())) {
                    actor = actorFromList;
                    isActorPresent = true;
                }
            }
            if (!isActorPresent) {
                ActorServices actorServices = new ActorServices();
                actorServices.addActor(actor);
            }
            List<MovieModel> movies = session.createQuery("SELECT u FROM MovieModel u").getResultList();
            boolean isMoviePresent = false;
            for (MovieModel movieFromList : movies) {
                if (movieFromList.getName().equals(movie.getName())) {
                    movie = movieFromList;
                    isMoviePresent = true;
                }
            }
            if (!isMoviePresent) {
                MovieServices movieServices = new MovieServices();
                movieServices.addMovie(movie);
            }

            ActorMovieModel actorMovie = new ActorMovieModel(actor, movie);
            List<ActorMovieModel> actorMovies =
                    session.createQuery("SELECT u FROM ActorMovieModel u").getResultList();
            boolean isActorMoviePresent = false;
            for (ActorMovieModel actorMovieFromList : actorMovies) {
                if (actorMovieFromList.getActor().getId()==actor.getId()
                    && actorMovieFromList.getMovie().getId()==movie.getId()){
                    actorMovie = actorMovieFromList;
                    isActorMoviePresent = true;
                }
            }
            if (!isActorMoviePresent) {

                genericObject.add(actorMovie);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void insertActorMovie() {
        ActorModel actor = new ActorModel(
                "Leonardo DiCaprio",
                "United States Of America",
                LocalDate.of(1974, 11, 11)
        );

        MovieModel movie = new MovieModel(
                "The Wolf of Wall Street",
                2013,
                "Drama/Comedy",
                "3h");

        ActorMovieServices actorMovieServices = new ActorMovieServices();
        actorMovieServices.addActorMovie(actor, movie);
    }
}


