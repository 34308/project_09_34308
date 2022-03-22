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

    public int getGameMode() {
        int gameMode=3;
        Scanner scanner=new Scanner(System.in);
        System.out.println("wybierz tryb rozgrywki:\n[1]-Gracz vs AI\n[2]-Gracz vs Gracz\n[0]-aby wyjsc");
        while(!(gameMode==0||gameMode==1||gameMode==2)) {
            gameMode = scanner.nextInt();
        }
        return gameMode;
    }
    public void PlayerVsPlayer() throws InterruptedException {
        boolean end=false;
        firstPlayerBoard.showPlayerBoard();
        secondPlayersBoard.showPlayerBoard();
        //wstaw statki gracza
        System.out.println("Umieść statki\nGracz jeden:");
        firstPlayerBoard.placeShips();
        System.out.println("Gracz dwa:");
        secondPlayersBoard.placeShips();
        //pętla obsługujaca turowy charakter rozgrywki
        //odbywa sie do momentu gdy wszystkie statik z jedej z plansz zostaną zatopione
        do{
            //wyswietl plansze grazca
            System.out.println("Gracz jeden:");
            firstPlayerBoard.showPlayerBoard();
            System.out.println("Gracz dwa:");
            secondPlayersBoard.showObstructedPlayerBoard();
            //strzał gracza 1
            System.out.println("Tura gracza jeden:");
            secondPlayersBoard.shotAt();
            scroll();
            TimeUnit.SECONDS.sleep(5);

            System.out.println("Gracz jeden:");
            firstPlayerBoard.showObstructedPlayerBoard();
            System.out.println("Gracz dwa:");
            secondPlayersBoard.showPlayerBoard();
            //strzał gracza 2
            System.out.println("Tura gracza dwa:");
            firstPlayerBoard.shotAt();
            //jezeli na ktorejs z planszy wszystkie statki sa zatopione zmien warunek zakoncz gre
            scroll();
            TimeUnit.SECONDS.sleep(5);
            if(secondPlayersBoard.isEnded()||firstPlayerBoard.isEnded()){
                end=true;
            }
        }while(!end);
        System.out.println("plansza gracza jeden");
        firstPlayerBoard.showPlayerBoard();
        System.out.println("plansza gracza dwa");
        secondPlayersBoard.showPlayerBoard();
        return;
    }
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

    private void scroll(){
        for(int i=0;i<10;i++){
            System.out.println(" ");
        }
    }
}
