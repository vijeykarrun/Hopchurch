package com.hopchurch.Application;

import android.app.Application;
import android.content.Context;

public class MyApplicaionClass extends Application {
    private static Context appcontext;

    public void onCreate() {
        super.onCreate();
        appcontext = this;
    }

    public static Context getContext() {
        return appcontext;
    }
}
