package com.company;

import com.company.exeptions.IncorrectFileException;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Controller {
    Board board=new Board();
    AiBoard aiBoard=new AiBoard();
    TwoPlayersBoard firstPlayerBoard=new TwoPlayersBoard();
    TwoPlayersBoard secondPlayersBoard=new TwoPlayersBoard();
    Timer timer=new Timer();


    public void PlayerVsAi() throws FileNotFoundException, IncorrectFileException {
        boolean end=false;
        //tworzenie planszy gracza
        Board board=new Board();
        //tworzenie planszy ai
        AiBoard aiBoard=new AiBoard();
        //wczytywanie pozycji statków z pliku
        aiBoard.addAiShips();
        aiBoard.showPlayerBoard();
        //wyświetl plansze gracza
        board.showPlayerBoard();
        //wstaw statki gracza
        board.placeShips();
        //pętla obsługujaca turowy charakter rozgrywki
        //odbywa sie do momentu gdy wszystkie statik z jedej z plansz zostaną zatopione
        do{
            //wyswietl plansze grazca
            System.out.println("Plansza Gracza:");
            board.showPlayerBoard();
            //wyswietl plansze ai(zaslonieta)
            System.out.println("Plansza AI:");
            aiBoard.showPlayerBoard();
            //strzał AI
            board.shotAt();
            //stezał gracza
            aiBoard.shotAt();
            //jezeli na ktorejs z planszy wszystkie statki sa zatopione zmien warunek zakoncz gre
            if(board.isEnded()||aiBoard.isEnded()){
                end=true;
            }
        }while(!end);
        System.out.println("Plansza Gracza:");
        board.showPlayerBoard();
        System.out.println("Plansza AI:");
        aiBoard.showPlayerBoard();
        return;
    }


}
