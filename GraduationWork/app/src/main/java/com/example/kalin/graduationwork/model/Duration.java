package com.example.kalin.graduationwork.model;

import android.database.Cursor;

import com.example.kalin.graduationwork.dao.DBHelper;

import java.io.Serializable;

/**
 * Created by Kalin on 30.1.2017 г..
 */

public class Duration implements Serializable {

    public static final String TAG = "Duration";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mStart;
    private String mFinish;
    private String mRepeat;
    private String mAllday;
    private Event mEvent;

    public Duration() {

    }

    public Duration(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_ID)));
        setStart(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_START)));
        setFinish(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_FINISH)));
        setRepeat(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_REPEAT)));
        setAllday(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_ALLDAY)));
    }

    public Duration(String start, String finish, String repeat, String allday, Event event) {
        this.mStart = start;
        this.mFinish = finish;
        this.mRepeat = repeat;
        this.mAllday = allday;
        this.mEvent = event;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getStart() {
        return mStart;
    }

    public void setStart(String mStart) {
        this.mStart = mStart;
    }

    public String getFinish() {
        return mFinish;
    }

    public void setFinish(String mFinish) {
        this.mFinish = mFinish;
    }

    public String getRepeat() {
        return mRepeat;
    }

    public void setRepeat(String mRepeat) {
        this.mRepeat = mRepeat;
    }

    public String getAllday() {
        return mAllday;
    }

    public void setAllday(String mAllday) {
        this.mAllday = mAllday;
    }

    public Event getEvent() {
        return mEvent;
    }

    public void setEvent(Event mEvent) {
        this.mEvent = mEvent;
    }

}