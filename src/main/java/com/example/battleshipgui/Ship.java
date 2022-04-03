package com.example.battleshipgui;

public class Ship {
    //stan statku zbity czy nie
    //ilośc punktow zycia danego statku
    int hitPoints;
    //wielkosc danego statku
    int size;
    //pozycje do ktorych jest przypisany na mapie
    int numberOfPositions;
    int[][] position;
    //konstruktor podstawowy statku
    public Ship() {
        this.hitPoints = 0;
        this.size = 0;
        this.position = new int[1][1];
    }
    //konstruktor 2
    public Ship(int hitPoints, int size, int[][] position) {
        this.hitPoints = hitPoints;
        this.size = size;
        this.position = position;
    }
    //umozliwia przypisanie statkowi rozmiaru punktow zycia i pozycji
    public void changeShip(int size,int[][] position){
        this.hitPoints = size;
        this.size = size;
        this.position = position;
    }
    //zwraca pozycje
    public int[][] getPosition() {
        return position;
    }
    //zwraca rozmiar
    public int getSize() {
        return size;
    }
    //zmienjsza punkty zycia statku o jeden za kazde wywolanie

    public void hitted(){
        System.out.println("Trafiony");
        this.hitPoints--;
        if(hitPoints<=0){
            System.out.println("Zatopiony");
        }
    }
    //zwraca alive uzywany do sprawdzenia czy statek nadal funkcjonujes
    public boolean isAlive(){
        if(hitPoints<=0){
            return false;
        }
        return true;
    }
}
