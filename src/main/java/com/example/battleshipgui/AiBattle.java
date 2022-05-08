package com.example.battleshipgui;

import com.example.battleshipgui.exeptions.IncorrectFileException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

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

public class AiBattle implements Initializable {
    @FXML
    public Label endingMessage;
    public DialogPane endingPane;
    public GridPane Ai1;
    public GridPane Ai2;
    List<Rectangle> rectangles=new ArrayList<>();
    List<Rectangle> eRectangles=new ArrayList<>();
    int it=0;
    int eit=0;
    int sqSize=50;
    AiBoard aiboard=new AiBoard();
    AiBoard aiBoard2=new AiBoard();

    public void crateRectangles(){
        for(int i=0;i<100;i++){
            rectangles.add(new Rectangle(sqSize,sqSize,GRAY));
            eRectangles.add(new Rectangle(sqSize,sqSize,GRAY));
        }

    }
    public void clearTable(){
        Ai1.getChildren().removeAll(eRectangles);
        Ai2.getChildren().removeAll(rectangles);
        it=0;
        eit=0;
    }
    public void colourTable(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(aiboard.board[i][j]==1){
                    rectangles.get(it).setFill(GRAY);
                    Ai2.add(rectangles.get(it),j,i);
                    it++;
                }
                else if(aiboard.board[i][j]==9){
                    rectangles.get(it).setFill(RED);
                    Ai2.add(rectangles.get(it),j,i);
                    it++;
                }
                else if(aiboard.board[i][j]==8){
                    rectangles.get(it).setFill(BLUE);
                    Ai2.add(rectangles.get(it),j,i);
                    it++;
                }
            }
        }
    }
    public void colourEnemyTable(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(aiBoard2.board[i][j]==1){
                    eRectangles.get(eit).setFill(GRAY);
                    Ai1.add(eRectangles.get(eit),j,i);
                    eit++;
                }
                else if(aiBoard2.board[i][j]==9){
                    eRectangles.get(eit).setFill(RED);
                    Ai1.add(eRectangles.get(eit),j,i);
                    eit++;
                }
                else if(aiBoard2.board[i][j]==8){
                    eRectangles.get(eit).setFill(BLUE);
                    Ai1.add(eRectangles.get(eit),j,i);
                    eit++;
                }
            }
        }
    }
    @FXML
    public void shoot(ActionEvent event)  {
        aiboard.shotAt();
       if(aiboard.isEnded())win();
        aiBoard2.shotAt();
        if(aiBoard2.isEnded())win2();

        clearTable();
        colourTable();
        colourEnemyTable();
    }
    public void win(){
        Ai1.setDisable(true);
        Ai2.setDisable(true);
        endingPane.setVisible(true);
        endingMessage.setText("AI I WON!");
    }
    public void win2(){
        Ai1.setDisable(true);
        Ai2.setDisable(true);
        endingPane.setVisible(true);
        endingMessage.setText("AI II WON!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aiBoard2=new AiBoard();
        aiboard=new AiBoard();
        try {
            aiboard.addAiShips();
            aiBoard2.addAiShips();
        } catch (FileNotFoundException | IncorrectFileException e) {
            e.printStackTrace();
        }
        crateRectangles();
        Ai2.setDisable(true);
        Ai1.setDisable(true);
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
}

