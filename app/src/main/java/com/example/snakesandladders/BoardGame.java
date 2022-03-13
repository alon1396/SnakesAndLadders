package com.example.snakesandladders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BoardGame extends AppCompatActivity implements View.OnClickListener
{
    private Manager manager;

    private ImageView[][] pics;

    private ImageButton cube;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game);
    }

    @Override
    public void onClick(View v)
    {

    }
}