package com.example.battleshipgui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.paint.Color.RED;

public class HelloApplication extends Application {
    EventHandler<MouseEvent> eventHandler;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        stage.setTitle("Hello!");
        ArrayList<Rectangle> rectangles=new ArrayList<Rectangle>();


       // GridPane gridPane = new GridPane();
       // Canvas canvas;


       /* for(int j=0;j<100;j++){
            rectangles.add(new Rectangle(20,20,Color.GRAY));
        }
        eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    System.out.println("Hello World"+GridPane.getColumnIndex((Node)e.getSource()));


                }
        };
        int j=0;
        for (int x=0;x<10;x++){
            for (int i=0;i<10;i++){
                gridPane.addEventFilter(MOUSE_CLICKED,eventHandler);
                gridPane.add(rectangles.get(j),x,i);
                j++;
            }
        }

        gridPane.setGridLinesVisible(true);
        Scene scene = new Scene(gridPane, 520, 440);
        stage.setScene(scene);
        stage.show();

        */

        Scene scene = new Scene(fxmlLoader.load() ,340, 380);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}