package com.astuetz.viewpager.extensions.sample;

import android.app.Application;

import com.astuetz.utils.SizeUtils;
import com.astuetz.utils.Utils;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SizeUtils.init(this);
        Utils.init(this);

    }
}
