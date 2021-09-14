package model.dto;

import javax.persistence.*;

@Entity
@Table(name = "actor_movie")
public class ActorMovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ActorModel actor;

    @ManyToOne
    private MovieModel movie;


    public ActorMovieModel(ActorModel actor, MovieModel movie) {
        this.actor = actor;
        this.movie = movie;
    }

    public ActorMovieModel(){}

    public int getId() {
        return id;
    }

    public ActorMovieModel setId(int id) {
        this.id = id;
        return this;
    }

    public ActorModel getActor() {
        return actor;
    }

    public ActorMovieModel setActor(ActorModel actor) {
        this.actor = actor;
        return this;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public ActorMovieModel setMovie(MovieModel movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public String toString() {
        return "ActorMovieModel{" +
                "id=" + id +
                ", actor=" + actor +
                ", movie=" + movie +
                '}';
    }
}
