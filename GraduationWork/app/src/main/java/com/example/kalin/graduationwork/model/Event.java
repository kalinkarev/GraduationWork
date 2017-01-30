package com.example.kalin.graduationwork.model;

import java.io.Serializable;

/**
 * Created by Kalin on 30.1.2017 г..
 */

public class Event implements Serializable {

    public static final String TAG = "";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mName;
    private String mColor;
    private String mNote;
    private String mNotiication;
    private String mPrice;

    public Event() {

    }

    public Event(String name, String color, String note, String notification, String price) {
        this.mName = name;
        this.mColor = color;
        this.mNote = note;
        this.mNotiication = notification;
        this.mPrice = price;
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

    public String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String mNote) {
        this.mNote = mNote;
    }

    public String getNotification() {
        return mNotiication;
    }

    public void setNotification(String mNotiication) {
        this.mNotiication = mNotiication;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

}