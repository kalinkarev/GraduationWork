package com.example.kalin.graduationwork;

import android.app.Application;

/**
 * Created by Kalin on 27.4.2017 г..
 */

public class BusyApplication extends Application {

    public static BusyApplication getInstance() {
        if (instance == null) {
            instance = new BusyApplication();
        }
        return instance;
    }

    private static BusyApplication instance;

    public BusyApplication() {
        super();
    }
}
