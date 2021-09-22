package com.example.demo_beauty_saloon.entity;

/**
 * Service entity
 *
 */
public class Service {

    private long id;
    private String nameEn;
    private String nameRu;
    private String descriptionEn;
    private String descriptionRu;
    private String timeslots;
    private String availability;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }


    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }


    public String getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(String timeslots) {
        this.timeslots = timeslots;
    }


    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
