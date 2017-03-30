package com.example.kalin.graduationwork.utils;

import android.content.Context;

import com.example.kalin.graduationwork.model.ColorData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalin on 30.3.2017 г..
 */

public class ColorUtil {

    static ColorUtil instance;

    public static ColorUtil getInstance(Context context) {
        if (instance == null) {
            instance = new ColorUtil(context);
        }
        return instance;
    }

    List<ColorData> colors;
    private Context context;

    private ColorUtil(Context context) {
        this.colors = new ArrayList<>();
        this.context = context;
    }

    public void init() {
        ColorData blue = new ColorData();
        colors.add(blue);
    }

    public List<ColorData> getColors() {
        return colors;
    }
}
