package com.company;

import com.company.exeptions.IncorrectFileException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, IncorrectFileException {
        boolean end=false;
        Controller controller=new Controller();
        int gameMode=controller.getGameMode();
        switch (gameMode) {
            case 0 -> {
                System.exit(0);
                break;
            }
            case 1 -> {
                controller.PlayerVsAi();
                break;
            }
            case 2 ->{
                controller.PlayerVsPlayer();
                break;
            }
        }
    }
}
//dodanie kontrolera gracz vs AI