package com.example.battleshipgui;
import com.example.battleshipgui.exeptions.IncorrectFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class AiBoard extends Board{
    //TAKI SAM JAK SHOW PLAYER BOARD W BOARD ALE NIE WYSWIETLA POZYCJI STATKOW
    /**public void showPlayerBoard() {
        for (int i = 0; i < boardSize + 1; i++) {
            for (int j = 0; j < boardSize + 1; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("  ");
                    }
                    if (j > 0) {
                        System.out.print(" " + letters[j - 1]);
                    }
                } else if (j == 0) {
                    if(i==10){
                        System.out.print(" " + i);
                    }else{
                        System.out.print("  " + i);
                    }
                }
                if (i > 0 && j > 0) {
                    if (board[i - 1][j - 1] == 1 ) {
                        System.out.print("  ");
                    } else if (board[i - 1][j - 1] == 9) {
                        System.out.print("X ");
                    }else if(board[i - 1][j - 1] == 8) {
                        System.out.print("@ ");
                    } else {
                        System.out.print("  ");
                    }
                }
                if (j == boardSize) {
                    System.out.println(" ");
                }
            }
        }
    }**/
    //ODCZYTYWANIE POZYCJI Z PLIKU I PRZENOSZENIE ICH NA TABLICE
    public void addAiShips() throws FileNotFoundException, IncorrectFileException {
        Random rand = new Random();
        int fileNumber = rand.nextInt(1,5);


        File file = new File("positions/scheme" +fileNumber+".txt");
        FileValidator fileValidator=new FileValidator();
        if(!fileValidator.isValid(file)){
            throw new IncorrectFileException("/File"+"scheme"+fileNumber+".txt"+" is incorrect");
        }
        Scanner sc=new Scanner(file);
        String line;
        ArrayList<Ship> localShips = new ArrayList<>();
        while (sc.hasNext()){
            line = sc.nextLine();
            String[] splitedLine = line.split(" ");
            int sizeOfShip  = splitedLine.length;
            int[][] positions = new int[sizeOfShip][2];
            for (int i = 0; i < sizeOfShip; i++) {
                char[] charCoordinates = splitedLine[i].toCharArray();
                int x = charCoordinates[0] - '0';
                int y = charCoordinates[1] - '0';
                positions[i][0] = x;
                positions[i][1] = y;
                board[x][y]=1;
            }
            Ship ship = new Ship(sizeOfShip,sizeOfShip,positions);
            localShips.add(ship);
        }
        ships = localShips.toArray(new Ship[0]);

    }

    public void shotAt(int x,int y){
        if(board[x][y]==1){
            hitShip(x,y);
            board[x][y]=9;
        }
        else if(board[x][y]==0){
            board[x][y]=8;
        }
    }
    //ROZNIE SIE JEDYNIE NAPISEM KTO WYGRAL
    public boolean isEnded(){
        int it=0;
        for(int i = 0; i< 10; i++){
            if(!ships[i].isAlive()){
                it++;
            }
        }
        // System.out.println("Gracz wygrał!!");
        return it == 10;

    }
  /**  private int getYCoordinate() {
        char y = 'a';
        int n=0;
        while (true) {
            System.out.println("podaj pozycje strzału na osi alfabetycznej: ");
            y = scanner.next().charAt(0);
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == y) {
                    return letterToNumber(y)-1;
                }
            }
            System.out.println("podaj wielką literę od A do J!\n");
        }
    }*/

    /**private int getXCoordinate() {
        int x = 0;
        while (true) {
            System.out.println("podaj pozycje strzału na osi liczbowej");
            try {
                x = scanner.nextInt();
                if(x<11 && x >0) {
                    return x-1;
                }
                System.out.println("podaj cyfre od 1 do 10!");
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("podaj cyfre od 1 do 10!");
                scanner.nextLine();
            }
        }
    }*/
}
