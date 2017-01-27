package com.example.kalin.graduationwork.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.EventLog;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Kalin on 23.1.2017 г..
 */

public class EventDAO {

    public static final String TAG = "EventDAO"; // to show where we are

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private Context mContext;
    private String[] mAllColumns = { DBHelper.COLUMN_EVENT_ID,
                                    DBHelper.COLUMN_EVENT_NAME,
                                    DBHelper.COLUMN_EVENT_COLOR,
                                    DBHelper.COLUMN_EVENT_NOTE,
                                    DBHelper.COLUMN_EVENT_NOTIFICATION,
                                    DBHelper.COLUMN_EVENT_PRICE };

    public EventDAO(Context context) {
        this.mContext = context;
        mDBHelper = new DBHelper(context);

        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opnenning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close() {
        mDBHelper.close();
    }

    public Event createEvent(String name, String color, String note, Boolean notification,Float price) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_EVENT_NAME, name);
        values.put(DBHelper.COLUMN_EVENT_COLOR, color);
        values.put(DBHelper.COLUMN_EVENT_NOTE, note);
        values.put(DBHelper.COLUMN_EVENT_NOTIFICATION, notification);
        values.put(DBHelper.COLUMN_EVENT_PRICE, price);

        long insertId = mDatabase.insert(DBHelper.TABLE_EVENTS, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_EVENTS, mAllColumns, DBHelper.COLUMN_EVENT_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Event newEvent = cursorToEvent(cursor);
        cursor.close();
        return newEvent;
    }

    public void deleteEvent(Event event) {
        long id = event.getId();
        LocationDAO locationDao = new LocationDAO(mContext);
        List<Location> listLocations = locationDao.getLocationsOfEvent(id);
        if (listLocations != null && !listLocations.isEmpty()) {
            for (Location l : listLocations) {
                locationDao.deleteLocation(l);
            }
        }

        System.out.println("the deleted event has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_EVENTS, DBHelper.COLUMN_EVENT_ID + " = " + id, null);

    }

    public List<Event> getAllEvents() {
        List<Event> listEvents = new ArrayList<Event>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_EVENTS, mAllColumns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Event event = cursorToEvent(cursor);
                listEvents.add(event);
                cursor.moveToNext();
            }

            cursor.close();
        }
        return listEvents;
    }

    public Event getEventById(long id) {
        Cursor cursor = mDatabase(DBHelper.TABLE_EVENTS, mAllColumns,
                DBHelper.COLUMN_EVENT_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Event event = cursorToEvent(cursor);
        return event;
    }

    protected Event cursorToEvent(Cursor cursor) {
        Event event = new Event();
        event.setId(cursor.getLong(0));
        event.setName(cursor.getLong(1));
        event.setColor(cursor.getLong(2));
        event.setNote(cursor.getLong(3));
        event.setNotification(cursor.getLong(4));
        event.setPrice(cursor.getLong(5));
        return event;
    }
}