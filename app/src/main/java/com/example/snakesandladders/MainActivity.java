package com.example.snakesandladders;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button gameON;

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameON = (Button) findViewById(R.id.gameON);
        gameON.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = null;
        if(v.getId() == R.id.gameON)
        {
            intent = new Intent(this, BoardGame.class);
            startActivity(intent);
        }
    }
}