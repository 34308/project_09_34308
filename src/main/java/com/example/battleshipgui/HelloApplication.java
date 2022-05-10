package com.example.battleshipgui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HelloApplication extends Application {
    EventHandler<MouseEvent> eventHandler;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        stage.setTitle("Hello!");
        ArrayList<Rectangle> rectangles=new ArrayList<Rectangle>();


        Scene scene = new Scene(fxmlLoader.load() ,340, 380);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}