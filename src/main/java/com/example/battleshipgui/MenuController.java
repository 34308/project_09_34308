package com.example.battleshipgui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {

    public void changeStage(ActionEvent event ) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PlaceShips.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene=new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void PvPStart(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PlaceShipsPVP.fxml"));
        Scene scene = new Scene(fxmlLoader.load() ,1000, 1000);
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void goToAiBattle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aivsai-arena.fxml"));
        Scene scene = new Scene(fxmlLoader.load() ,1200, 1000);
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent event) {
        Platform.exit();
    }
}
