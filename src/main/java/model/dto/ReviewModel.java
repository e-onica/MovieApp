package model.dto;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class ReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieModel movie;

    public ReviewModel(int id, String description, UserModel user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public ReviewModel(String description,UserModel user, MovieModel movie) {
        this.description = description;
        this.user = user;
        this.movie = movie;
    }

    public ReviewModel() {
    }

    public int getId() {
        return id;
    }

    public ReviewModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReviewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserModel getUser() {
        return user;
    }

    public ReviewModel setUser(UserModel user) {
        this.user = user;
        return this;
    }
}
