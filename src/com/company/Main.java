package com.company;

import com.company.exeptions.IncorrectFileException;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws IncorrectFileException, FileNotFoundException {
        Board board=new Board();
        board.showPlayerBoard();
        board.placeShips();
        board.showPlayerBoard();
        AiBoard aiBoard=new AiBoard();
        aiBoard.showPlayerBoard();
        aiBoard.addAiShips();

    }
}
