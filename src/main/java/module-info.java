module com.example.battleshipgui {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;


    opens com.example.battleshipgui to javafx.fxml;
    exports com.example.battleshipgui;
}