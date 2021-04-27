package com.edencoding;

import com.edencoding.layouts.BootstrapPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = new BootstrapPane();
        primaryStage.setTitle("Responsive Layout Example");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.getIcons().add(new Image(
                getClass().getResourceAsStream("/img/EdenCodingIcon.png")
        ));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
