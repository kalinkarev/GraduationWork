package com.example.kalin.graduationwork.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kalin.graduationwork.model.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalin on 27.1.2017 г..
 */

public class LocationDAO {

    public static final String TAG = "LocationDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_LOCATION_ID,
            DBHelper.COLUMN_LOCATION_NAME,
            DBHelper.COLUMN_LOCATION_LONGITUTE,
            DBHelper.COLUMN_LOCATION_LATITUDE,
            DBHelper.COLUMN_LOCATION_EVENT_ID };

    public LocationDAO(Context context) {
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

    public Location createLocation(String name, long longitute, long latitude, long eventId) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_LOCATION_NAME, name);
        values.put(DBHelper.COLUMN_LOCATION_LONGITUTE, longitute);
        values.put(DBHelper.COLUMN_LOCATION_LATITUDE, latitude);
        values.put(DBHelper.COLUMN_LOCATION_EVENT_ID, eventId);

        long insertId = mDatabase.insert(DBHelper.TABLE_LOCATIONS, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_LOCATIONS, mAllColumns,
                DBHelper.COLUMN_LOCATION_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Location newLocation = cursorToLocation(cursor);
        cursor.close();
        return newLocation;
    }

    public void deleteLocation(Location location) {
        long id = location.getId();
        System.out.println("the deleted location has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_LOCATIONS, DBHelper.COLUMN_LOCATION_ID + " = " + id, null);
    }

    public List<Location> getAllLocations() {
        List<Location> listLocations = new ArrayList<Location>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_LOCATIONS, mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = cursorToLocation(cursor);
            listLocations.add(location);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listLocations;
    }

    public List<Location> getLocationsOfEvent(long eventId) {
        List<Location> listLocations = new ArrayList<Location>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_LOCATIONS, mAllColumns, DBHelper.COLUMN_LOCATION_ID + " = ?",
                new String[] { String.valueOf(eventId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = cursorToLocation(cursor);
            listLocations.add(location);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listLocations;
    }

    private Location cursorToLocation(Cursor cursor) {
        Location location = new Location();

        location.setId(cursor.getLong(0));
        location.setName(cursor.getString(1));
        location.setLongitute(cursor.getString(2));
        location.setLatitude(cursor.getLong(3));

        // get The event by id
        long eventId = cursor.getLong(4);
        EventDAO dao = new EventDAO(mContext);
        Event event = dao.getEventById(eventId);
        if (event != null)
            location.setEvent(event);

        return location;
    }

}