package com.nikhil.smartcyclev1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SmartCycleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageRecogDatabase.init(this);
        RecyclingDatabase.init(this);
        SharedPreferences  sharedPref = getBaseContext().getSharedPreferences("SharedFile", Context.MODE_PRIVATE);

    }
}
