package com.example.androidswapserviceprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.widget.Toast;

public class SampleReceiver extends BroadcastReceiver {

    public SampleReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        System.out.println("Intent Received!!");
//        Toast.makeText(context,"intent received:",Toast.LENGTH_LONG).show();
//        Intent singSong = new Intent(context, SingSongService.class);
//        context.startService(singSong);
    }
}