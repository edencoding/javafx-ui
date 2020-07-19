package com.edencoding.controllers;

import javafx.application.Application;
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
import javafx.stage.Stage;

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
    public TextField searchBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "New York", "New York")); //8,323,340
        orders.add(new Order(2, "Colorado", "Denver"));
        orders.add(new Order(3, "Missouri", "Kansas City"));
        orders.add(new Order(4, "Nebraska", "Custer"));
        orders.add(new Order(5, "Iowa", "Black Hawk"));
        orders.add(new Order(6, "Nevada", "Las Vegas"));
        orders.add(new Order(7, "California", "San Diego")); //1.54m
        orders.add(new Order(8, "Illinois", "Chicago")); //2.2m
        orders.add(new Order(9, "Massachusetts", "Boston")); //4.3m
        orders.add(new Order(10, "Vermont", "Montpellier"));
        orders.add(new Order(11, "Alberta", "Revelstoke"));
        orders.add(new Order(12, "Manitoba", "Winnipeg"));
        orders.add(new Order(13, "British Colombia", "Terrace"));

        FilteredList<Order> filteredData = new FilteredList<>(FXCollections.observableList(orders));
        exampleTable.setItems(filteredData);

        exampleTable.setRowFactory(tableView -> {
            TableRow<Order> row = new TableRow<>();
            row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), false);
            row.itemProperty().addListener((obs, oldOrder, newOrder) -> {
                boolean assignClass = filteredData.contains(newOrder) &&
                        (newOrder.getCity().equals("New York") ||
                                newOrder.getCity().equals("Boston"));

                row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), assignClass);
            });
            return row;
        });

        searchBox.textProperty().addListener((observable, oldValue, newValue) ->
                exampleTable.setItems(filterList(orders, newValue.toLowerCase()))
        );
    }

    private Predicate<Order> createPredicate(String searchText){
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(order, searchText);
        };
    }

    private ObservableList<Order> filterList(List<Order> list, String searchText){
        List<Order> filteredList = new ArrayList<>();

        for (Order order : list){
            if(searchFindsOrder(order, searchText)){
                filteredList.add(order);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchFindsOrder(Order order, String searchText){
        return (order.getCity().toLowerCase().contains(searchText)) ||
                (order.getState().toLowerCase().contains(searchText)) ||
                Integer.valueOf(order.getId()).toString().equals(searchText);
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    @FXML
    private void handleGitButtonClicked(ActionEvent event) {
        new Application() {
            @Override
            public void start(Stage stage) {
            }
        }.getHostServices().showDocument("https://github.com/edencoding/javafx-ui/");
        event.consume();
    }

    public void handleClearSearchText(ActionEvent event) {
        searchBox.setText("");
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
