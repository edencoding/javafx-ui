package com.edencoding.controllers;

import com.edencoding.models.Courier;
import com.edencoding.models.Driver;
import com.edencoding.models.Order;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class MainViewController implements Initializable {

    public TableView<Order> exampleTable;
    public TableColumn<Order, Integer> orderIdColumn;
    public TableColumn<Order, Boolean> shipTypeColumn;
    public TableColumn<Order, String> courierColumn;
    public TableColumn<Order, String> costColumn;
    public TableColumn<Order, Integer> driverColumn;

    private final DecimalFormat currency = new DecimalFormat("$##0.00");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        shipTypeColumn.setCellValueFactory(cellData -> cellData.getValue().priorityShippingProperty());
        driverColumn.setCellValueFactory(cellData -> cellData.getValue().driverIdProperty().asObject());

        costColumn.setCellValueFactory(cellData -> {
            double priorityMultiplier = cellData.getValue().isPriorityShipping() ? 1.1d : 1d;
            double cost = cellData.getValue().weightProperty().get() * priorityMultiplier;
            return new SimpleStringProperty(currency.format(cost));
        });

        courierColumn.setCellValueFactory(cellData -> {
            Courier courier = getCourier(cellData.getValue().getCourierId());
            return courier.nameProperty();
        });

        shipTypeColumn.setCellFactory(col -> {
            TableCell<Order, Boolean> cell = new TableCell<>();

            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null && newVal) {
                    HBox centreBox = new HBox();
                    centreBox.setAlignment(Pos.CENTER);
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/img/important.png")));
                    imageView.setFitHeight(25);
                    imageView.setPreserveRatio(true);
                    centreBox.getChildren().add(imageView);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(centreBox));
                }
            });

            return cell;
        });

        driverColumn.setCellFactory(col -> {
            TableCell<Order, Integer> cell = new TableCell<>();

            cell.itemProperty().addListener((observableValue, o, newValue) -> {
                if (newValue != null) {
                    Driver driver = getDriver(newValue);

                    GridPane trackingDetailsHolder = new GridPane();
                    trackingDetailsHolder.setHgap(5);
                    ImageView driverPicture = new ImageView(new Image(getClass().getResourceAsStream(driver.getPhotoFileName())));
                    driverPicture.setPreserveRatio(true);
                    driverPicture.setFitHeight(30d);
                    GridPane.setRowSpan(driverPicture, 2);
                    trackingDetailsHolder.getChildren().add(driverPicture);

                    Label driverStatus = new Label();
                    driverStatus.textProperty().bind(driver.nameProperty());
                    GridPane.setColumnIndex(driverStatus, 1);
                    trackingDetailsHolder.getChildren().add(driverStatus);

                    HBox rating = new HBox();
                    for (int i = 0; i < driver.getRating(); i++) {
                        rating.getChildren().add(
                                new ImageView(new Image(getClass().getResourceAsStream("/img/gold_star.png")))
                        );
                    }

                    for (int i = 0; i < (5 - driver.getRating()); i++) {
                        rating.getChildren().add(
                                new ImageView(new Image(getClass().getResourceAsStream("/img/black_star.png")))
                        );
                    }
                    GridPane.setConstraints(rating, 1, 1);
                    trackingDetailsHolder.getChildren().add(rating);

                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(trackingDetailsHolder));
                }
            });
            return cell;
        });

        List<Order> orders = exampleOrders();
        exampleTable.setItems(FXCollections.observableList(orders));
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

    Driver getDriver(int id) {
        return exampleDrivers().get(id);
    }

    Courier getCourier(int id) {
        return exampleCouriers().get(id);
    }

    Map<Integer, Driver> exampleDrivers() {
        Map<Integer, Driver> courierMap = new HashMap<>();
        courierMap.put(1, new Driver("Ellen Jowels", 4, "/img/driver1.png"));
        courierMap.put(2, new Driver("Eric Northope", 2, "/img/driver2.png"));
        courierMap.put(3, new Driver("Stella Gerrard", 3, "/img/driver3.png"));
        courierMap.put(4, new Driver("Stanley Horbright", 3, "/img/driver4.png"));
        return courierMap;
    }

    Map<Integer, Courier> exampleCouriers() {
        Map<Integer, Courier> courierMap = new HashMap<>();
        courierMap.put(0, new Courier(0, "FedEx"));
        courierMap.put(1, new Courier(1, "ParcelForce"));
        return courierMap;
    }

    List<Order> exampleOrders() {
        return Arrays.asList(
                new Order(1, "New York", "New York", false, 0, 7d, 1),
                new Order(2, "Colorado", "Denver", true, 1, 7d, 2),
                new Order(3, "Missouri", "Kansas City", false, 0, 15d, 3),
                new Order(4, "Nebraska", "Custer", true, 0, 3d, 4));
    }

}
