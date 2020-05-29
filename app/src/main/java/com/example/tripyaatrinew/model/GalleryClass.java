package com.example.tripyaatrinew.model;

public class GalleryClass {

    String image;
    String place_type;
    String place_name;

    public GalleryClass() {
    }

    public GalleryClass(String image, String place_type,String place_name) {
        this.image = image;
        this.place_type = place_type;
        this.place_name=place_name;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlace_type() {
        return place_type;
    }

    public void setPlace_type(String place_type) {
        this.place_type = place_type;
    }
}
