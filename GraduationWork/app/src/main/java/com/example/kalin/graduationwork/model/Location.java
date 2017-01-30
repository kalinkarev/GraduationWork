package com.example.kalin.graduationwork.model;

import java.io.Serializable;

/**
 * Created by Kalin on 30.1.2017 г..
 */

public class Location implements Serializable {

    public static final String TAG = "Location";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mName;
    private String mLongitute;
    private String mLatitude;
    private Event mEvent;

    public Location() {

    }

    public Location(String name, String longitute, String latitude) {
        this.mName = name;
        this.mLongitute = longitute;
        this.mLatitude = latitude;
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