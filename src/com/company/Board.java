package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    AiMemory aiMemory =new AiMemory();
    //wielkosc planszy
    int boardSize = 10;
    static int numberOfShips = 10;
    //stworzenie statkow
    Ship[] ships = new Ship[numberOfShips];
    //utworzenie planszy do gry
    int[][] board=new int[boardSize][boardSize];
    Scanner scanner=new Scanner(System.in);
    //litery uzywane do drukowania tablicy i odczytywania wejścia gracza
    static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    private void constructShips(){
        for(int i=0;i<10;i++){
            ships[i]= new Ship();
        }
    }
    public Board(){
        constructShips();
    }
    //wyswietlanie planszy
    //0-puste pole
    //8-pudło
    //9-trafiony
    //1-statek
    public void showPlayerBoard() {
        //literowanie po kazdym elemencie tablicy
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
                //potrzebujemy tego warunku gdyz drukujemy dodatkowe 2 linijki
                if (i > 0 && j > 0) {
                    if (board[i - 1][j - 1] == 1 ) {
                        System.out.print("0 ");
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
    //znajduje statek na wkazanej pozycji i odejmuje od niego hp
    public boolean hitShip(int x,int y){
        int[][] position ;
        int size;
        //przeszukiwanie wszystkich statków statku o tej samej pozycji co x i y
        for(int i = 0; i< 10; i++){
            position= ships[i].getPosition();
            size=ships[i].getSize();
            for(int l = 0; l< size; l++){
                    if(position[l][0]==x && position[l][1]==y &&ships[i].isAlive()){
                        ships[i].hitted();
                        return true;
                    }
            }
        }
        return false;
    }
    public boolean isDown(int x,int y){
        int[][] position ;
        int size;
        //przeszukiwanie wszystkich statków statku o tej samej pozycji co x i y
        for(int i = 0; i< 10; i++){
            position= ships[i].getPosition();
            size=ships[i].getSize();
            for(int l = 0; l< size; l++){
                if(position[l][0]==x && position[l][1]==y){
                    return !ships[i].isAlive();
                }
            }
        }
        return false;
    }
    //randomowy strzał wykonywany przez komputer w tablice gracza
   public void shotAt(){
       aiMemory.copyBoard(board.clone());
        int[] xy=new int[2];
        int miss=0;
        if(aiMemory.getState()==0){
            xy[0]=aiMemory.getX();
            xy[1]=aiMemory.getY();
            System.out.println("1-stacja "+board[xy[0]][xy[1]]);
            if(board[xy[0]][xy[1]]==1){
                System.out.println("trafiony 1-stacja");
                hitShip(xy[0],xy[1]);
                board[xy[0]][xy[1]]=9;
                aiMemory.setState(1);
                aiMemory.setCxCy(xy);
            }
            else if(board[xy[0]][xy[1]]==0){
                System.out.println("Pudlo 1-stacja");
                aiMemory.setFindNewShip();
                board[xy[0]][xy[1]]=8;
            }else if(board[xy[0]][xy[1]]==9||board[xy[0]][xy[1]]==8){
                aiMemory.setFindNewShip();
                shotAt();
            }
        }
        else if(aiMemory.getState()==1){
            System.out.println("2-stacja");
            xy=aiMemory.nextShotCord();
            System.out.println("2-stacja "+board[xy[0]][xy[1]]);
            if(board[xy[0]][xy[1]]==1){
                System.out.println("trafiony 2-stacja");
                miss=0;
                hitShip(xy[0],xy[1]);
                board[xy[0]][xy[1]]=9;
                if(isDown(xy[0],xy[1])){
                    aiMemory.setFindNewShip();
                    aiMemory.setState(0);
                }
                else {
                    aiMemory.setX(xy[0]);
                    aiMemory.setY(xy[1]);
                }
            }
            else if(board[xy[0]][xy[1]]==0){
                System.out.println("pudlo 2-stacja"+board[xy[0]][xy[1]]);
                board[xy[0]][xy[1]]=8;
                miss++;
                aiMemory.getBackToCenter();
                aiMemory.nextCurrent();
                if(miss>=4){
                    aiMemory.setFindNewShip();
                    aiMemory.setState(0);
                }
            }
            else if(board[xy[0]][xy[1]]==9||board[xy[0]][xy[1]]==8){
                aiMemory.setFindNewShip();
                shotAt();
        }


        }
       aiMemory.copyBoard(board.clone());
    }

    //umieszczenie statków graczy na planszy
    public void placeShips(){
        int i=0;
        //wybieranie poszczególnych statków zaczynając od 2-poziomowych po ich ilości 2-4, 3-3 4-2 6-1
        while (i < 4) {
            placeTheShip(i, 2);
            i++;
        }
        while (i >= 4 && i < 7) {
            placeTheShip(i, 3);
            i++;
        }
        while (i >= 7 && i < 9) {
            placeTheShip(i, 4);
            i++;
        }
        while (i == 9) {
            placeTheShip(i, 6);
            i++;
        }
    }
    private void placeTheShip(int currentShipNumber, int currentShipSize){
        int x = getXCoordinate(currentShipSize);
        int y = getYCoordinate(currentShipSize);
        int dir = getDirection();
        //przekształcenie litery na liczbe1
        //sprawdzanie czy miejsce do ktorego chcemy wpisac statek jest wolne
        if (checkPlace(x, y, dir, currentShipSize)) {
            //JEZELI TAK WSTAW STATEK
            ships[currentShipNumber].changeShip(currentShipSize, generatePosition(currentShipSize, x, y, dir));
            showPlayerBoard();

        } else//JEZELI NIE WYSWIETL KOMUNIKAT O BLEDZIE
        {
            System.out.println("Błedne koordynaty");
            placeTheShip(currentShipNumber, currentShipSize);
        }
    }

    private int getDirection() {
        int direction;
        while (true) {
            System.out.println("podaj pozycje orientacje statku [1]-DO GORY [2]-PRAWO [3]-DOL [4]-LEWO ");
            try {
                direction = scanner.nextInt();
                if(direction<5 && direction >0) {
                    return direction;
                }
                System.out.println("podaj cyfre od 1 do 4!");
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("podaj cyfre od 1 do 4!");
                scanner.nextLine();
            }
        }
    }

    protected int letterToNumber(char y) {
        for(int l=0;l<10;l++){
            if(letters[l] == y){
                return l+1;
            }
        }
        return 0;
    }

    private int getYCoordinate(int sizeOfShip) {
        char y;
        while (true) {
            System.out.println("podaj pozycje statku o wielkości " + sizeOfShip + "\npodaj y: ");
            y = scanner.next().charAt(0);
            for (char letter : letters) {
                if (letter == y) {
                    return letterToNumber(y) - 1;
                }
            }
            System.out.println("podaj wielką literę od A do J!\n");
        }
    }

    private int getXCoordinate(int sizeOfShip) {
        int x ;
        while (true) {
            System.out.println("podaj pozycje statku o wielkości "+sizeOfShip+"\npodaj x: ");
            try {
                x = scanner.nextInt();
                if(x<11 && x >0) {
                    return x-1;
                }
                System.out.println("podaj cyfre od 1 do 10!");
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("podaj cyfre od 1 do 10!");
                scanner.nextLine();
            }
        }
    }

    //SPRAWDZA CZY STATEK O PODANYM ROZMIARZE, POZYCJI I KIERUNKU MOZE ZOSTAC WPISANY
    private boolean checkPlace(int x, int y,int dir,int size) {
        //SPRAWDZA CZY KOORDYNATY MIESZCZA SIE W PLANSZY

        if(!(x< boardSize)&&!(x>=0)&&!(y< boardSize)&& !(y>=0)){
            return false;
        }
        //SPRAWDZA CZY STATEK O DANEJ WIELKOSCI NIE WYCHODZI POZA PLANSZE
        if(size>0){
            if(dir==1){
                if((x+1)-size<0){
                    return false;
                }
            }
            else if(dir==2){
                if(y+size> boardSize){
                    return false;
                }
            }
            else if(dir==3){
                if(x+size> boardSize){
                    return false;
                }
            }
            else if(dir==4){
                if((y+1)-size<0){
                    return false;
                }
            }
        }

        //KIERUNKI DO SPRAWDZENIA
        int[][] checkers;
        checkers = new int[][]{{1 + x, y}, {x, y + 1}, {1+x, y + 1}, {x - 1, y}, {x, y - 1}, {x - 1, y - 1}, {x - 1, y + 1}, {1+x , y - 1}};
        for (int i = 0; i < 8; i++) {
            //PRZESZUKIWANIE TABLICY KIERUNKOW
            if (checkers[i][0] >= 0 && checkers[i][0] < boardSize && checkers[i][1] >= 0 && checkers[i][1] < boardSize){

                //W PRZYPADKU ZNALEZIENIA STAKTU W OBRĘBIE ZWROCIC FALSE
                if (board[checkers[i][0]][checkers[i][1]]==1){
                    return false;

                }
            }
        }
        //JEZELI NIE ZNALEZIONO ZADNYCH PROBLEMOW ZWROC TRUE
        if(size>0){
            if(dir==1){
                return checkPlace(x-1,y,dir,size-1);
            }
            if(dir==2){
                return checkPlace(x,y+1,dir,size-1);
            }
            if(dir==3){
                return checkPlace(x+1,y,dir,size-1);
            }
            if(dir==4){
                return checkPlace(x,y-1,dir,size-1);
            }
        }
        return true;
    }
    //ZNAJDUJE WSZYTSKIE POZYCJE DLA DANEGO STATKU I JE ZWRACA
    public int[][] generatePosition(int size,int x,int y,int dir) {
        int it=0;
        int[][] position=new int[size][2];
        if (dir == 1) {
            for (int i = x; i >x - size; i--) {
                    position[it][0]=i;
                    position[it][1]=y;
                    it++;
            }
        }
        if (dir == 2) {
            for (int i = y; i < y + size; i++) {
                position[it][0]=x;
                position[it][1]=i;
                it++;
            }
        }
        if (dir == 3) {
            for (int i = x; i < x + size; i++) {
                position[it][0]=i;
                position[it][1]=y;
                it++;
            }
        }
        if (dir == 4) {
            for (int i = y; i > y - size; i--) {
                position[it][0]=x;
                position[it][1]=i;
                it++;
            }
        }
        for (int i = 0; i < size; i++) {
            //PRZEPISUJE POZYCJE STATKU NA TABLICE
            board[position[i][0]][position[i][1]]=1;
        }
        return position;
    }
    //SPRAWDZA CZY LICZBA ZNISZCZONYCH STATKOW JEST ROWNA LICZBIE WSZYSTKICH STATKOW JEZELI TAK ZWRACA TRUE
    public boolean isEnded(){
        int it=0;
        for(int i = 0; i< 10; i++){
            if(!ships[i].isAlive()){
                it++;
            }
        }
        if(it==10){
            System.out.println("AI wygrało!!");
            return true;

        }else{
            return false;
        }

    }
}
