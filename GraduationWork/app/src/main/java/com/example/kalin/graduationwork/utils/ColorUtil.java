package com.example.kalin.graduationwork.utils;

import android.content.Context;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.ColorData;

import java.util.ArrayList;
import java.util.List;

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
        ColorData black = new ColorData("Black", context.getResources().getColor(R.color.Black));
        ColorData darkBlue = new ColorData("DarkBlue", context.getResources().getColor(R.color.DarkBlue));
        ColorData blue = new ColorData("Blue", context.getResources().getColor(R.color.Blue));
        ColorData lightBlue = new ColorData("LightBlue", context.getResources().getColor(R.color.LightBlue));
        ColorData darkPink = new ColorData("DarkPink", context.getResources().getColor(R.color.DarkPink));
        ColorData pink = new ColorData("Pink", context.getResources().getColor(R.color.Pink));
        ColorData lightPink = new ColorData("LightPink", context.getResources().getColor(R.color.LightPink));
        ColorData darkRed = new ColorData("DarkRed", context.getResources().getColor(R.color.DarkRed));
        ColorData red = new ColorData("Red", context.getResources().getColor(R.color.Red));
        ColorData orange = new ColorData("Orange", context.getResources().getColor(R.color.Orange));
        ColorData lightOrange = new ColorData("LightOrange", context.getResources().getColor(R.color.LightOrange));
        ColorData green = new ColorData("Green", context.getResources().getColor(R.color.Green));
        ColorData darkGray = new ColorData("DarkGray", context.getResources().getColor(R.color.DarkGray));
        ColorData gray = new ColorData("Gray", context.getResources().getColor(R.color.Gray));
        ColorData lightGray = new ColorData("LightGray", context.getResources().getColor(R.color.LightGray));
        ColorData purple = new ColorData("Purple", context.getResources().getColor(R.color.Purple));
        ColorData lightPurple = new ColorData("LightPurple", context.getResources().getColor(R.color.LightPurple));

        colors.add(black);
        colors.add(darkBlue);
        colors.add(blue);
        colors.add(lightBlue);
        colors.add(darkPink);
        colors.add(pink);
        colors.add(lightPink);
        colors.add(darkRed);
        colors.add(red);
        colors.add(orange);
        colors.add(lightOrange);
        colors.add(green);
        colors.add(darkGray);
        colors.add(gray);
        colors.add(lightGray);
        colors.add(purple);
        colors.add(lightPurple);
    }

    public List<ColorData> getColors() {
        return colors;
    }
}