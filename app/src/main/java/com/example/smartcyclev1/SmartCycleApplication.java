package com.example.smartcyclev1;

import android.app.Application;

public class SmartCycleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageRecogDatabase.init(this);
        RecyclingDatabase.init(this);
    }
}
