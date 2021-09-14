package controller;

import model.dao.Generic;
import model.dto.MovieModel;
import model.dto.RatingModel;

public class RatingServices {
    Generic<RatingModel> genericObject = new Generic<>();

    public void addRating(RatingModel rating){

        genericObject.add(rating);
    }
}
