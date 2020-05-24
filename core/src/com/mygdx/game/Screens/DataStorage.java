package com.mygdx.game.Screens;

import java.util.ArrayList;

public class DataStorage {

    //INSTANCE VARIABLES
    public static ArrayList<Integer> highScoreComputer = new ArrayList<Integer>();
    public static ArrayList<Integer> highScorePV1= new ArrayList<Integer>();
    public static ArrayList<Integer> highScorePV2= new ArrayList<Integer>();
    public static int lossComputer = 0;
    public static int winsComputer = 0;
    public static int scoreComputer = 0;
    public static int winP1 = 0;
    public static int winP2 = 0;
    public static int scoreP1 = 0;
    public static int scoreP2 = 0;

    //GETTERS (ALL OF THEM ACT AS DATA STORAGE METHODS)
    static int getwinP1 (int loss){
        winP1 = loss;
        return loss;
    }
    static int getwinP2 (int loss){
        winP2 = loss;
        return loss;
    }
    static int getScoreP1 (int loss){
        scoreP1 = loss;
        return loss;
    }
    static int getScoreP2 (int loss){
        scoreP2 = loss;
        return loss;
    }
    static int getlossComputer (int loss){
        lossComputer = loss;
        return loss;
    }
    static int getwinsComputer (int wins){
        winsComputer = wins;
        return wins;
    }
    static int getscoreComputer (int score){
        scoreComputer = score;
        return score;
    }
    static ArrayList<Integer> getHighScoreComputer (ArrayList<Integer> highSC){
        highScoreComputer = highSC;
        return highSC;
    }
    static ArrayList<Integer> gethighScorePV1(ArrayList<Integer> highSC){
        highScorePV1 = highSC;
        return highSC;
    }
    static ArrayList<Integer> gethighScorePV2(ArrayList<Integer> highSC){
        highScorePV2 = highSC;
        return highSC;
    }
}
