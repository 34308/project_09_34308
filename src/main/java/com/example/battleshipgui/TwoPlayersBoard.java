package com.example.battleshipgui;

import java.util.Scanner;

public class TwoPlayersBoard extends Board {
    public void shotAt(int x,int y){
        if(board[x][y]==1){
            hitShip(x,y);
            board[x][y]=9;
        }
        else if(board[x][y]==0){
            board[x][y]=8;
        }
    }
    TwoPlayersBoard(TwoPlayersBoard T){
        this.board=T.board;
        this.ships=T.ships;

    }
    public TwoPlayersBoard(){
        constructShips();
    }
   /** private int getYCoordinate() {
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
    }

    private int getXCoordinate() {
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
    public void showObstructedPlayerBoard() {
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
    }
}
