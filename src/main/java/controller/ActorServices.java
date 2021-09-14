package controller;

import model.dao.Configuration;
import model.dao.Generic;
import model.dto.ActorModel;
import model.dto.ActorMovieModel;
import model.dto.MovieModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ActorServices {

    private Generic<ActorModel> genericObject = new Generic<>();
    private ActorModel actor = new ActorModel();

    public void addActor(ActorModel actor) {
        genericObject.add(actor);
    }

    public ActorModel searchActorByName(String actorName) {

        ActorModel foundActor = genericObject.searchByName(actorName, actor);

        if (foundActor != null) {
            System.out.println(foundActor);

        } else {
            System.out.println("Sorry we couldn't find anything");
        }

        return foundActor;
    }

}
