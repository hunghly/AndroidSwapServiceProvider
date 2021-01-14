package com.example.androidswapserviceprovider;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SingSongService extends Service {
    public SingSongService() {
    }

    @Override
    public void onCreate() {
//        try {
//            Toast.makeText(this, "This is unbound so if you exit the app, the service continues to run.\n", Toast.LENGTH_LONG).show();
//            singQueen();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("Inside Bind");
        try {
            Toast.makeText(this, "This is bound so if you exit the app, the service stops.\n", Toast.LENGTH_LONG).show();
            singQueen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void singQueen() throws InterruptedException {
        Toast.makeText(this, "Buddy, you're a boy, make a big noise\n", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Playing in the street, gonna be a big man someday\n", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "You got mud on your face, you big disgrace\n", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Kicking your can all over the place, singin'\n", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "We will, we will rock you\n", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "We will, we will rock you\n", Toast.LENGTH_LONG).show();
    }
}