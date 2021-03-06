package com.example.battleshipgui;

import com.example.battleshipgui.exeptions.IncorrectFileException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class ShipBattleController implements Initializable {
    @FXML
    public Label endingMessage;
    @FXML
    public DialogPane endingPane;
    public Label downLabel;
    List<Rectangle> rectangles=new ArrayList<>();
    List<Rectangle> eRectangles=new ArrayList<>();
    int it=0;
    int eit=0;
    int sqSize=50;
    Board board=new Board();
    AiBoard aiBoard=new AiBoard();
    @FXML
    GridPane PlayerBoard;
    @FXML
    GridPane AIBoard;
    ArrayList<int[]> clicked=new ArrayList<>();



    public void crateRectangles(){
        for(int i=0;i<100;i++){
            rectangles.add(new Rectangle(sqSize,sqSize,GRAY));
            eRectangles.add(new Rectangle(sqSize,sqSize,GRAY));
        }
    }
    public void clearTable(){
        AIBoard.getChildren().removeAll(eRectangles);
        PlayerBoard.getChildren().removeAll(rectangles);
        it=0;
        eit=0;
    }
    public void colourTable(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(board.board[i][j]==1){
                    rectangles.get(it).setFill(GRAY);
                    PlayerBoard.add(rectangles.get(it),j,i);
                    it++;
                }
                else if(board.board[i][j]==9){
                    rectangles.get(it).setFill(RED);
                    PlayerBoard.add(rectangles.get(it),j,i);
                    it++;
                }
                else if(board.board[i][j]==8){
                    rectangles.get(it).setFill(BLUE);
                    PlayerBoard.add(rectangles.get(it),j,i);
                    it++;
                }
            }
        }
    }
    public void colourEnemyTable(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(aiBoard.board[i][j]==9){
                    eRectangles.get(eit).setFill(RED);
                    AIBoard.add(eRectangles.get(eit),j,i);
                    eit++;
                }
                else if(aiBoard.board[i][j]==8){
                    eRectangles.get(eit).setFill(BLUE);
                    AIBoard.add(eRectangles.get(eit),j,i);
                    eit++;
                }
            }
        }
    }
    @FXML
    public void shoot(MouseEvent e) {
        if(e.getButton()==MouseButton.PRIMARY){
            Node target = (Node) e.getTarget();
            // traverse towards root until userSelectionGrid is the parent node
            if (target !=AIBoard) {
                Node parent;
                while ((parent = target.getParent()) != AIBoard) {
                    target = parent;
                }
            }
            Integer colIndex = GridPane.getColumnIndex(target);
            Integer rowIndex = GridPane.getRowIndex(target);
            if (colIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {
                if (checkClicked(rowIndex, colIndex)) {
                    aiBoard.shotAt(rowIndex, colIndex);
                    if(aiBoard.shipDown(rowIndex, colIndex)){
                        downLabel.setText("Zatopiony");
                        downLabel.setAlignment(Pos.TOP_CENTER);
                    }
                    else{downLabel.setText("");}
                    if(aiBoard.isEnded()) win();
                    board.shotAt();
                    if(board.isEnded()) lost();

                    clicked.add(new int[]{rowIndex, colIndex});
                }
            }
            }
        clearTable();
        colourTable();
        colourEnemyTable();
    }
    public boolean checkClicked(Integer r,Integer c){
        for (int[] i:clicked) {
            if(i[0]== r && i[1]== c){
                return false;
            }
        }
        return true;
    }
    public void win(){
        AIBoard.setDisable(true);
        PlayerBoard.setDisable(true);
        endingPane.setVisible(true);
        endingMessage.setText("YOU HAVE WON!");

    }
    public void lost(){
        AIBoard.setDisable(true);
        PlayerBoard.setDisable(true);
        endingPane.setVisible(true);
        endingMessage.setText("YOU HAVE LOST!!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aiBoard=new AiBoard();
        try {
            aiBoard.addAiShips();
        } catch (FileNotFoundException | IncorrectFileException e) {
            e.printStackTrace();
        }
        crateRectangles();
    }

    public void goToMenu(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load() ,300, 300);
        Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void Return(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setBoard(Board board) {

        this.board = board;
        colourEnemyTable();
        colourTable();
    }
}
