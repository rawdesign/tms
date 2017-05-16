package com.android.tmsoneprototype.app;

import android.app.Application;
import android.content.Context;

import com.android.tmsoneprototype.db.DBHelper;
import com.android.tmsoneprototype.db.DBManager;

public class TMSOnePrototypeApp extends Application {

    private static Context context;
    private static DBHelper dbHelper;

    // Gloabl declaration of variable to use in whole app
    public static boolean activityVisible; // Variable that will check the current activity state

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DBManager.initializeInstance(dbHelper);
    }

    public static Context getContext(){
        return context;
    }

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true; // this will set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false; // this will set false when activity paused

    }

}