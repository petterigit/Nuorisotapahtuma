package com.example.petteri.nuorisotapahtuma;

public class FeedBack {
    private String FeedBack_string;
    private String Rating;
    private String GivenBy;

    public String toString() {
        return FeedBack_string + ", " + Rating + " Tähteä " + ", " + GivenBy;
    }

    public String getFeedBack_string() {
        return FeedBack_string;
    }

    public void setFeedBack_string(String feedBack_string) {
        FeedBack_string = feedBack_string;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getGivenBy() {
        return GivenBy;
    }

    public void setGivenBy(String givenBy) {
        GivenBy = givenBy;
    }



}
