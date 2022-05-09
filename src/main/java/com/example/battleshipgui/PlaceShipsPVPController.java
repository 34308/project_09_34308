package com.example.battleshipgui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.PKCS12Attribute;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.GRAY;

public class PlaceShipsPVPController {


    public GridPane player1;
    public GridPane player2;
    public CheckBox checkbox1;
    public CheckBox checkbox2;
    public Button startButton;
    public GridPane sizeBoard1;
    public GridPane sizeBoard2;
    Image image = new Image("cursor1.png");
    boolean p1 = true;
    List<Rectangle> rectangles1 = new ArrayList<>();
    List<Rectangle> rectangles2 = new ArrayList<>();
    int it = 0;
    TwoPlayersBoard board = new TwoPlayersBoard();
    TwoPlayersBoard board2 = new TwoPlayersBoard();
    int dir = 1;
    int i = 0;
    String sizeColor="#C3D120";
    public void colorSize1(){
        sizeBoard1.getChildren().get(0).setStyle("-fx-background-color: "+sizeColor);
        sizeBoard1.getChildren().get(1).setStyle("-fx-background-color:  "+sizeColor);
        if(i>=3){
            sizeBoard1.getChildren().get(2).setStyle("-fx-background-color: "+sizeColor);
            if(i>=6){
                sizeBoard1.getChildren().get(3).setStyle("-fx-background-color: "+sizeColor);
            }
            if(i>=8){
                sizeBoard1.getChildren().get(4).setStyle("-fx-background-color:  "+sizeColor);
                sizeBoard1.getChildren().get(5).setStyle("-fx-background-color:  "+sizeColor);
            }
        }
    }
    public void colorSize2(){
        sizeBoard2.getChildren().get(0).setStyle("-fx-background-color:  "+sizeColor);
        sizeBoard2.getChildren().get(1).setStyle("-fx-background-color:  "+sizeColor);
        if(i>=3){
            sizeBoard2.getChildren().get(2).setStyle("-fx-background-color:  "+sizeColor);
            if(i>=6){
                sizeBoard2.getChildren().get(3).setStyle("-fx-background-color: "+sizeColor);
            }
            if(i>=8){
                sizeBoard2.getChildren().get(4).setStyle("-fx-background-color:  "+sizeColor);
                sizeBoard2.getChildren().get(5).setStyle("-fx-background-color: "+sizeColor);
            }
        }

    }
    public void setCursor() {
        image = new Image("cursor" + dir + ".png");
        player1.getScene().getRoot().setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
        colorSize2();
        colorSize1();

    }
    public void resetSize1(){
        for (Node n : sizeBoard1.getChildren()) {
            n.setStyle("");
        }
        colorSize1();
    }
    public void resetSize2(){
        for (Node n : sizeBoard2.getChildren()) {
            n.setStyle("");
        }
        colorSize2();
    }
    public void placeShip(MouseEvent e) {
        crateRectangles();
        if (e.getButton() == MouseButton.SECONDARY) {
            dir++;
            if (dir > 4) {
                dir = 1;
            }
            image = new Image("cursor" + dir + ".png");
            if (p1) {
                player1.getScene().getRoot().setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
            } else {
                player2.getScene().getRoot().setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
            }

        }
        if (e.getButton() == MouseButton.PRIMARY) {
            Node target = (Node) e.getTarget();
            // traverse towards root until userSelectionGrid is the parent node

            Integer colIndex = GridPane.getColumnIndex(target);
            Integer rowIndex = GridPane.getRowIndex(target);
            if (colIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {

                //System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());

                //wybieranie poszczególnych statków zaczynając od 2-poziomowych po ich ilości 2-4, 3-3 4-2 6-1
                if (i < 4) {
                    if (p1) {
                        colorSize1();
                        if (board.placeTheShip(i, 2, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    } else {
                        colorSize2();
                        if (board2.placeTheShip(i, 2, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    }

                }
                if (i >= 4 && i < 7) {
                    if (p1) {
                        colorSize1();
                        if (board.placeTheShip(i, 3, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    } else {
                        colorSize2();
                        if (board2.placeTheShip(i, 3, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    }
                }
                if (i >= 7 && i < 9) {
                    if (p1) {
                        colorSize1();
                        if (board.placeTheShip(i, 4, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    } else {
                        colorSize2();
                        if (board2.placeTheShip(i, 4, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    }
                }
                if (i == 9) {
                    if (p1) {
                        colorSize1();
                        if (board.placeTheShip(i, 6, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    } else {
                        colorSize2();
                        if (board2.placeTheShip(i, 6, rowIndex, colIndex, dir)) {
                            i++;
                        }
                    }
                }
            }
            colourTable();
        }
    }

    public void crateRectangles() {
        for (int i = 0; i < 100; i++) {
            double h = (player1.getHeight() - (10 * player1.getHgap())) / 10;
            double v = (player1.getWidth() - (10 * player1.getVgap())) / 10;
            rectangles1.add(new Rectangle(h, v, GRAY));
            rectangles2.add(new Rectangle(h, v, GRAY));
        }
        it = 0;
    }


    public void startGame(ActionEvent e) throws IOException {
        if (board2.arePlaced() && board.arePlaced() ) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pvp-arena.fxml"));
            Parent root = fxmlLoader.load();
            ShipBattleControllerPVP shipBattleController = fxmlLoader.getController();
            shipBattleController.receiveData(board, board2);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setResizable(false);
            Scene scene = new Scene(root, 1240, 1000);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void clearTable() {
        if (p1) {
            player1.getChildren().removeAll(rectangles1);
        } else {
            player2.getChildren().removeAll(rectangles2);
        }
        it = 0;
    }

    public void colourTable() {
        clearTable();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (p1) {
                    if (board.board[i][j] == 1) {
                        player1.add(rectangles1.get(it), j, i);
                        it++;
                    }
                } else {

                    if (board2.board[i][j] == 1) {
                        player2.add(rectangles2.get(it), j, i);
                        it++;
                    }
                }
            }
        }

    }

    public void Return(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void resetShipPlacment(ActionEvent event) {
        if(p1) {
            player1.setDisable(false);
            board.resetShips();
            clearTable();
            i = 0;
            colourTable();
            resetSize1();
        } else {
            player2.setDisable(false);
            board2.resetShips();
            clearTable();
            i = 0;
            colourTable();
            resetSize2();
        }
    }

    public void checkBoard(ActionEvent event) {
        if (checkbox1.isSelected()) {
            if (board.arePlaced()) {
                player1.setDisable(true);
                i=0;
                p1 = false;
            } else {
                checkbox1.setSelected(false);
            }
            if(checkbox2.isSelected()){
                startButton.setDisable(false);
                resetSize2();
            }
        } else {
            startButton.setDisable(true);
            if (board.arePlaced()) {
                if (!checkbox2.isSelected()) {
                    p1 = true;
                }
            }
        }

    }

    public void checkBoard2(ActionEvent event) {
        if (checkbox2.isSelected()) {

            if (board2.arePlaced()) {
                player2.setDisable(true);
            } else {
                checkbox2.setSelected(false);
            }
            if(checkbox1.isSelected()){
                startButton.setDisable(false);
            }
        }
        else startButton.setDisable(true);
    }
}