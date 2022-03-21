package com.company;

import com.company.exeptions.IncorrectFileException;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws IncorrectFileException, FileNotFoundException {
        Controller controller=new Controller();
        controller.PlayerVsAi();

    }
}
//dodanie kontrolera gracz vs AI