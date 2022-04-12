package com.example.battleshipgui;

import com.example.battleshipgui.exeptions.IncorrectFileException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class ShipBattleControllerPVP implements Initializable {
    @FXML
    public DialogPane startGameWindow;
    public Label endingMessage;
    public DialogPane endingPane;
    public GridPane player1;
    public GridPane player2;
    public GridPane ViewPlayer2;
    public GridPane ViewPlayer1;
    List<Rectangle> rectangles=new ArrayList<>();
    List<Rectangle> eRectangles=new ArrayList<>();
    List<Rectangle> rectangles2=new ArrayList<>();
    List<Rectangle> eRectangles2=new ArrayList<>();
    int it=0;
    boolean k=true;
    int eit=0;
    TwoPlayersBoard firstPlayerBoard=new TwoPlayersBoard();
    TwoPlayersBoard secondPlayersBoard=new TwoPlayersBoard();
    int dir=1;
    ArrayList<int[]> clicked=new ArrayList<int[]>();
    ArrayList<int[]> clicked2=new ArrayList<int[]>();

    @FXML
    private void receiveData(Event event) {
        // Step 1
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        List <TwoPlayersBoard> boards= (List<TwoPlayersBoard>) stage.getUserData();
        firstPlayerBoard = boards.get(0);
        secondPlayersBoard = boards.get(1);

        firstPlayerBoard.showPlayerBoard();
        secondPlayersBoard.showPlayerBoard();

        player2.setDisable(false);
        player1.setDisable(false);
        startGameWindow.setVisible(false);
    }


    public void crateRectangles(){
        double h=(player1.getHeight()-(10*player1.getHgap()))/10;
        double v=(player1.getWidth()-(10*player1.getVgap()))/10;
        double h2=(ViewPlayer1.getHeight()-(10*ViewPlayer1.getHgap()))/10;
        double v2=(ViewPlayer1.getWidth()-(10*ViewPlayer1.getVgap()))/10;
        for(int i=0;i<100;i++){
            rectangles.add(new Rectangle(h,v,GRAY));
            eRectangles.add(new Rectangle(h,v,GRAY));
            rectangles2.add(new Rectangle(h2,v2,GRAY));
            eRectangles2.add(new Rectangle(h2,v2,GRAY));
        }
    }

    public void clearTable(){
        player1.getChildren().removeAll(eRectangles);
        player2.getChildren().removeAll(rectangles);
        ViewPlayer1.getChildren().removeAll(eRectangles2);
        ViewPlayer2.getChildren().removeAll(rectangles2);
        it=0;
        eit=0;
    }


    public void colourP2(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(firstPlayerBoard.board[i][j]==1){
                    rectangles2.get(it).setFill(GRAY);
                    ViewPlayer2.add(rectangles2.get(it),j,i);
                    it++;
                }
                else if(firstPlayerBoard.board[i][j]==9){
                    rectangles2.get(it).setFill(RED);
                    ViewPlayer2.add(rectangles2.get(it),j,i);

                    rectangles.get(it).setFill(RED);
                    player2.add(rectangles.get(it),j,i);
                    it++;
                }
                else if(firstPlayerBoard.board[i][j]==8){
                    rectangles2.get(it).setFill(BLUE);
                    ViewPlayer2.add(rectangles2.get(it),j,i);

                    rectangles.get(it).setFill(BLUE);
                    player2.add(rectangles.get(it),j,i);
                    it++;
                }
            }
        }
    }

    public void colourP1(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(secondPlayersBoard.board[i][j]==1){
                    eRectangles2.get(eit).setFill(GRAY);
                    ViewPlayer1.add(eRectangles2.get(eit),j,i);
                    eit++;
                }
                else if(secondPlayersBoard.board[i][j]==9){
                    eRectangles2.get(eit).setFill(RED);
                    ViewPlayer1.add(eRectangles2.get(eit),j,i);

                    eRectangles.get(eit).setFill(RED);
                    player1.add(eRectangles.get(eit),j,i);
                    eit++;
                }
                else if(secondPlayersBoard.board[i][j]==8){
                    eRectangles2.get(eit).setFill(BLUE);
                    ViewPlayer1.add(eRectangles2.get(eit),j,i);

                    eRectangles.get(eit).setFill(BLUE);
                    player1.add(eRectangles.get(eit),j,i);
                    eit++;
                }
            }
        }
    }

    public void firstShoot(MouseEvent e) {
        crateRectangles();
        if(e.getButton()== MouseButton.PRIMARY){
            Node target = (Node) e.getTarget();
            // traverse towards root until userSelectionGrid is the parent node
            if (target !=player1) {
                Node parent;
                while ((parent = target.getParent()) != player1) {
                    target = parent;
                }
            }
            Integer colIndex = player1.getColumnIndex(target);
            Integer rowIndex = player1.getRowIndex(target);
            if (colIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {
                if (checkClicked(clicked,rowIndex, colIndex)) {
                    secondPlayersBoard.shotAt(rowIndex.intValue(), colIndex.intValue());
                    if(secondPlayersBoard.isEnded()) firstWin();
                    clicked.add(new int[]{rowIndex.intValue(), colIndex.intValue()});
                    player2.setDisable(false);
                    player1.setDisable(true);
                }
            }
        }
        clearTable();
        colourP2();
        colourP1();
    }
    @FXML
    public void secondShoot(MouseEvent e) throws IncorrectFileException, FileNotFoundException {
        if(e.getButton()== MouseButton.PRIMARY){
            Node target = (Node) e.getTarget();
            // traverse towards root until userSelectionGrid is the parent node
            if (target !=player1) {
                Node parent;
                while ((parent = target.getParent()) !=player2){
                    target = parent;
                }
            }
            Integer colIndex = player2.getColumnIndex(target);
            Integer rowIndex = player2.getRowIndex(target);
            if (colIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {
                if (checkClicked(clicked2,rowIndex, colIndex)) {
                    firstPlayerBoard.shotAt(rowIndex, colIndex);
                    if(firstPlayerBoard.isEnded()) secWin();

                    clicked2.add(new int[]{rowIndex.intValue(), colIndex.intValue()});
                    player2.setDisable(true);
                    player1.setDisable(false);
                }
            }
        }
        clearTable();
        colourP2();
        colourP1();

    }
    public boolean checkClicked( ArrayList<int[]> k,Integer r,Integer c){
        for (int[] i:k) {
            if(i[0]==r.intValue() && i[1]==c.intValue()){
                return false;
            }
        }
        return true;
    }
    public void firstWin(){
        player1.setDisable(true);
        player2.setDisable(true);
        endingPane.setVisible(true);
        endingMessage.setText("FIRST PLAYER WON!");

    }
    public void secWin(){
        player1.setDisable(true);
        player2.setDisable(true);
        endingPane.setVisible(true);
        endingMessage.setText("SECOND PLAYER WON!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player2.setDisable(true);
        player1.setDisable(true);
    }

    public void goToMenu(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load() ,300, 300);
        Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}


