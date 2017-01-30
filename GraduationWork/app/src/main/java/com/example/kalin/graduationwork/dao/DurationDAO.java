package com.example.kalin.graduationwork.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kalin.graduationwork.model.Duration;
import com.example.kalin.graduationwork.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalin on 29.1.2017 г..
 */

public class DurationDAO {

    public static final String TAG = "DurationDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_DURATION_ID,
            DBHelper.COLUMN_DURATION_START,
            DBHelper.COLUMN_DURATION_FINISH,
            DBHelper.COLUMN_DURATION_REPEAT,
            DBHelper.COLUMN_DURATION_ALLDAY,
            DBHelper.COLUMN_DURATION_EVENT_ID };

    public DurationDAO(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }


    public Duration createDuration(long start, long finish, String repeat, String allday,long eventId) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DURATION_START, start);
        values.put(DBHelper.COLUMN_DURATION_FINISH, finish);
        values.put(DBHelper.COLUMN_DURATION_REPEAT, repeat);
        values.put(DBHelper.COLUMN_DURATION_ALLDAY, allday);
        values.put(DBHelper.COLUMN_DURATION_EVENT_ID, eventId);

        long insertId = mDatabase.insert(DBHelper.TABLE_DURATIONS, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_DURATIONS, mAllColumns,
                DBHelper.COLUMN_DURATION_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Duration newDuration = cursorToDuration(cursor);
        cursor.close();
        return newDuration;
    }

    public void deleteDuration(Duration duration) {
        long id = duration.getId();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_DURATIONS, DBHelper.COLUMN_DURATION_ID + " = " + id, null);
    }

    public List<Duration> getAllDurations() {
        List<Duration> listDurations = new ArrayList<Duration>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_DURATIONS, mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Duration duration = cursorToDuration(cursor);
            listDurations.add(duration);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listDurations;
    }

    public List<Duration> getDurationsOfEvent(long eventId) {
        List<Duration> listDurations = new ArrayList<Duration>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_DURATIONS, mAllColumns, DBHelper.COLUMN_DURATION_ID + " = ?",
                new String[] { String.valueOf(eventId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Duration duration = cursorToDuration(cursor);
            listDurations.add(duration);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listDurations;
    }

    private Duration cursorToDuration(Cursor cursor) {
        Duration duration = new Duration ();
        duration.setId(cursor.getLong(0));
        duration.setStart(cursor.getLong(1));
        duration.setFinish(cursor.getLong(2));
        duration.setRepeat(cursor.getString(3));
        duration.setAllday(cursor.getString(4));

        // get The event by id
//        long eventId = cursor.getLong(5);
        EventDAO dao = new EventDAO(mContext);
        Event event = dao.getEventById(eventId);
        if (event != null)
            duration.setEvent(event);

        return duration;
    }
}