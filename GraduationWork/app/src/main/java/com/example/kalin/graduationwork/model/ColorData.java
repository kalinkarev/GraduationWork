package com.example.kalin.graduationwork.model;

/**
 * Created by Kalin on 30.3.2017 г..
 */

public class ColorData {

    private String mName;
//    private int mColor;
    private Event mEvent;

    public ColorData() {

    }

    public ColorData(String name, int color, Event event) {
        this.mName = name;
//        this.mColor = color;
        this.mEvent = event;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

//    public int getColor() {
//        return mColor;
//    }
//
//    public void setColor(int mColor) {
//        this.mColor = mColor;
//    }

    public Event getEvent() {
        return mEvent;
    }

    public void setEvent(Event mEvent) {
        this.mEvent = mEvent;
    }
}