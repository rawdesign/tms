package com.android.tmsoneprototype.util;

public class Const {
    //----------------- URL -----------------//
    public static final String BASE_URL = "http://www.bonitajewelery.com/devel/tms/";
    public static final String BASE_API_URL = BASE_URL + "api/"; //api base url

    //----------------- DATABASE -----------------//
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "tms_one_prototype.db";

    //----------------- TABLE -----------------//
    public static final String TABLE_PROPERTY = "t_property";

    //----------------- FIELD -----------------//
    public static final String FIELD_PROPERTY_ID = "property_id";
    public static final String FIELD_PROPERTY_OWNER = "property_owner";
    public static final String FIELD_PROPERTY_TITLE = "property_title";
    public static final String FIELD_PROPERTY_ADDRESS = "property_address";
    public static final String FIELD_PROPERTY_PRICE = "property_price";
    public static final String FIELD_PROPERTY_IMG = "property_img";
    public static final String FIELD_PROPERTY_IMG_THMB = "property_img_thmb";
    public static final String FIELD_PROPERTY_STATUS = "property_status";
    public static final String FIELD_PROPERTY_CREATE_DATE = "property_create_date";
}