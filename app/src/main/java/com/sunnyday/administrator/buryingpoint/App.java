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
        initAutoAppStartAppEndSdk(this);
    }


    private void initAutoTrackViewScreenSdk(Application application) {
        SensorsDataAPI.init(application);
    }
    private void initAutoAppStartAppEndSdk(Application application) {
        com.sunnyday.administrator.autotrackappstartappendsdk.SensorsDataAPI.init(application);
    }
}
