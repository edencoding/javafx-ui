package com.edencoding;

import com.edencoding.layouts.BootstrapColumn;
import com.edencoding.layouts.BootstrapPane;
import com.edencoding.layouts.BootstrapRow;
import com.edencoding.layouts.Breakpoint;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        BootstrapPane root = new BootstrapPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setGridLinesVisible(true);
        root.addRow(makeBootstrapRow());
        root.setPadding(new Insets(15));

        primaryStage.setTitle("Responsive Layout Example");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.getIcons().add(new Image(
                getClass().getResourceAsStream("/img/EdenCodingIcon.png")
        ));

        primaryStage.show();
    }

    private BootstrapRow makeBootstrapRow() {
        BootstrapRow row = new BootstrapRow();

        BootstrapColumn column0 = new BootstrapColumn(createColoredPane(Color.YELLOW.brighter()));
        column0.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);
        BootstrapColumn column1 = new BootstrapColumn(createColoredPane(Color.RED.brighter()));
        column1.setBreakpointColumnWidth(Breakpoint.LARGE, 4);
        column1.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);
        BootstrapColumn column2 = new BootstrapColumn(createColoredPane(Color.BLUE.brighter()));
        column2.setBreakpointColumnWidth(Breakpoint.LARGE, 4);
        column2.setBreakpointColumnWidth(Breakpoint.SMALL, 6);
        column2.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);
        BootstrapColumn column3 = new BootstrapColumn(createColoredPane(Color.GREEN.brighter()));
        column3.setBreakpointColumnWidth(Breakpoint.LARGE, 4);
        column3.setBreakpointColumnWidth(Breakpoint.SMALL, 6);
        column3.setBreakpointColumnWidth(Breakpoint.XSMALL, 12);

        row.addColumn(column0);
        row.addColumn(column1);
        row.addColumn(column2);
        row.addColumn(column3);

        return row;
    }

    private AnchorPane createColoredPane(Color color) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(
                new Background(
                        new BackgroundFill(
                                color, CornerRadii.EMPTY, Insets.EMPTY
                        )
                )
        );
        return anchorPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
