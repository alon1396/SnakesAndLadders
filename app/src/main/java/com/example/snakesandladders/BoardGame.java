package com.example.snakesandladders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class BoardGame extends AppCompatActivity implements View.OnClickListener {
    private Manager manager;

    private ImageView[][] pics;

    private ImageButton cube;

    private int[][] mat;

    private int i, j, resId;

    private String str;

    private Button restart, back;

    public TextView moneyTxtV;

    private int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game);

        manager = new Manager(this);

        cube = (ImageButton) findViewById(R.id.cube);
        cube.setOnClickListener(this);

        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(this);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        moneyTxtV = (TextView) findViewById(R.id.money);
        moneyTxtV.setOnClickListener(this);

        mat = manager.getMat();

        pics = new ImageView[9][7];
        for (i = 0; i < pics.length; i++) {
            for (j = 0; j < pics[i].length; j++) {
                str = "imageView" + i + j;
                resId = getResources().getIdentifier(str, "id", getPackageName());
                pics[i][j] = (ImageView) findViewById(resId);
                pics[i][j].setImageResource(mat[i][j]);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.back) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.restart) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    pics[i][j].setImageResource(R.color.transparent);
                }
            }
            pics[8][6].setImageResource(R.drawable.player);
        }
        if (v.getId() == R.id.cube) {
            int roll = manager.roll();
            cube.setImageResource(Manager.cubes[roll]);
            for (int k = 0; k < roll; k++) {
                manager.movePlayer(1, k == k - 1);
                setPics();
                setMoney(manager.getMoney());
                try {
                    Thread.sleep(150);
                } catch (InterruptedException err) {}
            }
//            manager.updateMat(manager.getLastX(), manager.getLastY());
        }
        if (this.manager.didWin()) //האם הגענו לסוף המשחק
        {
            this.money = this.manager.getMoney();
            intent = new Intent(this, MainActivity2End.class);
            intent.putExtra("money", money);
            startActivity(intent);
        }
    }

    public void setMoney(int money) {
        this.moneyTxtV.setText(String.valueOf(money));
    }

    public void setPics() {
        mat = manager.getMat();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                if (mat[i][j] == R.drawable.player) {
                    pics[i][j].setImageResource(mat[i][j]);
                }
                else
                    pics[i][j].setImageResource(R.color.transparent);
            }
        }
    }
}