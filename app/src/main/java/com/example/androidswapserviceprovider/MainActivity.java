package com.example.androidswapserviceprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button nextBtn;
    Button unboundServiceBtn;
    Button boundServiceBtn;
    Button sendBroadcastBtn;
    Button bgThreadBtn;
    Button fgThreadBtn;
    ProgressBar pBar;
    Handler threadHandler1;
    Handler threadHandler2 = new Handler();
    private Runnable foregroundTask = new Runnable() {
        @Override
        public void run() {
            pBar.incrementProgressBy(1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BroadcastReceiver br = new SampleReceiver();
        IntentFilter brFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        brFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, brFilter);

        pBar = (ProgressBar) findViewById(R.id.p_bar);
        pBar.setMax(100);

        threadHandler1 = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.d("LOG_TAG", "handleMessage in threadHandler1 with msg: " + msg.obj);
                Toast.makeText(MainActivity.this,"handling the message in threadHandler1 with msg: " + msg.obj, Toast.LENGTH_LONG).show();
            }
        };

        //
        bgThreadBtn = (Button) findViewById(R.id.bg_thread_btn);
        bgThreadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = threadHandler1.obtainMessage(1); // obtain a free token from handle and update it message
                        msg.obj = "Hello from thread";
                        threadHandler1.sendMessage(msg); // sends the message
//                        bgThreadBtn.setText("This is now handled"); // throws an error
//                        Toast.makeText(MainActivity.this,"This is BG thread", Toast.LENGTH_LONG).show();
                    }
                });
                thread.start();
            }
        });




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
                        Log.d("LOG_TAG", "onServiceConnected");
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
//                        Toast.makeText(MainActivity.this, "Exiting...\n", Toast.LENGTH_LONG).show();
                        System.out.println("Disconnected");
                        Log.d("LOG_TAG", "onServiceDisconnected");
                    }
                };
                bindService(startBoundService, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        sendBroadcastBtn = (Button) findViewById(R.id.send_broadcast_btn);
        sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Sending the message...", Toast.LENGTH_LONG).show();
                Intent broadcast = new Intent();
                broadcast.setAction("First Broadcast");
                sendBroadcast(broadcast);
            }
        });

    }
}