package model.dto;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_detail")
    private UserDetailModel userDetailModel;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ReviewModel> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<RatingModel> ratings = new ArrayList<>();


    public UserModel() {
    }

    public UserModel(int id, String email, String password, UserDetailModel userDetailModel) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userDetailModel = userDetailModel;
    }

    public UserModel(String email, String password, UserDetailModel userDetailModel) {
        this.email = email;
        this.password = password;
        this.userDetailModel = userDetailModel;
    }

    public List<ReviewModel> getReviews() {
        return reviews;
    }

    public UserModel setReviews(List<ReviewModel> reviews) {
        this.reviews = reviews;
        return this;
    }

    public List<RatingModel> getRatings() {
        return ratings;
    }

    public UserModel setRatings(List<RatingModel> ratings) {
        this.ratings = ratings;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailModel getUserDetailModel() {
        return userDetailModel;
    }

    public UserModel setUserDetailModel(UserDetailModel userDetailModel) {
        this.userDetailModel = userDetailModel;
        return this;
    }

    @Override
    public String toString() {
        return "username " + email + "\n" +
                userDetailModel.toString() ;
    }


}
