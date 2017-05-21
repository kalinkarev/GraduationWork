package com.example.kalin.graduationwork.model;

import android.database.Cursor;

import com.example.kalin.graduationwork.dao.DBHelper;

import java.io.Serializable;

public class Duration implements Serializable {

    private long mId;
    private long mStart;
    private long mFinish;
//    private boolean mRepeat;
    private boolean mAllday;
    private Event mEvent;

    public Duration() {

    }

    public Duration(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_ID)));
        setStart(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_START)));
        setFinish(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_FINISH)));
//        setRepeat(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_REPEAT)) == 1);
        setAllday(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_DURATION_ALLDAY)) == 1);
    }

    public Duration(long start, long finish, boolean repeat, boolean allday, Event event) {
        this.mStart = start;
        this.mFinish = finish;
//        this.mRepeat = repeat;
        this.mAllday = allday;
        this.mEvent = event;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public long getStart() {
        return mStart;
    }

    public void setStart(long mStart) {
        this.mStart = mStart;
    }

    public long getFinish() {
        return mFinish;
    }

    public void setFinish(long mFinish) {
        this.mFinish = mFinish;
    }

//    public boolean getRepeat() {
//        return mRepeat;
//    }
//
//    public void setRepeat(boolean mRepeat) {
//        this.mRepeat = mRepeat;
//    }

    public boolean getAllday() {
        return mAllday;
    }

    public void setAllday(boolean mAllday) {
        this.mAllday = mAllday;
    }

    public Event getEvent() {
        return mEvent;
    }

    public void setEvent(Event mEvent) {
        this.mEvent = mEvent;
    }

}