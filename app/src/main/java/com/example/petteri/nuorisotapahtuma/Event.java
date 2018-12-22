package com.example.petteri.nuorisotapahtuma;

public class Event {
    private String name;
    private String begins;
    private String ends;
    private String place;
    private String date;
    private int agesFrom;
    private int agesTo;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String info;

    /* Getters and setters */

    public String getDate() {return date; }

    public void setDate(String date) {this.date = date; }

    public int getAgesFrom() {return agesFrom; }

    public void setAgesFrom(int agesFrom) {this.agesFrom = agesFrom; }

    public int getAgesTo() {return agesTo; }

    public void setAgesTo(int agesTo) { this.agesTo = agesTo; }

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
