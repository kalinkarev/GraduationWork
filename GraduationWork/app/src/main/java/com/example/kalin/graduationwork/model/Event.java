package com.example.kalin.graduationwork.model;

import android.database.Cursor;

import com.example.kalin.graduationwork.BusyApplication;
import com.example.kalin.graduationwork.dao.DBHelper;
import com.example.kalin.graduationwork.utils.ColorUtil;

import java.io.Serializable;

public class Event implements Serializable {

    private long mId;
    private String mName;
    private ColorData mColor;
    private String mNote;
    private boolean mNotication;
    private int mPrice;
    private Duration mDuration;
    private Location mLocation;

    public Event() {}

    public Event(String name, ColorData color, String note, boolean notification, int price,
                 Duration duration, Location location) {
        this.mName = name;
        this.mColor = color;
        this.mNote = note;
        this.mNotication = notification;
        this.mPrice = price;
        this.mDuration = duration;
        this.mLocation = location;
    }

    public Event(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_ID)));
        setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_NAME)));
        setColor(ColorUtil.getInstance(BusyApplication.getInstance()).findColor(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_COLOR))));
//        setColor(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_COLOR)));
        setNote(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_NOTE)));
        setNotification(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_NOTIFICATION)) == 1);
        setPrice(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_PRICE)));
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

    public ColorData getColor() {
        return mColor;
    }

    public void setColor(ColorData mColor) {
        this.mColor = mColor;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String mNote) {
        this.mNote = mNote;
    }

    public boolean getNotification() {
        return mNotication;
    }

    public void setNotification(boolean mNotiication) {
        this.mNotication = mNotiication;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration mDuration) {
        this.mDuration = mDuration;
    }
}