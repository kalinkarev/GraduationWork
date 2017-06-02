package com.example.kalin.graduationwork.model;

import java.io.Serializable;

public class ColorData implements Serializable{

    private String mName;
    private int mColor;

    public ColorData() {}

    public ColorData(String name, int color) {
        this.mName = name;
        this.mColor = color;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

}