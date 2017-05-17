package com.android.tmsoneprototype.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.android.tmsoneprototype.util.Utils;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetworkReceiver extends BroadcastReceiver {

    public static final String NETWORK_AVAILABLE_ACTION = "com.android.tmsoneprototype.NetworkAvailable";
    public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context));
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
    }

    private boolean isConnectedToInternet(Context context) {
        try {
            Utils.displayToast(context, "Looking for Networks...", Toast.LENGTH_SHORT);
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.
                        getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.
                        getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}