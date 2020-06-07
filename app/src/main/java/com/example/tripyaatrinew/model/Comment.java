package com.example.tripyaatrinew.model;

public class Comment {
    float rating;
    String comment;
    String place;

    public Comment() {
    }

    public Comment(float rating, String comment,String place) {
        this.rating = rating;
        this.comment = comment;
        this.place=place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
