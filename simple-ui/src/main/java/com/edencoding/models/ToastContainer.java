package com.edencoding.models;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ToastContainer extends VBox {

    public ToastContainer(){
        setSpacing(10);
        setPadding(new Insets(25));
        setAlignment(Pos.BOTTOM_RIGHT);
    }

    public void displayToast(String message){
        Label errorLabel = new Label(message);
        errorLabel.setWrapText(true);
        errorLabel.getStyleClass().add("error-label");
        errorLabel.setOpacity(0);
        this.getChildren().add(errorLabel);

        double fadeTime = 500;
        double messageDisplayTime = 2000;

        Timeline displayErrorMessage = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(errorLabel.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(fadeTime), new KeyValue(errorLabel.opacityProperty(), 1)),
                new KeyFrame(Duration.millis(fadeTime + messageDisplayTime), new KeyValue(errorLabel.opacityProperty(), 1)),
                new KeyFrame(Duration.millis(fadeTime * 2 + messageDisplayTime), new KeyValue(errorLabel.opacityProperty(), 0))
        );
        displayErrorMessage.setOnFinished(event -> this.getChildren().remove(errorLabel));
        displayErrorMessage.playFromStart();

    }
}
