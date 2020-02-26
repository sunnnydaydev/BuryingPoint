package com.sunnyday.administrator.autotrackappstartappendsdk;

/**
 * Create by SunnyDay on 2020/02/26
 */
public enum SensorsDataTable {
    APP_STARTED("app_started"),
    APP_PAUSED_TIME("app_paused_time"),
    APP_END_STATE("app_end_state");

    private String name;

    SensorsDataTable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
