package com.edencoding.models;

import javafx.beans.property.*;

/**
 * @author Ed Eden-Rump
 * @created 20/07/2020 - 16:53
 * @project
 **/
public class Order {
    IntegerProperty id;
    StringProperty state;
    StringProperty city;
    BooleanProperty priorityShipping;
    IntegerProperty courierId;
    DoubleProperty weight;
    IntegerProperty driverId;

    public Order(Integer id, String state, String city,
                 Boolean priorityShipping, Integer courier, Double weight,
                 Integer driverId) {
        this.id = new SimpleIntegerProperty(id);
        this.state = new SimpleStringProperty(state);
        this.city = new SimpleStringProperty(city);
        this.priorityShipping = new SimpleBooleanProperty(priorityShipping);
        this.courierId = new SimpleIntegerProperty(courier);
        this.weight = new SimpleDoubleProperty(weight);
        this.driverId = new SimpleIntegerProperty(driverId);
    }

    public int getDriverId() {
        return driverId.get();
    }

    public void setDriverId(int driverId) {
        this.driverId.set(driverId);
    }

    public IntegerProperty driverIdProperty() {
        return driverId;
    }

    public BooleanProperty priorityShippingProperty() {
        return priorityShipping;
    }

    public IntegerProperty courierProperty() {
        return courierId;
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty stateProperty() {
        return state;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public boolean isPriorityShipping() {
        return priorityShipping.get();
    }

    public void setPriorityShipping(boolean priorityShipping) {
        this.priorityShipping.set(priorityShipping);
    }

    public int getCourierId() {
        return courierId.get();
    }

    public void setCourierId(int courierId) {
        this.courierId.set(courierId);
    }

    public IntegerProperty courierIdProperty() {
        return courierId;
    }

    public double getWeight() {
        return weight.get();
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }
}
