package com.gnayuil.acost.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.gnayuil.acost.App;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SPUtils {

    private static Map<String, SPUtils> sSPMap = new HashMap<>();
    private SharedPreferences sp;

    public static SPUtils getInstance() {
        return getInstance("config");
    }

    public static SPUtils getInstance(String spName) {
        SPUtils sp = sSPMap.get(spName);
        if (sp == null) {
            sp = new SPUtils(spName);
            sSPMap.put(spName, sp);
        }
        return sp;
    }

    private SPUtils(String spName) {
        sp = App.getApp().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public void put(@NonNull String key, @NonNull String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getString(@NonNull String key) {
        return getString(key, "");
    }

    public String getString(@NonNull String key, @NonNull String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void put(@NonNull String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(@NonNull String key) {
        return getInt(key, -1);
    }

    public int getInt(@NonNull String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void put(@NonNull String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public long getLong(@NonNull String key) {
        return getLong(key, -1L);
    }

    public long getLong(@NonNull String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void put(@NonNull String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public float getFloat(@NonNull String key) {
        return getFloat(key, -1f);
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void put(@NonNull String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(@NonNull String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void put(@NonNull String key, @NonNull Set<String> values) {
        sp.edit().putStringSet(key, values).apply();
    }

    public Set<String> getStringSet(@NonNull String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    public Set<String> getStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public void remove(@NonNull String key) {
        sp.edit().remove(key).apply();
    }

    public boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    public void clear() {
        sp.edit().clear().apply();
    }

}
