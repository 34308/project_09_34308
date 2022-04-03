package com.example.battleshipgui.exeptions;

public class WrongCoordinateException extends RuntimeException {
    public WrongCoordinateException(String s){
        super(s);
    }
}
