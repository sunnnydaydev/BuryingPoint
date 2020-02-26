package com.sunnyday.administrator.autotrackappstartappendsdk;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.Map;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Create by SunnyDay on 2020/02/25
 */
public class SensorsDataAPI {
    private volatile static SensorsDataAPI INSTANCE;
    private final String TAG = this.getClass().getSimpleName();//
    private String mDeviceId;
    private Map<String, Object> mDeviceInfo;
    public static final String SDK_VERSION = "1.0.0";
    private static final Object mLock = new Object();

    private SensorsDataAPI(Application application) {
        mDeviceId = SensorsDataPrivate.getAndroidID(application.getApplicationContext());
        mDeviceInfo = SensorsDataPrivate.getDeviceInfo(application.getApplicationContext());
        SensorsDataPrivate.registerActivityLifecycleCallbacks(application);
    }

    public static SensorsDataAPI getInstance() {
        return INSTANCE;
    }

    /**
     * double check 方式单利进行初始化。
     */
    public SensorsDataAPI init(Application application) {
        if (null == INSTANCE) {
            synchronized (mLock) {
                if (null == INSTANCE) {
                    INSTANCE = new SensorsDataAPI(application);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 指定不采集哪个 Activity 的页面浏览事件
     *
     * @param activity Activity
     */
    public void ignoreAutoTrackActivity(Class<?> activity) {
        SensorsDataPrivate.ignoreAutoTrackActivity(activity);
    }

    /**
     * 恢复采集某个 Activity 的页面浏览事件
     *
     * @param activity Activity
     */
    public void removeIgnoredActivity(Class<?> activity) {
        SensorsDataPrivate.removeIgnoredActivity(activity);
    }

    /**
     * track 事件
     *
     * @param eventName  String 事件名称
     * @param properties JSONObject 事件自定义属性
     */
    public void track(@NonNull String eventName, @Nullable JSONObject properties) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("event", eventName);
            jsonObject.put("device_id", mDeviceId);

            JSONObject sendProperties = new JSONObject(mDeviceInfo);

            if (properties != null) {
                SensorsDataPrivate.mergeJSONObject(properties, sendProperties);
            }

            jsonObject.put("properties", sendProperties);
            jsonObject.put("time", System.currentTimeMillis());

            Log.i(TAG, SensorsDataPrivate.formatJson(jsonObject.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
