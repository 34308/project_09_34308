package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileValidator {
    final int boardSize=10;
    ArrayList<int[]>  forbiddenPositions=new ArrayList();

    public boolean isValid(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line;
        int x=0;int y=0;int li=0;
        while (sc.hasNext()) {
            x=0; y=0; li=0;
            line = sc.nextLine();
            String[] splitedLine = line.split(" ");
            int sizeOfShip = splitedLine.length;
            for (int i = 0; i < sizeOfShip; i++) {
                int[] forbiddenPosition=new int[2];

                if(i<sizeOfShip-1){
                    if(!Objects.equals(splitedLine[i].charAt(0), splitedLine[i+1].charAt(0)) ){
                        if(!Objects.equals(splitedLine[i].charAt(1), splitedLine[i+1].charAt(1)) ){
                            return false;
                        }
                    }
                }
                char[] charCoordinates = splitedLine[i].toCharArray();
                x = charCoordinates[0] - '0';
                y = charCoordinates[1] - '0';

                if(!isPositionValid(x,y)){
                    return false;
                }
                forbiddenPosition[0]=x;
                forbiddenPosition[1]=y;
                forbiddenPositions.add(forbiddenPosition);
                li++;
            }
            addAllForbiddenPositions(li);
        }
        return true;
    }

    private void addAllForbiddenPositions(int li){
        int x=0;
        int y=0;
        int size=forbiddenPositions.size();
        for(int i=size-1;i>=size-li;i--){
            x=forbiddenPositions.get(i)[0];
            y=forbiddenPositions.get(i)[1];
            forbiddenPositions.remove(i);

            int[][] checkers = {{x + 1, y}, {x, y + 1}, {x + 1, y + 1}, {x - 1, y}, {x, y - 1}, {x - 1, y - 1}, {x - 1, y + 1}, {x + 1, y - 1}};
            for (int j = 0; j < 8; j++) {
                int[] forbiddenPosition=new int[2];
                if (checkers[j][0] >= 0 && checkers[j][0] < boardSize && checkers[j][1] >= 0 && checkers[j][1] < boardSize){
                    forbiddenPosition[0]=checkers[j][0];
                    forbiddenPosition[1]=checkers[j][1];
                    forbiddenPositions.add(forbiddenPosition);
                }
            }
        }
    }

    private boolean isPositionValid(int x,int y){
        for(int j=0;j<forbiddenPositions.size();j++){
            if(forbiddenPositions.get(j)[0]==x&&forbiddenPositions.get(j)[1]==y){
                System.out.println(forbiddenPositions.get(j)[0]+" "+forbiddenPositions.get(j)[1]);
                return false;
            }
        }
        return true;
    }
}