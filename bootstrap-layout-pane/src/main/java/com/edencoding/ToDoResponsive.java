package com.edencoding;

import com.edencoding.layouts.BootstrapColumn;
import com.edencoding.layouts.BootstrapPane;
import com.edencoding.layouts.BootstrapRow;
import com.edencoding.layouts.Breakpoint;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ToDoResponsive extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BootstrapPane root = makeView();
        root.getStylesheets().add(
                getClass().getResource("/css/styles.css").toExternalForm());

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        primaryStage.setTitle("Responsive ToDo List");
        primaryStage.setScene(new Scene(scrollPane, 300, 650));

        primaryStage.getIcons().add(new Image(
                getClass().getResourceAsStream("/img/EdenCodingIcon.png")
        ));

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            boolean decreasing;
            @Override
            public void run() {
                if(primaryStage.getWidth() > 1150) decreasing = true;
                if(primaryStage.getWidth() < 350) decreasing = false;

                if (decreasing) {
                    primaryStage.setWidth(primaryStage.getWidth() - 5);
                } else {
                    primaryStage.setWidth(primaryStage.getWidth() + 5);
                }
            }

        }, 2000, 25);

        primaryStage.show();
    }

    private BootstrapPane makeView() {

        BootstrapPane bootstrapPane = new BootstrapPane();
        bootstrapPane.setPadding(new Insets(15));
        bootstrapPane.getStyleClass().add("background");
        bootstrapPane.setVgap(25);
        bootstrapPane.setHgap(25);

        BootstrapRow row = new BootstrapRow();

        row.addColumn(createTitleColumn());
        row.addColumn(createColumn(createWidget("Do now", seedUrgentImportant())));
        row.addColumn(createColumn(createWidget("Do later", seedImportant())));
        row.addColumn(createColumn(createWidget("Delegate", seedUrgent())));
        row.addColumn(createColumn(createWidget("Avoid if possible", seedUnimportant())));

        bootstrapPane.addRow(row);
        return bootstrapPane;
    }

    private BootstrapColumn createAddItemColumn() {
        return null;
    }

    private BootstrapColumn createTitleColumn() {
        Label title = new Label("To Do List");

        //style
        title.getStyleClass().add("app-title");

        //add to column
        BootstrapColumn titleColumn = new BootstrapColumn(title);
        titleColumn.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);

        return titleColumn;
    }

    private BootstrapColumn createColumn(Node widget) {
        BootstrapColumn column = new BootstrapColumn(widget);
        column.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);
        column.setBreakpointColumnWidth(Breakpoint.SMALL, 6);
        column.setBreakpointColumnWidth(Breakpoint.LARGE, 3);
        return column;
    }

    private Node createWidget(String title, List<ToDo> items) {
        VBox widget = new VBox();
        widget.getStyleClass().add("widget");
        widget.getChildren().add(new Label(title));
        widget.getChildren().add(new Separator(Orientation.HORIZONTAL));

        for (ToDo todo : items) {
            widget.getChildren().add(createItem(todo));
        }
        return widget;
    }

    private Node createItem(ToDo todo) {
        HBox item = new HBox();
        item.getStyleClass().add("item");

        HBox left = new HBox();
        HBox.setHgrow(left, Priority.ALWAYS);
        left.getChildren().add(new Label(todo.title));

        HBox right = new HBox();
        right.setSpacing(15);
        right.setMinWidth(80);
        right.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(right, Priority.NEVER);
        right.getChildren().add(new Label(todo.dueBy.format(DateTimeFormatter.ofPattern("dd-MMM"))));
        right.getChildren().add(new Circle(5, todo.status));

        item.getChildren().addAll(left, right);
        return item;
    }

    private List<ToDo> seedUrgentImportant() {
        return Arrays.asList(
                new ToDo("Feed my kids", LocalDate.now(), Color.GREEN),
                new ToDo("Gym", LocalDate.now().plusDays(1), Color.ORANGERED),
                new ToDo("Weekly shop", LocalDate.now().plusDays(3), Color.RED)

        );
    }

    private List<ToDo> seedImportant() {
        return Arrays.asList(
                new ToDo("Create bootstrap responsive grid", LocalDate.now(), Color.GREEN),
                new ToDo("Write EdenCoding article", LocalDate.now(), Color.ORANGERED)
        );
    }

    private List<ToDo> seedUrgent() {
        return Arrays.asList(
                new ToDo("Schedule dental work", LocalDate.now(), Color.GREEN),
                new ToDo("Book hotel for conference", LocalDate.now(), Color.GREEN)
        );
    }

    private List<ToDo> seedUnimportant() {
        return Arrays.asList(
                new ToDo("Drink more water", LocalDate.now(), Color.RED),
                new ToDo("Buy stuff you don't need", LocalDate.now(), Color.GREEN),
                new ToDo("Check Facebook", LocalDate.now(), Color.GREEN)
        );
    }

    private class ToDo {
        String title;
        LocalDate dueBy;
        Color status;

        ToDo(String title, LocalDate dueBy, Color status) {
            this.title = title;
            this.dueBy = dueBy;
            this.status = status;
        }
    }
}
