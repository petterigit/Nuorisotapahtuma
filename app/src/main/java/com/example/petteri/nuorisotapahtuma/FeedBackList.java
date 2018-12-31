package com.example.petteri.nuorisotapahtuma;

import java.util.ArrayList;
import java.util.List;

/* Class for list of feedbacks */
public class FeedBackList {


    private ArrayList<FeedBack> feedback_list = new ArrayList<FeedBack>();

    /* Removes feedback at given index
       Not used
     */
    public void removeFeedBack(int index) {
        feedback_list.remove(index);
    }

    /* Create constructor for feedback */
    public void createFeedBack(String feedback, String rating) {
        FeedBack new_feedback = new FeedBack();
        new_feedback.setFeedBack_string(feedback);
        new_feedback.setRating(rating);
        new_feedback.setGivenBy("Anonymous");
        feedback_list.add(new_feedback);
    }

    /* Getters */
    public FeedBack getFeedBack(int i) {
        return feedback_list.get(i);
    }

    public ArrayList<FeedBack> getFeedBack_list() {
        return feedback_list;
    }
}
