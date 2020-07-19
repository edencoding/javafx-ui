package com.edencoding.controllers;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MainViewController implements Initializable {

    public TableView<Order> exampleTable;
    public TableColumn<Order, Integer> orderIdColumn;
    public TableColumn<Order, String> stateColumn;
    public TableColumn<Order, String> cityColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    public static class Order {
        IntegerProperty id;
        StringProperty state;
        StringProperty city;

        public Order(Integer id, String state, String city) {
            this.id = new SimpleIntegerProperty(id);
            this.state = new SimpleStringProperty(state);
            this.city = new SimpleStringProperty(city);
        }

        public int getId() {
            return id.get();
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public String getState() {
            return state.get();
        }

        public void setState(String state) {
            this.state.set(state);
        }

        public StringProperty stateProperty() {
            return state;
        }

        public String getCity() {
            return city.get();
        }

        public void setCity(String city) {
            this.city.set(city);
        }

        public StringProperty cityProperty() {
            return city;
        }
    }
}
