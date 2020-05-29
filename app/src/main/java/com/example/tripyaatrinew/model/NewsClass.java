package com.example.tripyaatrinew.model;

public class NewsClass {
    String news;
    String name;
    String description;
    String image;

    public NewsClass() {
    }

    public NewsClass(String news,String name,String description,String image) {
        this.news = news;
        this.name = name;
        this.description=description;
        this.image=image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
