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
        ColorData darkBlue = new ColorData();
        colors.add(darkBlue);
        ColorData blue = new ColorData();
        colors.add(blue);
        ColorData lightBlue = new ColorData();
        colors.add(lightBlue);

        ColorData darkPink = new ColorData();
        colors.add(darkPink);
        ColorData pink = new ColorData();
        colors.add(pink);
        ColorData lightPink = new ColorData();
        colors.add(lightPink);

        ColorData red = new ColorData();
        colors.add(red);
        ColorData lightRed = new ColorData();
        colors.add(lightRed);

        ColorData darkOrange = new ColorData();
        colors.add(darkOrange);
        ColorData orange = new ColorData();
        colors.add(orange);

        ColorData green = new ColorData();
        colors.add(green);

        ColorData gray = new ColorData();
        colors.add(gray);
        ColorData lightGray = new ColorData();
        colors.add(lightGray);

        ColorData purple = new ColorData();
        colors.add(purple);
        ColorData lightPurple = new ColorData();
        colors.add(lightPurple);

        ColorData white = new ColorData();
        colors.add(white);

    }

    public List<ColorData> getColors() {
        return colors;
    }
}
