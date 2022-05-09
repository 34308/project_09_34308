package com.example.battleshipgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import static javafx.scene.paint.Color.*;

public class PlaceShip {
    public GridPane sizeBoard;

    Image image = new Image("cursor1.png");
    List <Rectangle> rectangles=new ArrayList<>();
    int it=0;
    final Board board=new Board();
    int dir=1;
    int i=0;
    @FXML
    GridPane GuiBoard;

    public void setCursor(){
        if(!Objects.equals(GuiBoard.getScene().getRoot().getCursor(), new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2))){
            image = new Image("cursor"+dir+".png");
            GuiBoard.getScene().getRoot().setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        sizeBoard.getChildren().get(0).setStyle("-fx-background-color: "+" #C3D120");
        sizeBoard.getChildren().get(1).setStyle("-fx-background-color: "+" #C3D120");
    }
    public void colorSize(){
        sizeBoard.getChildren().get(0).setStyle("-fx-background-color: "+" #C3D120");
        sizeBoard.getChildren().get(1).setStyle("-fx-background-color: "+" #C3D120");
        if(i>=3){
            sizeBoard.getChildren().get(2).setStyle("-fx-background-color: "+" #C3D120");
            if(i>=6){
                sizeBoard.getChildren().get(3).setStyle("-fx-background-color: "+" #C3D120");
            }
            if(i>=8){
                sizeBoard.getChildren().get(4).setStyle("-fx-background-color: "+" #C3D120");
                sizeBoard.getChildren().get(5).setStyle("-fx-background-color: "+" #C3D120");
            }
        }
    }
    public void resetSize(){
        for (Node n : sizeBoard.getChildren()) {
            n.setStyle("");
        }
        colorSize();
    }
    public void placeShip(MouseEvent e){
        crateRectangles();
        if(e.getButton()== MouseButton.SECONDARY){
            dir++;
            if(dir>4){
                dir=1;
            }
            image = new Image("cursor"+dir+".png");
            GuiBoard.getScene().getRoot().setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
        }
        if(e.getButton()==MouseButton.PRIMARY){
            Node target = (Node) e.getTarget();
            // traverse towards root until userSelectionGrid is the parent node
            if (target != GuiBoard) {
                Node parent;
                while ((parent = target.getParent()) != GuiBoard) {
                    target = parent;
                }
            }
            Integer colIndex = GridPane.getColumnIndex(target);
            Integer rowIndex = GridPane.getRowIndex(target);
            if (colIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {

                //System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());

                //wybieranie poszczególnych statków zaczynając od 2-poziomowych po ich ilości 2-4, 3-3 4-2 6-1
                if (i < 4) {
                    if(i<3){
                        colorSize();
                    }
                    else{
                        colorSize();

                    }
                    if(board.placeTheShip(i, 2, rowIndex, colIndex,dir)){
                        i++;
                    }
                }
                if (i >= 4 && i < 7) {
                    if(i<6){
                        colorSize();
                    }
                    else{
                        colorSize();

                    }
                    if(board.placeTheShip(i, 3, rowIndex, colIndex,dir)){
                        i++;
                    }
                }
                if (i >= 7 && i < 9) {
                    if(i<8){
                        colorSize();
                    }
                    else{
                        colorSize();

                    }
                    if(board.placeTheShip(i, 4, rowIndex, colIndex,dir)){
                        i++;
                    }
                }
                if (i == 9) {
                    if(board.placeTheShip(i, 6, rowIndex, colIndex,dir)){
                        i++;
                    }
                }
            }
            colourTable();
        }
    }

    public void crateRectangles(){

        for(int i=0;i<100;i++){
            double h=(GuiBoard.getHeight()-(10*GuiBoard.getHgap()))/10;
            double v=(GuiBoard.getWidth()-(10*GuiBoard.getVgap()))/10;
            rectangles.add(new Rectangle(h,v,GRAY));
        }
    }
    public void startGame(ActionEvent e) throws IOException {
        if(board.arePlaced()){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aicp-arena.fxml"));
            Parent root=fxmlLoader.load();
            ShipBattleController shipBattleController =fxmlLoader.getController();
            shipBattleController.setBoard(board);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setResizable(false);
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void clearTable(){
       GuiBoard.getChildren().removeAll(rectangles);
        it=0;
    }
    public void colourTable(){
        clearTable();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(board.board[i][j]==1){
                    GuiBoard.add(rectangles.get(it),j,i);
                    it++;
                }
            }
        }

    }
    public void resetShips(ActionEvent event) {
        board.resetShips();
        clearTable();
        i=0;
        colourTable();
        resetSize();
    }


    public void Return(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}