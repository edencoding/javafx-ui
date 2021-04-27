package com.edencoding.layouts;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

public class BootstrapPane extends GridPane {

    private final List<BootstrapRow> rows = new ArrayList<>();
    private int maxDepth;
    private Breakpoint currentWindowSize = Breakpoint.XSMALL;

    public BootstrapPane() {
        super();
        setAlignment(Pos.CENTER);
        setColumnConstraints();
        setWidthEventHandlers();
    }

    private void setWidthEventHandlers() {
        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            Breakpoint newBreakpoint = Breakpoint.XSMALL;
            if (newValue.doubleValue() > 576) newBreakpoint = Breakpoint.SMALL;
            if (newValue.doubleValue() > 768) newBreakpoint = Breakpoint.MEDIUM;
            if (newValue.doubleValue() > 992) newBreakpoint = Breakpoint.LARGE;
            if (newValue.doubleValue() > 1200) newBreakpoint = Breakpoint.XLARGE;

            if (newBreakpoint != currentWindowSize) {
                currentWindowSize = newBreakpoint;
                calculateNodePositions();
                System.out.println("Changed to: " + newBreakpoint);
            }
        });
    }

    private void setColumnConstraints() {
        //Remove all current columns.
        getColumnConstraints().clear();

        //Create 12 equally sized columns for layout
        double width = 100.0 / 12.0;
        for (int i = 0; i < 12; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(width);
            getColumnConstraints().add(columnConstraints);
        }
    }

    private void setRowConstraints() {
        getRowConstraints().clear();

        //TODO: make more flexible, so doesn't have to take up 100% height
        double height = 100.0 / maxDepth;
        for (int i = 0; i < maxDepth; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(height);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void calculateNodePositions() {
        int currentGridPaneRow = 0;
        for (BootstrapRow row : rows) {
            currentGridPaneRow += calculateRowPositions(row, currentGridPaneRow);
        }
        maxDepth = currentGridPaneRow;
        setRowConstraints();
    }

    private int calculateRowPositions(BootstrapRow row, int currentGridPaneRow) {
        int inputRow = currentGridPaneRow;
        if (row.getColumns().isEmpty()) return 0;

        int currentGridPaneColumn = 0; //start in the first column
        for (BootstrapColumn column : row.getColumns()) {
            int contentWidth = column.getColumnWidth(currentWindowSize);
            if (currentGridPaneColumn + contentWidth > 12) {
                currentGridPaneRow++;
                currentGridPaneColumn = 0;
            }

            GridPane.setConstraints(
                    column.getContent(),
                    currentGridPaneColumn,
                    currentGridPaneRow,
                    contentWidth,
                    1
            );

            currentGridPaneColumn += contentWidth;

        }
        return currentGridPaneRow - inputRow + 1;
    }

    /**
     * Add a BootstrapRow to the layout.
     * New BootstrapRows will automatically start on a new row.
     *
     * @param row the row to be added
     */
    public void addRow(BootstrapRow row) {
        if (rows.contains(row)) return; //prevent duplicate children error

        rows.add(row);
        calculateNodePositions();

        for (BootstrapColumn column : row.getColumns()) {
            getChildren().add(column.getContent());
            GridPane.setFillWidth(column.getContent(), true);
            GridPane.setFillHeight(column.getContent(), true);
        }
    }

    /**
     * Remove a BootstrapRow from the layout.
     *
     * @param row the row to be removed
     */
    public void removeRow(BootstrapRow row) {
        rows.remove(row);
        calculateNodePositions();

        for (BootstrapColumn column : row.getColumns()) {
            getChildren().remove(column.getContent());
        }
    }
}
