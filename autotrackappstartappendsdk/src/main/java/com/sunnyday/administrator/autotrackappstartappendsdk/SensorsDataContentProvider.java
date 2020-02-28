package com.sunnyday.administrator.autotrackappstartappendsdk;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Create by SunnyDay on 2020/02/28
 */
public class SensorsDataContentProvider extends ContentProvider {
    private final static int APP_START = 1;
    private final static int APP_END_STATE = 2;
    private final static int APP_PAUSED_TIME = 3;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor mEditor;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private ContentResolver mContentResolver;

    @Override
    public boolean onCreate() {
        if (getContext() != null) {
            String packageName = getContext().getPackageName();
            // uri与常量码绑定
            uriMatcher.addURI(packageName + "SensorsDataContentProvider", SensorsDataTable.APP_STARTED.getName(), APP_START);
            uriMatcher.addURI(packageName + ".SensorsDataContentProvider", SensorsDataTable.APP_END_STATE.getName(), APP_END_STATE);
            uriMatcher.addURI(packageName + ".SensorsDataContentProvider", SensorsDataTable.APP_PAUSED_TIME.getName(), APP_PAUSED_TIME);
            sharedPreferences = getContext().getSharedPreferences("com.sunnyday.administrator.autotrackappstartappendsdk.SensorsDataAPI", Context.MODE_PRIVATE);
            mEditor = sharedPreferences.edit();
            mEditor.apply();
            mContentResolver = getContext().getContentResolver();
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
