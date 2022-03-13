package com.example.snakesandladders;

import java.util.Random;

public class Manager
{
    private BoardGame boardGame;

    public static final int maxX = 7;
    public static final int maxY = 9;

    private int[][] mat;
    private Special[] special;
    private int money;

    private Player player;

    public static final int[] cubes = new int[] {
            0,
            R.drawable.cube1,
            R.drawable.cube2,
            R.drawable.cube3,
            R.drawable.cube4,
            R.drawable.cube5,
            R.drawable.cube6
    };

    public Manager(BoardGame boardGame)
    {
        this.boardGame = boardGame;
        this.player = new Player(0, 0);
        this.mat = new int[maxX][maxY];
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat[0].length; j++)
            {
                this.mat[i][j] = R.drawable.null_square;
            }
        }
        this.mat[0][0] = R.drawable.player;
        System.out.println(this.mat[0][5]);
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

    public void movePlayer(int steps) {
        this.money += steps * 30000;
        int x = steps + player.getX();
        int y = this.player.getY();
        int prevX = x;
        int prevY = y;
        if (x >= maxX) {
            x = x - maxX;
            if (y < maxY) {
                y++;
            } else {
                x = maxX - (maxX - steps) - 1;
            }
        }
        this.player.setX(x);
        this.player.setY(y);

        moveSpecial();

        this.mat[this.player.getX()][this.player.getY()] = R.drawable.player;
        this.mat[prevX][prevY] = R.drawable.null_square;
    }

    public void moveSpecial() {
        int x = this.player.getX();
        int y = this.player.getY();
        for (int i = 0; i < this.special.length; i++) {
            Special special = this.special[i];
            if (special.getType() == "s") {
                if (special.getEndX() == x && special.getEndY() == y) {
                    this.money -= 30000 * ((special.getEndX() + 1) * (special.getEndY() + 1) - (special.getStartX() + 1) * (special.getStartY() + 1));;
                    if (this.money < 0) this.money = 0;
                    this.player.setX(special.getStartX());
                    this.player.setY(special.getStartY());
                    return;
                }

            } else if (special.getType() == "l") {
                if (special.getStartX() == x && special.getStartY() == y) {
                    this.money += 30000 * ((special.getEndX() + 1) * (special.getEndY() + 1) - (special.getStartX() + 1) * (special.getStartY() + 1));
                    this.player.setX(special.getEndX());
                    this.player.setY(special.getStartY());
                    return;
                }
            }
        }
    }

    public int roll() {
        Random rnd = new Random();
        int num = rnd.nextInt(6) + 1;
        movePlayer(num);
        return cubes[num];
    }

    public int[][] getMat() {
        return mat;
    }

    public int getMoney() {
        return money;
    }
}