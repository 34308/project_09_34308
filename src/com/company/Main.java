package com.company;

import com.company.exeptions.IncorrectFileException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, IncorrectFileException {
	// write your code here
        boolean end=false;
        Controller controller=new Controller();
        int gameMode=controller.getGameMode();
        switch (gameMode) {
            case 0 -> {
               break;
            }
            case 1 -> controller.PlayerVsAi();
            case 2 -> controller.PlayerVsPlayer();
        }

    }
}
