package com.example.snakesandladders;

import android.util.Log;

import java.util.Random;

public class Manager {
    private BoardGame boardGame;

    private int money;

    public static final int maxY = 9;
    public static final int maxX = 7;

    private int lastY;
    private int lastX;

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
                mat[i][j] = R.color.transparent;
            }
        }
        mat[maxY - 1][maxX - 1] = R.drawable.player;
        this.money = 0;

        this.special = new Special[]{
                new Special(2, 2, 2, 0, "l"),
                new Special(4, 3, 3, 2, "l"),
                new Special(6, 5, 6, 3, "l"),
                new Special(6, 1, 4, 2, "l"),
                new Special(4, 8, 4, 6, "l"),
                new Special(0, 5, 2, 4, "s"),
                new Special(1, 2, 1, 4, "s"),
                new Special(3, 4, 5, 6, "s"),
                new Special(3, 5, 3, 7, "s"),
        };
    }

    public void movePlayer(int steps, boolean finalStep) {
        this.money += steps * 30000;
        lastY = playerY;
        lastX = playerX;
        if (this.isLeft)
            movePlayerLeft(steps);
        else
            movePlayerRight(steps);
        if (finalStep)
            movePlayerSpecial();
        mat[lastY][lastX] = R.color.transparent;
        mat[playerY][playerX] = R.drawable.player;
    }

//    public void updateMat() {
//
//    }

    public void movePlayerLeft(int steps) {
        int newY = playerY;
        int newX = playerX - steps;

        if (newX < 0) { // אם שלילי אז צריך לטפל ולהוריד שורה
            if (newY != 0) {
                newX = steps - 1 - playerX;
                newY--;
                isLeft = !isLeft;
            } else {
                newX = maxX - steps;
            }
        }
        playerY = newY;
        playerX = newX;
    }

    public void movePlayerRight(int steps) {
        int newY = playerY;
        int newX = playerX + steps;

        if (newX > 6) {
            newX = maxX - (newX - maxX) - 1;
            newY--;
            isLeft = !isLeft;
        }
        playerY = newY;
        playerX = newX;
    }

    public void movePlayerSpecial() {
        for (int i = 0; i < special.length; i++) {
            if (this.special[i].getStartX() == playerX && this.special[i].getStartY() == playerY) {
                playerX = this.special[i].getEndX();
                playerY = this.special[i].getEndY();
                isLeft = playerY % 2 == 0;
                if (this.special[i].getType() == "l") {
                    this.money += (63 - (this.special[i].getEndX() * this.special[i].getEndY()) - (63 - (playerX * playerY))) * 2;
                } else
                    this.money -= (63 - (playerX * playerY)) - (63 - (this.special[i].getEndX() * this.special[i].getEndY())) * 2;
                return;
            }
        }
    }

    public int roll() {
        Random rnd = new Random();
        int num = rnd.nextInt(6) + 1;
        return num;
    }

    public int getMoney() {
        return money;
    }

    public int[][] getMat() {
        return mat;
    }

    public boolean didWin() {
        return playerX == 0 && playerY == 0;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getLastY() {
        return lastY;
    }

    public int getLastX() {
        return lastX;
    }
}