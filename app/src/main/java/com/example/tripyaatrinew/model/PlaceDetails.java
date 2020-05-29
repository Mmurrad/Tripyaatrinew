package com.example.tripyaatrinew.model;

public class PlaceDetails {
    String state_name;
    String city_name;
    String place_name;
    String address;
    String phone;
    String web;
    String area;
    String about_place;
    String image;
    String place_heading;
    String place_sub;

    public PlaceDetails() {
    }

    public PlaceDetails(String state_name, String city_name, String place_name, String address, String phone, String web,String area ,String about_place,String image,String place_heading,String place_sub) {
        this.state_name = state_name;
        this.city_name = city_name;
        this.place_name = place_name;
        this.address = address;
        this.phone = phone;
        this.web = web;
        this.area=area;
        this.about_place = about_place;
        this.image=image;
        this.place_heading=place_heading;
        this.place_sub=place_sub;
    }

    public String getPlace_sub() {
        return place_sub;
    }

    public void setPlace_sub(String place_sub) {
        this.place_sub = place_sub;
    }

    public String getPlace_heading() {
        return place_heading;
    }

    public void setPlace_heading(String place_heading) {
        this.place_heading = place_heading;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState_name() {
        return state_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getAbout_place() {
        return about_place;
    }

    public void setAbout_place(String about_place) {
        this.about_place = about_place;
    }
}
