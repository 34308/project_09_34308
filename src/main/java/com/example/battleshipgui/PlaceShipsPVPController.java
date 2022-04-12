package com.example.battleshipgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.GRAY;

public class PlaceShipsPVPController {
    public Button nextPlayerButton;
    public Button startGameButton;
    Image image = new Image("cursor1.png");
    boolean p1=true;
    List <Rectangle> rectangles=new ArrayList<>();
    int it=0;
     TwoPlayersBoard board=new TwoPlayersBoard();
     TwoPlayersBoard board2=new TwoPlayersBoard();
    int dir=1;
    int i=0;
    @FXML
    GridPane GuiBoard;


    public void placeShip(MouseEvent e) throws IOException {

        crateRectangles();
        if(e.getButton()== MouseButton.SECONDARY){
            dir++;
            if(dir>4){
                dir=1;
            }
            image = new Image("cursor"+dir+".png");
            GuiBoard.getScene().getRoot().setCursor(new ImageCursor(image,image.getWidth()/2,image.getHeight()/2));
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
                    if(p1==true){
                        if(board.placeTheShip(i, 2,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
                    }else{
                        if(board2.placeTheShip(i, 2,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
                    }

                }
                if (i >= 4 && i < 7) {
                    if(p1==true){
                        if(board.placeTheShip(i, 3,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
                    }else{
                        if(board2.placeTheShip(i, 3,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
                    }
                }
                if (i >= 7 && i < 9) {
                    if(p1==true){
                        if(board.placeTheShip(i, 4,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
                    }else{
                        if(board2.placeTheShip(i, 4,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
                    }
                }
                if (i == 9) {
                    if(p1==true){
                        if(board.placeTheShip(i, 6,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
                    }else{
                        if(board2.placeTheShip(i, 6,rowIndex.intValue(),colIndex.intValue(),dir)){
                            i++;
                        }
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
    public void nextPlayer(ActionEvent e) throws IOException, CloneNotSupportedException {
        if(board.arePlaced()){
            p1=false;
            startGameButton.setDisable(false);

            clearTable();
            i=0;
            colourTable();
            nextPlayerButton.setDisable(true);
        }

    }
    public void startGame(ActionEvent e) throws IOException {
        if(board2.arePlaced()){
            TwoPlayersBoard p1=board;
            TwoPlayersBoard p2=board2;

            List<TwoPlayersBoard> boards=new ArrayList<TwoPlayersBoard>();
            boards.add(p1);
            boards.add(p2);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pvp-arena.fxml"));
            Scene scene = new Scene(fxmlLoader.load() ,1240, 1000);
            Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setUserData(boards);
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
                if(p1){
                    if(board.board[i][j]==1){
                        GuiBoard.add(rectangles.get(it),j,i);
                        it++;
                    }
                }else{
                    if(board2.board[i][j]==1){
                        GuiBoard.add(rectangles.get(it),j,i);
                        it++;
                    }
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