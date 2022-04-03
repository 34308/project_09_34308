package com.example.battleshipgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static javafx.scene.paint.Color.*;

public class HelloController {
    List <Rectangle> rectangles=new ArrayList<>();
    int it=0;
    final Board board=new Board();
    int dir=1;
    int i=0;
    @FXML
    GridPane GuiBoard;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void changeStage(ActionEvent event ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PlaceShips.fxml"));
        Scene scene = new Scene(fxmlLoader.load() ,520, 540);
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();;
        stage.setScene(scene);
        stage.show();
    }


    public void placeShip(MouseEvent e) throws IOException {
        crateRectangles();
        if(e.getButton()== MouseButton.SECONDARY){
            dir++;
            if(dir>4){
                dir=1;
            }
            System.out.println(dir);
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
            Integer colIndex = GuiBoard.getColumnIndex(target);
            Integer rowIndex = GuiBoard.getRowIndex(target);
            if (colIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {
                System.out.println(rowIndex+" "+colIndex);
                //System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
                System.out.println("i=" +i);
                //wybieranie poszczególnych statków zaczynając od 2-poziomowych po ich ilości 2-4, 3-3 4-2 6-1
                if (i < 4) {
                    if(board.placeTheShip(i, 2,rowIndex.intValue(),colIndex.intValue(),dir)){
                        i++;
                    }
                }
                if (i >= 4 && i < 7) {
                    if(board.placeTheShip(i, 3,rowIndex.intValue(),colIndex.intValue(),dir)){
                        i++;
                    }
                }
                if (i >= 7 && i < 9) {
                    if(board.placeTheShip(i, 4,rowIndex.intValue(),colIndex.intValue(),dir)){
                        i++;
                    }
                }
                if (i == 9) {
                    if(board.placeTheShip(i, 6,rowIndex.intValue(),colIndex.intValue(),dir)){
                        i++;
                    }
                }
            }
            colourTable();
        }

    }

    public void crateRectangles(){
        for(int i=0;i<100;i++){
            rectangles.add(new Rectangle(50,50,GRAY));
        }

    }
    public void startGame(ActionEvent e) throws IOException {
        Board b1=board;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-ships.fxml"));
        Scene scene = new Scene(fxmlLoader.load() ,520, 540);
        Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setUserData(b1);
        stage.setScene(scene);
        stage.show();
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
    }
}