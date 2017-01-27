package com.example.kalin.graduationwork.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kalin on 23.1.2017 г..
 */

public class DBHelper extends SQLiteOpenHelper {

    // columns of the events table
    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_EVENT_ID = "_id";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_EVENT_COLOR = "color";
    public static final String COLUMN_EVENT_NOTE = "note";
    public static final String COLUMN_EVENT_NOTIFICATION = "notification";
    public static final String COLUMN_EVENT_PRICE = "price";
    public static final String COLUMN_EVENT_DURATION_ID = "duration_id";
    public static final String COLUMN_EVENT_LOCATION_ID = "location_id";

    // columns of the durations table
    public static final String TABLE_DURATIONS = "durations";
    public static final String COLUMN_DURATION_ID = COLUMN_EVENT_ID;
    public static final String COLUMN_DURATION_START = "from";
    public static final String COLUMN_DURATION_FINISH = "to";
    public static final String COLUMN_DURATION_REPEAT = "repeat";
    public static final String COLUMN_DURATION_ALLDAY = "all-day";

    // columns of the location table
    public static final String TABLE_LOCATIONS = "locations";
    public static final String COLUMN_LOCATION_ID = COLUMN_EVENT_ID;
    public static final String COLUMN_LOCATION_NAME = "name";
    public static final String COLUMN_LOCATION_LATITUDE = "latitude";
    public static final String COLUMN_LOCATION_LONGITUTE = "longitute";

    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the event table creation
    private static final String SQL_CREATE_TABLE_EVENTS = "CREATE TABLE " + TABLE_EVENTS + "(" +
            COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_EVENT_NAME + " TEXT NOT NULL, " +
            COLUMN_EVENT_COLOR + " TEXT NOT NULL, " +
            COLUMN_EVENT_NOTE + " TEXT NOT NULL, " +
            COLUMN_EVENT_NOTIFICATION + " BOOLEAN, " +
            COLUMN_EVENT_PRICE + " BOOLEAN, " +
            COLUMN_EVENT_DURATION_ID + " INTEGER NOT NULL, " +
            COLUMN_EVENT_LOCATION_ID + " INTEGER NOT NULL " + ");";


    // SQL statement of the duration table creation
    private static final String SQL_CREATE_TABLE_DURATIONS = "CREATE TABLE " + TABLE_DURATIONS + "(" +
            COLUMN_DURATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DURATION_START + " INTEGER, " +
            COLUMN_DURATION_FINISH + " INTEGER, " +
            COLUMN_DURATION_REPEAT + " BOOLEAN, " +
            COLUMN_DURATION_ALLDAY + " BOOLEAN " + ");";

    // SQL statement of the location table creation
    private static final String SQL_CREATE_TABLE_LOCATIONS = "CREATE TABLE "+ TABLE_LOCATIONS + "(" +
            COLUMN_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LOCATION_NAME + " TEXT NOT NULL, " +
            COLUMN_LOCATION_LATITUDE + " INTEGER, " +
            COLUMN_LOCATION_LONGITUTE + " INTEGER " + ");";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
