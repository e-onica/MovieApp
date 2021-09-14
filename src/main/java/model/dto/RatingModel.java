package model.dto;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class RatingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stars")
    private int stars;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieModel movie;


    public RatingModel(int id, int stars, UserModel user) {
        this.id = id;
        this.stars = stars;
        this.user = user;
    }

    public RatingModel(int stars, UserModel user, MovieModel movie) {
        this.stars = stars;
        this.user = user;
        this.movie = movie;
    }

    public RatingModel(){}

    public int getId() {
        return id;
    }

    public RatingModel setId(int id) {
        this.id = id;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public RatingModel setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public UserModel getUser() {
        return user;
    }

    public RatingModel setUser(UserModel user) {
        this.user = user;
        return this;
    }
}
