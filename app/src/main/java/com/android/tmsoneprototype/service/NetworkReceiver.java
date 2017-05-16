package com.android.tmsoneprototype.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.tmsoneprototype.app.TMSOnePrototypeApp;
import com.android.tmsoneprototype.ui.HomeActivity;
import com.android.tmsoneprototype.util.Utils;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        try {
            // Check if activity is visible or not
            boolean isVisible = TMSOnePrototypeApp.isActivityVisible();
            Utils.displayToast(context, "Looking for Networks...", Toast.LENGTH_SHORT);

            // If it is visible then trigger the task else do nothing
            if (isVisible == true) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager
                        .getActiveNetworkInfo();

                // Check internet connection and accrding to state change the
                // text of activity by calling method
                if (networkInfo != null && networkInfo.isConnected()) {
                    new HomeActivity().checkNetwork(context, true);
                } else {
                    new HomeActivity().checkNetwork(context, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}