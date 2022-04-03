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
import net.synedra.validatorfx.Check;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import static javafx.scene.paint.Color.*;

public class ShipBattleController implements Initializable {
    @FXML
    public DialogPane startGameWindow;
    List<Rectangle> rectangles=new ArrayList<>();
    List<Rectangle> eRectangles=new ArrayList<>();
    int it=0;
    int eit=0;
    int sqSize=50;
    Board board=new Board();
    AiBoard aiBoard=new AiBoard();
    TwoPlayersBoard firstPlayerBoard=new TwoPlayersBoard();
    TwoPlayersBoard secondPlayersBoard=new TwoPlayersBoard();
    Timer timer=new Timer();
    int dir=1;
    int i=0;
    boolean o=true;
    @FXML
    GridPane PlayerBoard;
    @FXML
    GridPane AIBoard;


    @FXML
    private void receiveData(Event event) {
        // Step 1
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        board = (Board) stage.getUserData();
        PlayerBoard.setDisable(false);
        AIBoard.setDisable(false);
        startGameWindow.setVisible(false);
    }


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
    public void shoot(MouseEvent e) throws IncorrectFileException, FileNotFoundException {
        if(e.getButton()==MouseButton.PRIMARY){
            Node target = (Node) e.getTarget();
            // traverse towards root until userSelectionGrid is the parent node
            if (target !=AIBoard) {
                Node parent;
                while ((parent = target.getParent()) != AIBoard) {
                    target = parent;
                }
            }
            Integer colIndex = AIBoard.getColumnIndex(target);
            Integer rowIndex = AIBoard.getRowIndex(target);
            if (colIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else
            {
                aiBoard.shotAt(rowIndex,colIndex);
                board.shotAt();
            }
        }

        clearTable();
        colourTable();
        colourEnemyTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aiBoard=new AiBoard();
        try {
            aiBoard.addAiShips();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IncorrectFileException e) {
            e.printStackTrace();
        }
        crateRectangles();
        PlayerBoard.setDisable(true);
        AIBoard.setDisable(true);
    }
}
