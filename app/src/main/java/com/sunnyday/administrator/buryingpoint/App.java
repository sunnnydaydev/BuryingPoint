package com.sunnyday.administrator.buryingpoint;

import android.app.Application;


import com.sunnyday.administrator.autotrackappviewscreensdk.SensorsDataAPI;

/**
 * Create by SunnyDay on 2020/02/24
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initAutoTrackViewScreenSdk(this);
    }


    private void initAutoTrackViewScreenSdk(Application application) {
        SensorsDataAPI.init(application);
    }
}
