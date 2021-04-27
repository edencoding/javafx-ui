package com.edencoding.layouts;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.List;

public class BootstrapPane extends GridPane {

    List<BootstrapRow> rows;

    public BootstrapPane() {
        super();
        setColumnConstraints();
    }

    //Remove all current columns and create 12 equally sized columns for layout.
    private void setColumnConstraints() {
        getColumnConstraints().clear();
        double width = 100.0 / 12.0;
        for (int i = 0; i < 12; i++) {
            getColumnConstraints().add(new ColumnConstraints(width));
        }
    }

    public void addBootstrapRow(BootstrapRow row){
        rows.add(row);
    }


}
