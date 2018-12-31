package com.example.petteri.nuorisotapahtuma;

import java.util.ArrayList;
import java.util.List;

public class FeedBackList {
    // The array for the FeedBack-objects


    private ArrayList<FeedBack> feedback_list = new ArrayList<FeedBack>();

    public ArrayList<FeedBack> getFeedBack_list() {
        return feedback_list;
    }

    public void removeFeedBack(int bottle_nr) {
        feedback_list.remove(bottle_nr);
    }


    public void createFeedBack(String feedback, String rating) {
        FeedBack new_feedback = new FeedBack();
        new_feedback.setFeedBack_string(feedback);
        new_feedback.setRating(rating);
        new_feedback.setGivenBy("Anonymous");
        feedback_list.add(new_feedback);
    }

    public FeedBack getFeedBack(int i) {
        return feedback_list.get(i);
    }
}
