package com.example.androidswapserviceprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button nextBtn;
    Button unboundServiceBtn;
    Button boundServiceBtn;

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

        boundServiceBtn = (Button) findViewById(R.id.start_bound_service_btn);
        boundServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startBoundService = new Intent(MainActivity.this, SingSongService.class);

                ServiceConnection serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
//                        Toast.makeText(MainActivity.this, "Connected!\n", Toast.LENGTH_LONG).show();
                        System.out.println("Connected");
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
//                        Toast.makeText(MainActivity.this, "Exiting...\n", Toast.LENGTH_LONG).show();
                        System.out.println("Disconnected");
                    }
                };
                bindService(startBoundService, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });


    }
}