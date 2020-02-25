package com.sunnyday.administrator.autotrackappviewscreensdk;

import android.app.Application;
import android.util.Log;
import org.json.JSONObject;
import java.util.Map;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Create by SunnyDay on 2020/02/24
 */
public class SensorsDataAPI {
    private final String TAG = this.getClass().getSimpleName();
    private static SensorsDataAPI INSTANCE;
    private String mDeviceId;
    private Map<String, Object> mDeviceInfo;
    public static final String SDK_VERSION = "1.0.0";
    private static final Object mLock = new Object();

    private SensorsDataAPI(Application application) {
        mDeviceId = SensorsDataPrivate.getAndroidId(application);
        mDeviceInfo = SensorsDataPrivate.getDeviceInfo(application);
        SensorsDataPrivate.registerActivityLifecycleCallbacks(application);
    }

    @Keep
    public static SensorsDataAPI getInstance() {
        return INSTANCE;
    }

    @Keep
    @SuppressWarnings("UnusedReturnValue")
    public static SensorsDataAPI init(Application application) {
        synchronized (mLock) {
            if (null == INSTANCE) {
                INSTANCE = new SensorsDataAPI(application);
            }
            return INSTANCE;
        }
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
            jsonObject.put("event", eventName);// 事件名
            jsonObject.put("device_id", mDeviceId);// android id
            JSONObject sendProperties = new JSONObject(mDeviceInfo);// 设备信息

            if (properties != null) {
                // 合并数据
                SensorsDataPrivate.mergeJSONObject(properties, sendProperties);
            }

            jsonObject.put("properties", sendProperties);
            jsonObject.put("time", System.currentTimeMillis());
            // 打印采集的数据
            Log.i(TAG, SensorsDataPrivate.formatJson(jsonObject.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
