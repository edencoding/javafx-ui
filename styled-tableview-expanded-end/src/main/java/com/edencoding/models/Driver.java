package com.edencoding.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ed Eden-Rump
 * @created 20/07/2020 - 19:54
 * @project edencoding-examples
 **/
public class Driver {

    StringProperty name;
    IntegerProperty rating;
    String photoFileName;

    public Driver(String name, Integer rating, String photoFileName) {
        this.name = new SimpleStringProperty(name);
        this.rating = new SimpleIntegerProperty(rating);
        this.photoFileName = photoFileName;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getRating() {
        return rating.get();
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }
}
