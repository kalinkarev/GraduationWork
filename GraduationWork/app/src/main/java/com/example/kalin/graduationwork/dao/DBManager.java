package com.example.kalin.graduationwork.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kalin.graduationwork.model.Duration;
import com.example.kalin.graduationwork.model.Event;
import com.example.kalin.graduationwork.model.Location;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class DBManager {

    private static DBManager instance;

    public static DBManager getInstance(Context context) {
        if (instance == null) {
            instance = new DBManager(context);
        }
        return instance;
    }

    private Context context;
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;

    private DBManager(Context context) {
        this.context = context;
        mDBHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close() {
        mDBHelper.close();
    }

    public synchronized void addEvent(Event event, boolean toUpdate) {
        open();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_EVENT_NAME, event.getName());
        values.put(DBHelper.COLUMN_EVENT_COLOR, String.valueOf(event.getColor()));
        values.put(DBHelper.COLUMN_EVENT_NOTE, event.getNote());
        values.put(DBHelper.COLUMN_EVENT_NOTIFICATION, event.getNotification());
        values.put(DBHelper.COLUMN_EVENT_PRICE, event.getPrice());

        long insertId = mDatabase.insert(DBHelper.TABLE_EVENTS, null, values);

        ContentValues valuesDuration = new ContentValues();
        valuesDuration.put(DBHelper.COLUMN_DURATION_START, event.getDuration().getStart());
        valuesDuration.put(DBHelper.COLUMN_DURATION_FINISH, event.getDuration().getFinish());
        valuesDuration.put(DBHelper.COLUMN_DURATION_REPEAT, event.getDuration().getRepeat());
        valuesDuration.put(DBHelper.COLUMN_DURATION_ALLDAY, event.getDuration().getAllday());
        valuesDuration.put(DBHelper.COLUMN_DURATION_EVENT_ID, insertId);

        mDatabase.insert(DBHelper.TABLE_DURATIONS, null, valuesDuration);

        ContentValues valuesLocation = new ContentValues();
        valuesLocation.put(DBHelper.COLUMN_LOCATION_NAME, event.getLocation().getName());
        valuesLocation.put(DBHelper.COLUMN_LOCATION_LONGITUTE, event.getLocation().getLongitute());
        valuesLocation.put(DBHelper.COLUMN_LOCATION_LATITUDE, event.getLocation().getLatitude());
        valuesLocation.put(DBHelper.COLUMN_LOCATION_EVENT_ID, insertId);

        mDatabase.insert(DBHelper.TABLE_LOCATIONS, null, valuesLocation);

        close();
    }

    public synchronized void deleteEvent(Event event) {
        mDatabase.delete(DBHelper.TABLE_EVENTS, DBHelper.COLUMN_EVENT_ID + " = " + id, null);
        mDatabase.delete(DBHelper.TABLE_LOCATIONS, DBHelper.COLUMN_LOCATION_EVENT_ID + " = " + id, null);
        mDatabase.delete(DBHelper.TABLE_DURATIONS, DBHelper.COLUMN_DURATION_EVENT_ID + " = " + id, null);
    }

    public synchronized List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_EVENTS, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Event event = new Event(cursor);

                Cursor cursorDuration = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_DURATIONS + " WHERE "
                        + DBHelper.COLUMN_DURATION_EVENT_ID + "=" + event.getId(), null);
                if (cursorDuration.moveToNext()) {
                    Duration duration = new Duration(cursorDuration);
                    event.setDuration(duration);
                }
                cursorDuration.close();

                Cursor cursorLocation = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_LOCATIONS + " WHERE "
                        + DBHelper.COLUMN_LOCATION_EVENT_ID + "=" + event.getId(), null);
                if (cursorLocation.moveToNext()) {
                    Location location = new Location(cursorLocation);
                    event.setLocation(location);
                }
                cursorLocation.close();

                events.add(event);
            }

            cursor.close();
        }

        return events;
    }
}