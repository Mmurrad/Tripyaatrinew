package com.example.tripyaatrinew.model;

public class Booking {
    String name;
    String email;
    String phone;
    String starting;
    String visit;
    String person;

    public Booking() {
    }

    public Booking(String name, String email, String phone, String starting, String visit, String person) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.starting = starting;
        this.visit = visit;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
