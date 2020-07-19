package com.edencoding.models;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MetricsContainer extends VBox {

    private final String initialMessage;

    public MetricsContainer(String initialMessage) {
        this.initialMessage = initialMessage;

        setContainerStyle();
        resetContentToInitialMessage();
    }

    private void resetContentToInitialMessage() {
        getChildren().clear();
        getChildren().add(createStyledLabel(initialMessage));
    }

    private void setContainerStyle() {
        getStyleClass().add("content-pane");
    }

    private Label createStyledLabel(String message) {
        Label label = new Label(message);
        label.getStyleClass().add("title-text");
        return label;
    }
}
