package controller;

import model.dao.Configuration;
import model.dao.Generic;
import model.dto.ActorModel;
import model.dto.ActorMovieModel;
import model.dto.MovieModel;
import model.dto.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import view.ui.MovieUi;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ActorServices {

    private Generic<ActorModel> genericObject = new Generic<>();
    private ActorModel actor = new ActorModel();

    public void addActor(ActorModel actor) {
        genericObject.add(actor);
    }

    public ActorModel searchActorByName(String actorName, UserModel user) {

        ActorModel foundActor = genericObject.searchByName(actorName, actor);

        if (foundActor != null) {
            System.out.println("----------------------------------------------");
            System.out.println(foundActor);
            System.out.println("----------------------------------------------");

        } else {
            MovieUi movieUi = new MovieUi();
            movieUi.viewHomeMenu(user);
        }
        return foundActor;
    }

    public void getActorSMovies(ActorModel actor) {
        try (Session session = Configuration.getSessionFactory().openSession()) {

                    Query query =   session.createQuery(
                            " select a.movie " +
                                    " FROM MovieModel m " +
                                    "INNER JOIN ActorMovieModel a ON m.id = a.movie where a.actor="
                                    + actor.getId());
            List<MovieModel> movies  = (ArrayList<MovieModel>) query.getResultList();

            System.out.println("\n Filmography: \n");
           movies.forEach(movie -> System.out.println(movie.printMovieDetails()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
