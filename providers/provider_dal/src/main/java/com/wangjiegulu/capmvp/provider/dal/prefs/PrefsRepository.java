package com.wangjiegulu.capmvp.provider.dal.prefs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

//import com.wangjiegulu.capmvp.provider.bll.application.ProviderApplication;

import com.wangjiegulu.capmvp.provider.dal.application.DalApplication;

import java.util.Map;
import java.util.Set;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 10/31/16.
 */
public class PrefsRepository {
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public PrefsRepository(String name, int mode) {
        prefs = DalApplication.getInstance().getApplication().getSharedPreferences(name, mode);
        editor = prefs.edit();
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.prefs.getBoolean(key, defaultVal);
    }

    public Boolean getBoolean(String key) {
        return this.prefs.getBoolean(key, false);
    }


    public String getString(String key, String defaultVal) {
        return this.prefs.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.prefs.getString(key, null);
    }

    public int getInt(String key, int defaultVal) {
        return this.prefs.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }


    public float getFloat(String key, float defaultVal) {
        return this.prefs.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.prefs.getFloat(key, 0F);
    }

    public long getLong(String key, long defaultVal) {
        return this.prefs.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.prefs.getLong(key, 0L);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultVal) {
        return this.prefs.getStringSet(key, defaultVal);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key) {
        return this.prefs.getStringSet(key, null);
    }

    public Map<String, ?> getAll() {
        return this.prefs.getAll();
    }


    public PrefsRepository putString(String key, String value) {
        editor.putString(key, value);
//        editor.commit();
        return this;
    }

    public PrefsRepository putInt(String key, int value) {
        editor.putInt(key, value);
//        editor.commit();
        return this;
    }

    public PrefsRepository putFloat(String key, float value) {
        editor.putFloat(key, value);
//        editor.commit();
        return this;
    }

    public PrefsRepository putLong(String key, long value) {
        editor.putLong(key, value);
//        editor.commit();
        return this;
    }

    public PrefsRepository putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
//        editor.commit();
        return this;
    }

    public void commit() {
        editor.commit();
    }

    public void apply() {
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public PrefsRepository putStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value);
        editor.commit();
        return this;
    }

    public PrefsRepository clear() {
        editor.clear();
        return this;
    }


}
