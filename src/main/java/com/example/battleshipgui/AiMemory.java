package com.example.battleshipgui;
import java.util.Random;

public class AiMemory {
    Random generator = new Random();
    int[][] board=new int[10][10];
    int x;
    int y;
    int cx;
    int cy;
    int state=0;
    int current=1;
    int miss=0;
    int boardSize=10;
    public void setCxCy(int[] c) {
        this.cx = c[0];
        this.cy = c[1];
    }
    public void getBackToCenter(){
        x=cx;
        y=cy;
    }
    public AiMemory(){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                this.board[i][j]=0;
            }
        }
        newCoordinates();
    }
    public int[] nextShotCord(){
        int[] xy=new  int[]{};

        if(current==1){
            xy=new int[] {x+1,y};
        }
        else if(current==2){
            xy= new int[] {x-1,y};
        }
        else if(current==3){
            xy= new int[] {x,y+1};
        }
        else if(current==4){
            xy= new int[] {x,y-1};
        }
        if(check(xy)){
            return xy;
        }
        else{
            miss++;
            if(miss>4){
                miss=0;
                getBackToCenter();

            }
            nextCurrent();
            return nextShotCord();
        }
    }


    public void copyBoard(int[][] board) {
        for(int i=0;i<boardSize;i++){
            System.arraycopy(board[i], 0, this.board[i], 0, boardSize);
        }
        addForbiddenRegions();
    }

    private void addForbiddenRegions() {
        int [][]checkers ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
               if(board[i][j]==9){
                   checkers= new int[][]{{1 + i, j}, {i, j + 1}, {1+i, j + 1}, {i - 1, j}, {i, j - 1}, {i - 1, j - 1}, {i - 1, j + 1}, {1+i , j - 1}};
                    for(int k=0;k<8;k++){
                        if (checkers[k][0] >= 0 && checkers[k][0] < boardSize && checkers[k][1] >= 0 && checkers[k][1] < boardSize){
                            if(board[checkers[k][0]][checkers[k][1]]==0){
                                board[checkers[k][0]][checkers[k][1]]=8;
                            }
                        }
                    }
               }
            }
        }

    }




    public void setX(int x) {
        this.x = x;
    }
    public boolean check(int[] xy){
        if(xy[0]>=0&&xy[0]<10&&xy[1]>=0&&xy[1]<boardSize){
            return board[xy[0]][xy[1]] != 8 && board[xy[0]][xy[1]] != 9;
        }
        else{
            return false;
        }

    }
    public void setFindNewShip() {
        newCoordinates();
        state=0;
    }
    public void newCoordinates() {
        do {
            x = generator.nextInt(0, boardSize);
            y = generator.nextInt(0, boardSize);
        } while (!check(new int[]{x, y}));
    }
    public void nextCurrent() {
        if(current>=4){
            current=1;
        }
        else{
            current++;
        }
    }
    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
