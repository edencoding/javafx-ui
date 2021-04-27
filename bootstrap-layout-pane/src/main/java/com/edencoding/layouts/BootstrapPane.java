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

    private void setColumnConstraints() {
        //Remove all current columns.
        getColumnConstraints().clear();

        //Create 12 equally sized columns for layout
        double width = 100.0 / 12.0;
        for (int i = 0; i < 12; i++) {
            getColumnConstraints().add(new ColumnConstraints(width));
        }
    }

    /**
     * Add a BootstrapRow to the layout.
     * New BootstrapRows will automatically start on a new row.
     *
     * @param row the row to be added
     */
    public void addBootstrapRow(BootstrapRow row) {
        rows.add(row);
    }
}
