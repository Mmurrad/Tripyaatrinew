package com.example.tripyaatrinew.model;

public class Lati_Longi_Class {
    String place_name;
    String longitude;
    String latitude;

    public Lati_Longi_Class() {
    }

    public Lati_Longi_Class(String place_name, String longitude, String latitude) {
        this.place_name = place_name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
