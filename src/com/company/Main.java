package com.company;

public class Main {

    public static void main(String[] args) {
        Board board=new Board();
        board.showPlayerBoard();
        board.placeShips();
        board.showPlayerBoard();
        AiBoard aiBoard=new AiBoard();
        aiBoard.showPlayerBoard();
        int i=0;
        while(i<10){
            board.shotAt();
            board.showPlayerBoard();
            i++;
        }
    }
}