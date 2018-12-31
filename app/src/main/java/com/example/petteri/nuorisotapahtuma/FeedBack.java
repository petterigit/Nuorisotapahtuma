package com.example.petteri.nuorisotapahtuma;

/* Feedback class to store feedback */
public class FeedBack {
    private String FeedBack_string;
    private String Rating;
    private String GivenBy;

    /* toString changes default name when class is called
    *  Used to show feedback in one line
    *  */
    public String toString() {
        return FeedBack_string + ", " + Rating + " Tähteä " + ", " + GivenBy;
    }

    /* Getters and setters */
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
