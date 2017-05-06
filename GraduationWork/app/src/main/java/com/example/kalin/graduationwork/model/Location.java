package com.example.kalin.graduationwork.model;

import android.database.Cursor;

import com.example.kalin.graduationwork.dao.DBHelper;

import java.io.Serializable;

public class Location implements Serializable {

    private long mId;
    private String mName;
    private String mLongitute;
    private String mLatitude;
    private Event mEvent;

    public Location() {

    }

    public Location(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_LOCATION_ID)));
        setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOCATION_NAME)));
        setLongitute(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOCATION_LONGITUTE)));
        setLatitude(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOCATION_LATITUDE)));
    }

    public Location(String name, String longitute, String latitude, Event event) {
        this.mName = name;
        this.mLongitute = longitute;
        this.mLatitude = latitude;
        this.mEvent = event;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getLongitute() {
        return mLongitute;
    }

    public void setLongitute(String mLongitute) {
        this.mLongitute = mLongitute;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Event getEvent() {
        return mEvent;
    }

    public void setEvent(Event mEvent) {
        this.mEvent = mEvent;
    }

}