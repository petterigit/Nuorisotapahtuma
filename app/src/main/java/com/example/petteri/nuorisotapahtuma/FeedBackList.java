package com.example.petteri.nuorisotapahtuma;

import java.util.ArrayList;
import java.util.List;

public class FeedBackList {
    // The array for the FeedBack-objects

    private static FeedBackList instance = new FeedBackList();
    private List<FeedBack> feedback_list = new ArrayList<FeedBack>();

    public List<FeedBack> getFeedBack_list() {
        return feedback_list;
    }

    public void removeFeedBack(int bottle_nr) {
        feedback_list.remove(bottle_nr);
    }

    public static FeedBackList getInstance () {
        return instance;
    }
    public void createFeedBack(String feedback) {
        FeedBack new_feedback = new FeedBack();
        new_feedback.setFeedBack_string(feedback);
        feedback_list.add(new_feedback);
    }

    public FeedBack getFeedBack(int i) {
        return feedback_list.get(i);
    }
}
