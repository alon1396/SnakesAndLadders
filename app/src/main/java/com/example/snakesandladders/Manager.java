package com.example.snakesandladders;

import android.util.Log;

import java.util.Random;

public class Manager {
    private BoardGame boardGame;

    private int money;

    public static final int maxY = 9;
    public static final int maxX = 7;

    private boolean isLeft; // T - לך ימינה F - לך שמאלה
    private int[][] mat;
    private Special[] special;

    private int playerY;
    private int playerX;


    public static final int[] cubes = new int[]{
            0,
            R.drawable.cube1,
            R.drawable.cube2,
            R.drawable.cube3,
            R.drawable.cube4,
            R.drawable.cube5,
            R.drawable.cube6
    };

    public Manager(BoardGame boardGame) {
        this.boardGame = boardGame;
        this.playerY = 8;
        this.playerX = 6;
        mat = new int[maxY][maxX];
        this.isLeft = true;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                mat[i][j] = R.drawable.null_square;
            }
        }
        mat[maxY - 1][maxX - 1] = R.drawable.player;
        this.money = 0;


        this.special = new Special[]{
                new Special(2, 0, 2, 2, "l"),
                new Special(5, 2, 4, 4, "l"),
                new Special(0, 3, 0, 5, "l"),
                new Special(2, 5, 3, 6, "l"),
                new Special(4, 6, 4, 8, "l"),
                new Special(3, 1, 3, 3, "s"),
                new Special(1, 2, 3, 4, "s"),
                new Special(5, 4, 5, 6, "s"),
                new Special(2, 6, 1, 8, "s"),
        };
    }
//    public void isLeft(){
//        if(playerX > 6)
//            this.isLeft = false;
//        if(playerX < 0)
//            this.isLeft = true;
//    }

    public void movePlayer(int steps) {
        int lastY = playerY;
        int lastX = playerX;
        if (this.isLeft)
            movePlayerLeft(steps);
        else
            movePlayerRight(steps);
        mat[lastY][lastX] = R.drawable.null_square;
        mat[playerY][playerX] = R.drawable.player;
    }

    public void movePlayerLeft(int steps) {
        int newY = playerY;
        int newX = playerX - steps;

        if (newX < 0) { // אם שלילי אז צריך לטפל ולהוריד שורה
            if (newY != 0) {
                newX = steps - 1 - playerX;
                newY--;
                isLeft = !isLeft;
            }
            else {
                newX = maxX - steps;
            }
        }
        playerY = newY;
        playerX = newX;
    }
    public void movePlayerRight (int steps) {
        int newY = playerY;
        int newX = playerX + steps;

        if(newX > 6){
            newX = maxX - (newX - maxX) - 1;
            newY--;
            isLeft = !isLeft;
        }
        playerY = newY;
        playerX = newX;
    }

    public int roll() {
        Random rnd = new Random();
        int num = rnd.nextInt(6) + 1;
        movePlayer(num);
        return cubes[num];
    }

    public int getMoney() {
        return money;
    }

    public int[][] getMat() {
        return mat;
    }
}