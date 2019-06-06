package com.example.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class BroadcastRecieverExample extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity;

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnectivity){
                Toast.makeText(context,"Disconnected", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context,"Connected", Toast.LENGTH_LONG).show();
            }
        }
    }
}
