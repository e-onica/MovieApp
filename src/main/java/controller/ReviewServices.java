package controller;

import model.dao.Configuration;
import model.dao.Generic;
import model.dto.MovieModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewServices {

    public void printAllReviews(int movieId){
        try (Session session = Configuration.getSessionFactory().openSession()) {
            List<String> reviews = session.createQuery(
                    "select description from ReviewModel where id_movie=" + movieId).getResultList();
            reviews.forEach(review -> System.out.println(review));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}