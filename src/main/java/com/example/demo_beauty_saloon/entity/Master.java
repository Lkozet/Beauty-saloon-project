package com.example.demo_beauty_saloon.entity;


/**
 * Master entity
 *
 */
public class Master {

    private long id;
    private String login;
    private String password;
    private String nameEn;
    private String nameRu;
    private double rating;
    private String informationEn;
    private String informationRu;
    private long serviceId;

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }


    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public String getInformationEn() {
        return informationEn;
    }

    public void setInformationEn(String informationEn) {
        this.informationEn = informationEn;
    }


    public String getInformationRu() {
        return informationRu;
    }

    public void setInformationRu(String informationRu) {
        this.informationRu = informationRu;
    }
}
