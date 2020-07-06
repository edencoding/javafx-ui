package com.edencoding.simple_ui.controllers;

import com.edencoding.simple_ui.config.Defaults;
import com.edencoding.simple_ui.models.ToastContainer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private StackPane contentContainer;

    @FXML
    private VBox dragTarget;

    private ToastContainer toastContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addToastContainer();
        enableDragboardFileLoad();
    }

    private void addToastContainer() {
        toastContainer = new ToastContainer();
        contentContainer.getChildren().add(toastContainer);
    }

    private void enableDragboardFileLoad() {
        dragTarget.setOnDragDropped(this::handleOnDragDropped);
        dragTarget.setOnDragEntered(this::handleDragEntered);
        dragTarget.setOnDragExited(this::handleDragExit);
        dragTarget.setOnDragOver(this::handleDragOver);
    }

    private void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getGestureSource() != dragTarget
                && dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.COPY);
        }
        dragEvent.consume();
    }

    private void handleOnDragDropped(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        dragTarget.getStyleClass().remove(Defaults.DRAG_ALLOWED);
        toastContainer.displayToast("This is where we'd load the file...");
        dragEvent.setDropCompleted(true);
        dragEvent.consume();
    }

    private void handleDragExit(DragEvent dragEvent) {
        dragTarget.getStyleClass().remove(Defaults.DRAG_ALLOWED);
    }

    private void handleDragEntered(DragEvent dragEvent) {
        dragTarget.getStyleClass().add(Defaults.DRAG_ALLOWED);
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    @FXML
    private void handleLaunchGithubWebsiteButtonClicked(ActionEvent event) {
        new Application() {
            @Override
            public void start(Stage stage) {
            }
        }.getHostServices().showDocument("https://github.com/nested-space/OutlookCalendarMetrics");
        event.consume();
    }
}
