package com.example.tripyaatrinew.model;

public class AboutApp {
    String appname;
    String about_app;
    String admin_name;
    String admin_phone;
    String admin_email;
    String admin_website;

    public AboutApp() {
    }

    public AboutApp(String appname, String about_app, String admin_name, String admin_phone, String admin_email, String admin_website) {
        this.appname = appname;
        this.about_app = about_app;
        this.admin_name = admin_name;
        this.admin_phone = admin_phone;
        this.admin_email = admin_email;
        this.admin_website = admin_website;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getAbout_app() {
        return about_app;
    }

    public void setAbout_app(String about_app) {
        this.about_app = about_app;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_website() {
        return admin_website;
    }

    public void setAdmin_website(String admin_website) {
        this.admin_website = admin_website;
    }
}
