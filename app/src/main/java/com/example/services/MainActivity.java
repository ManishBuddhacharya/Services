package com.example.services;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import createChannel.CreateChannel;

public class MainActivity extends AppCompatActivity {
    private Button btnfirst, btnSecond;
    NotificationManagerCompat notificationManagerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnfirst = findViewById(R.id.btnFirst);
        btnSecond = findViewById(R.id.btnSecond);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        btnfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification1();
            }
        });
    }

    private void displayNotification1() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.manish_icon)
                .setContentTitle("Hello App")
                .setContentText("Hello !!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        notificationManagerCompat.notify(1, builder.build());

    }

    private void displayNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.manish_icon)
                .setContentTitle("Hello App")
                .setContentText("Hello From the other side")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        notificationManagerCompat.notify(2, builder.build());
    }

    BroadcastRecieverExample broadcastRecieverExample = new BroadcastRecieverExample(this);

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastRecieverExample, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastRecieverExample);
    }
}
