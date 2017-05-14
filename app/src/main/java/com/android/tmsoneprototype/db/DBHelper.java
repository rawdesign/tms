package com.android.tmsoneprototype.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.tmsoneprototype.app.TMSOnePrototypeApp;
import com.android.tmsoneprototype.db.repo.PropertyRepo;
import com.android.tmsoneprototype.util.Const;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper() {
        super(TMSOnePrototypeApp.getContext(), Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(PropertyRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));
        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_PROPERTY);
        onCreate(db);
    }

}