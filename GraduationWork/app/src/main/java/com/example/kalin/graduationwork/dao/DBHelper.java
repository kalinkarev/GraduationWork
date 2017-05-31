package com.example.kalin.graduationwork.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    // columns of the events table
    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_EVENT_ID = "_id";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_EVENT_COLOR = "color";
//    public static final String COLUMN_EVENT_NOTE = "note";
    public static final String COLUMN_EVENT_NOTIFICATION = "notification";
    public static final String COLUMN_EVENT_PRICE = "price";
    public static final String COLUMN_EVENT_CHECKED = "checked";

    // columns of the durations table
    public static final String TABLE_DURATIONS = "durations";
    public static final String COLUMN_DURATION_ID = COLUMN_EVENT_ID;
    public static final String COLUMN_DURATION_START = "start";
    public static final String COLUMN_DURATION_FINISH = "finish";
//    public static final String COLUMN_DURATION_REPEAT = "repeat";
    public static final String COLUMN_DURATION_ALLDAY = "allDay";
    public static final String COLUMN_DURATION_EVENT_ID = "event_id";

    // columns of the location table
    public static final String TABLE_LOCATIONS = "locations";
    public static final String COLUMN_LOCATION_ID = COLUMN_EVENT_ID;
    public static final String COLUMN_LOCATION_NAME = "name";
    public static final String COLUMN_LOCATION_LATITUDE = "latitude";
    public static final String COLUMN_LOCATION_LONGITUTE = "longitute";
    public static final String COLUMN_LOCATION_EVENT_ID = "event_id";

    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_EVENTS = "create table "
            + TABLE_EVENTS + "( "
            + COLUMN_EVENT_ID + " integer primary key autoincrement, "
            + COLUMN_EVENT_NAME + " text not null, "
            + COLUMN_EVENT_COLOR + " integer, "
//            + COLUMN_EVENT_NOTE + " text not null, "
            + COLUMN_EVENT_NOTIFICATION + " integer, "
            + COLUMN_EVENT_CHECKED + " integer, "
            + COLUMN_EVENT_PRICE + " integer not null);";

    // Database creation sql statement
    private static final String DATABASE_CREATE_DURATIONS = "create table "
            + TABLE_DURATIONS + "( "
            + COLUMN_DURATION_ID + " integer primary key autoincrement, "
            + COLUMN_DURATION_START + " long, "
            + COLUMN_DURATION_FINISH + " long, "
//            + COLUMN_DURATION_REPEAT + " integer, "
            + COLUMN_DURATION_ALLDAY + " integer, "
            + COLUMN_DURATION_EVENT_ID + " integer not null);";

    // Database creation sql statement
    private static final String DATABASE_CREATE_LOCATIONS = "create table "
            + TABLE_LOCATIONS + "( "
            + COLUMN_LOCATION_ID + " integer primary key autoincrement, "
            + COLUMN_LOCATION_NAME + " text not null, "
            + COLUMN_LOCATION_LATITUDE + " string, "
            + COLUMN_LOCATION_LONGITUTE + " string, "
            + COLUMN_LOCATION_EVENT_ID + " integer not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_EVENTS);
        db.execSQL(DATABASE_CREATE_DURATIONS);
        db.execSQL(DATABASE_CREATE_LOCATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DURATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);

        onCreate(db);
    }

    public boolean updateDB(String status, String what, int value) {
        return false;
    }

}