package com.example.androidswapserviceprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button nextBtn;
    Button unboundServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // used for swapping to screen two
        nextBtn = (Button) findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, ScreenTwo.class);
                startActivity(next);
            }
        });

        unboundServiceBtn = (Button) findViewById(R.id.start_unbound_service_btn);
        unboundServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startUnboundService = new Intent(MainActivity.this, SingSongService.class);
                startService(startUnboundService);
            }
        });

    }
}