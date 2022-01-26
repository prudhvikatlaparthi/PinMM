package com.pru.pinmm;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.pru.pinmm.preferences.MyPreferences;

public class MyApplication extends Application {

    public static MyPreferences myPreferences;
    private static MyApplication application;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }

    public static MyPreferences getMyPreferences() {
        synchronized (MyApplication.class) {
            if (sharedPreferences == null) {
                sharedPreferences = getContext().getSharedPreferences(BuildConfig.APPLICATION_ID + "_preferences", Context.MODE_PRIVATE);
                myPreferences = new MyPreferences(sharedPreferences);
            }
            return myPreferences;
        }
    }
}
