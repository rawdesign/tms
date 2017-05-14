package com.android.tmsoneprototype.db.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.android.tmsoneprototype.db.DBManager;
import com.android.tmsoneprototype.db.model.PropertyAdd;
import com.android.tmsoneprototype.util.Const;

public class PropertyRepo {

    public static String createTable() {
        return "CREATE TABLE " + Const.TABLE_PROPERTY + "("
                + Const.FIELD_PROPERTY_ID  + " PRIMARY KEY,"
                + Const.FIELD_PROPERTY_OWNER + " TEXT,"
                + Const.FIELD_PROPERTY_TITLE+ " TEXT,"
                + Const.FIELD_PROPERTY_ADDRESS + " TEXT,"
                + Const.FIELD_PROPERTY_PRICE + " TEXT,"
                + Const.FIELD_PROPERTY_IMG + " TEXT,"
                + Const.FIELD_PROPERTY_IMG_THMB + " TEXT,"
                + Const.FIELD_PROPERTY_STATUS + " TEXT)";
    }

    /**
     * Insert property into SQLite DB
     */
    public int insert(PropertyAdd propertyAdd) {
        int id;
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.FIELD_PROPERTY_ID, propertyAdd.getId());
        values.put(Const.FIELD_PROPERTY_OWNER, propertyAdd.getOwner());
        values.put(Const.FIELD_PROPERTY_TITLE, propertyAdd.getTitle());
        values.put(Const.FIELD_PROPERTY_ADDRESS, propertyAdd.getAddress());
        values.put(Const.FIELD_PROPERTY_PRICE, propertyAdd.getPrice());
        values.put(Const.FIELD_PROPERTY_IMG, propertyAdd.getImg());
        values.put(Const.FIELD_PROPERTY_IMG_THMB, propertyAdd.getImgThmb());
        values.put(Const.FIELD_PROPERTY_STATUS, propertyAdd.getStatus());

        // Inserting row
        id = (int) db.insert(Const.TABLE_PROPERTY, null, values);
        DBManager.getInstance().closeDatabase();

        return id;
    }

}