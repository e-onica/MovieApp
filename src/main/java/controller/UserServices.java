package controller;

import model.dao.Configuration;
import model.dao.Generic;
import model.dto.ActorModel;
import model.dto.MovieModel;
import model.dto.RatingModel;
import model.dto.UserModel;
import org.hibernate.Session;

import java.util.List;

public class UserServices {
    Generic<UserModel> genericObject = new Generic<>();

    public void addUser(UserModel user) {
        genericObject.add(user);
    }

//    public void viewProfileDetails(UserModel user){
//        try (Session session = Configuration.getSessionFactory().openSession()) {
//            UserModel userDetails =
//                    session.createQuery("from UserModel").getSingleResult();
//
//            System.out.println();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }





}
