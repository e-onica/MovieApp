import controller.*;
import model.dao.Configuration;
import model.dto.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.DurationType;
import view.ui.MovieUi;
import view.ui.UserUi;

import javax.persistence.Query;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Session session = Configuration.getSessionFactory().openSession();
//
//        try(Session session = Configuration.getSessionFactory().openSession()){
////            List<UserModel> users =
//            UserModel user1 = new UserModel();
//            Transaction transaction = session.beginTransaction();
//                    Query query = session.createQuery(" from " + user1.getClass().getName());
//            query.getResultList().forEach(user -> System.out.println(user));
//            transaction.commit();
//        }catch(Exception e){
//            e.printStackTrace();
//        }



        UserUi userUi = new UserUi();
        userUi.logIn();

//        MovieServices movieServices = new MovieServices();
//        movieServices.searchMovieByName("Batman Begins");
//        ActorServices actorServices = new ActorServices();
//        actorServices.searchActorByName("Antonio Banderas");






    }



}
