package com.example.androidswapserviceprovider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ScreenTwo extends AppCompatActivity {

    Button prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);

        prevBtn = (Button) findViewById(R.id.prev_btn);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prev = new Intent(ScreenTwo.this, MainActivity.class);
                startActivity(prev);
            }
        });


    }
}