package com.example.theripper.a2048;

import android.content.SharedPreferences;

/**
 * Created by The Ripper on 12/19/2017.
 */

public class Data {
    private static int row=4;
    private static int col=4;
    static int Score;
    static boolean Gameover;
    static SharedPreferences sharedPreferences;


    public static int getRow() {
        return row;
    }

    public static int getCol() {
        return col;
    }

    public static int getScore() {
        return Score;
    }

    public static boolean isGameover() {
        return Gameover;
    }
}
