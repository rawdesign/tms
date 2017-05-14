package com.android.tmsoneprototype.app;

import android.app.Application;
import android.content.Context;

import com.android.tmsoneprototype.db.DBHelper;
import com.android.tmsoneprototype.db.DBManager;

public class TMSOnePrototypeApp extends Application {

    private static Context context;
    private static DBHelper dbHelper;

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

}