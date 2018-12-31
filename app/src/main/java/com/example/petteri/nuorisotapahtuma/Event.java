package com.example.petteri.nuorisotapahtuma;

/* Class for Event */
public class Event {
    private String name;
    private String begins;
    private String ends;
    private String place;
    private String date;
    private String info;
    private String ages;
    private String visitors;

    /* Event contains one feedback list */
    private FeedBackList feedBackList = new FeedBackList();


    /* ToString changes default name when class is called */
    public String toString() {
        return name;
    }

    /* Constructor */
    public Event(String name, String begins, String ends, String place, String date, String info, String ages, String visitors) {
        this.name = name;
        this.begins = begins;
        this.ends = ends;
        this.place = place;
        this.date = date;
        this.info = info;
        this.ages = ages;
        this.visitors = visitors;
    }




    /* Getters and setters */
    public String getVisitors() {
        return visitors;
    }

    public void setVisitors(String visitors) {
        this.visitors = visitors;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public FeedBackList getFeedBackList () {
        return feedBackList;
    }

    public String getDate() {return date; }

    public void setDate(String date) {this.date = date; }

    public String getAges() {return ages; }

    public void setAges(String ages) {this.ages = ages; }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBegins() {
        return begins;
    }

    public void setBegins(String begins) {
        this.begins = begins;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }


}
