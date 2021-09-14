package model.dto;

import javax.persistence.*;

@Entity
@Table(name = "user_detail")
public class UserDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    public UserDetailModel() {
    }

    public UserDetailModel(int id, String country, int age, String gender) {
        this.id = id;
        this.country = country;
        this.age = age;
        this.gender = gender;
    }

    public UserDetailModel(String country, int age, String gender) {
        this.country = country;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public UserDetailModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserDetailModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserDetailModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserDetailModel setGender(String gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "UserDetailModel{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

}
