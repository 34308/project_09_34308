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
    int current;
    int miss=0;
    public void setCxCy(int c[]) {
        this.cx = c[0];
        this.cy = c[1];
    }
    public void getBackToCenter(){
        x=cx;
        y=cy;
    }
    public AiMemory(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                this.board[i][j]=0;
            }
        }
        newCoordinates();
    }
    public int[] nextShotCord(){
        int[] xy=new  int[]{0,0};
        if(current==1){
            xy=new int[] {x+1,y};
        }
        if(current==2){
            xy= new int[] {x-1,y};
        }
        if(current==3){
            xy= new int[] {x,y+1};
        }
        if(current==4){
            xy= new int[] {x,y-1};
        }
        if(check(xy)){
            return xy;
        }
        else{
            miss++;
            if(miss>4){
                miss=0;
                setFindNewShip();
            }
            nextCurrent();
            return nextShotCord();
        }
    }


    public void copyBoard(int[][] board) {
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                this.board[i][j]=board[i][j];
            }
        }
    }

    public void newCoordinates() {
        do {
            x = generator.nextInt(0, 10);
            y = generator.nextInt(0, 10);
        } while (!check(new int[]{x, y}));
    }


    public void setX(int x) {
        this.x = x;
    }
    public boolean check(int[] xy){
        if(xy[0]>=0&&xy[0]<10&&xy[1]>=0&&xy[1]<10){
            if(board[xy[0]][xy[1]]==8||board[xy[0]][xy[1]]==9){
                return false;
            }
            else{
                return true;
            }

        }
        else{
            return false;
        }

    }
    public void setFindNewShip() {
        newCoordinates();
        state=0;
    }
    public void randomCurrent() {
        current=generator.nextInt(1,4);
    }
    public void nextCurrent() {
        if(current>=4){
            current=1;
        }
        else{
            current++;
        }
    }
    public void oppositeCurrent() {
        if(current==1){
            current=2;
        }
        else if(current==2){
            current=1;
        }
        else if(current==3){
            current=4;
        }
        else if(current==4){
            current=3;
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
