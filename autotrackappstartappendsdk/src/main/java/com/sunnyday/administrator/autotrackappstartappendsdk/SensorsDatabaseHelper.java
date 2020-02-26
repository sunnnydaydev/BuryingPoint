package com.sunnyday.administrator.autotrackappstartappendsdk;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

/**
 * Create by SunnyDay on 2020/02/26
 */
public class SensorsDatabaseHelper {
    private static final String SensorsDataContentProvider = ".SensorsDataContentProvider/";
    private ContentResolver mContentResolver;
    private Uri mAppStart;
    private Uri mAppEndState;
    private Uri mAppPausedTime;

    public static final String APP_STARTED = "$app_started";
    public static final String APP_END_STATE = "$app_end_state";
    public static final String APP_PAUSED_TIME = "$app_paused_time";

    public SensorsDatabaseHelper(Context context, String packageName) {
        mContentResolver = context.getContentResolver();
    }
}
