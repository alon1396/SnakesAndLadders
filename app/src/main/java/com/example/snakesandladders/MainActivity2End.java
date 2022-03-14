package com.example.snakesandladders;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity2End extends AppCompatActivity {

    private int money;
    private TextView endMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_end);

        Bundle extras = getIntent().getExtras();

        if(extras != null)
        {
            this.money = extras.getInt("money");
        }
        endMoney = (TextView) findViewById(R.id.EndMoney);
        endMoney.setText(String.valueOf(money));
    }
}