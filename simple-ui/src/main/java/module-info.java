module javafxui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.edencoding to javafx.fxml;
    opens com.edencoding.controllers to javafx.fxml;

    exports com.edencoding;
}