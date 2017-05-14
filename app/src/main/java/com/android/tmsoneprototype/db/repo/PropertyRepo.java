package com.android.tmsoneprototype.db.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.tmsoneprototype.db.DBManager;
import com.android.tmsoneprototype.db.model.PropertyAdd;
import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.util.Const;

import java.util.ArrayList;
import java.util.List;

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
     * Get all property into SQLite DB
     */
    public List<PropertyList> getAll() {
        PropertyList property = new PropertyList();
        List<PropertyList> propertyLists = new ArrayList<PropertyList>();

        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        String query = "SELECT * FROM " + Const.TABLE_PROPERTY;
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                property = new PropertyList();
                property.setId(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_ID)));
                property.setOwner(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_OWNER)));
                property.setTitle(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_TITLE)));
                property.setAddress(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_ADDRESS)));
                property.setPrice(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_PRICE)));
                property.setImg(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_IMG)));
                property.setImgThmb(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_IMG_THMB)));
                property.setStatus(cursor.getString(cursor.getColumnIndex(Const.FIELD_PROPERTY_STATUS)));

                propertyLists.add(property);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DBManager.getInstance().closeDatabase();

        return propertyLists;
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

    /**
     * Delete all record
     */
    public void deleteAll() {
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        db.delete(Const.TABLE_PROPERTY, null, null);
        DBManager.getInstance().closeDatabase();
    }

}