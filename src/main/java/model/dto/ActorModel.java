package model.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "actor", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class ActorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "actor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ActorMovieModel> actorMovieList = new ArrayList<>();

    public ActorModel(int id, String name, String country, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public ActorModel(String name, String country, LocalDate dateOfBirth) {
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public ActorModel() {
    }

    public int getId() {
        return id;
    }

    public ActorModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ActorModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ActorModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public ActorModel setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public List<ActorMovieModel> getActorMovieList() {
        return actorMovieList;
    }

    public ActorModel setActorMovieList(List<ActorMovieModel> actorMovieList) {
        this.actorMovieList = actorMovieList;
        return this;
    }

    @Override
    public String toString() {
        return name + ", " +
                country + ", " +
                dateOfBirth;

    }


}
