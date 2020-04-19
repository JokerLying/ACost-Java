package com.gnayuil.acost.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import androidx.appcompat.app.AppCompatDelegate;

import com.gnayuil.acost.App;

import java.util.Locale;

public class SettingUtils {

    public static String DRAWER_STATUS = "showDrawer";
    private static String DARK_MODE = "darkMode";
    private static String LANGUAGE = "language";

    public static void setDarkMode(boolean darkMode) {
        SPUtils.getInstance().put(DARK_MODE, darkMode);
        changeDarkMode(darkMode);
    }

    private static void changeDarkMode(boolean darkMode) {
        AppCompatDelegate.setDefaultNightMode(darkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static boolean getDarkMode() {
        return SPUtils.getInstance().getBoolean(DARK_MODE, false);
    }

    public static void setLanguage(int language) {
        if (language != getLanguageInt()) {
            SPUtils.getInstance().put(LANGUAGE, getLanguageIntToString(language));
        }
    }

    public static Resources getLocalLanguageResources(Context context) {
        return getLocalLanguageContext(context).getResources();
    }


    public static Context getLocalLanguageContext(Context context) {
        Locale locale = getLanguageLocal();
        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();
        Context localizedContext;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            localizedContext = context.createConfigurationContext(configuration);
        } else {
            configuration.setLocale(locale);
            localizedContext = context.createConfigurationContext(configuration);
        }
        return localizedContext;
    }

    public static int getLanguageInt() {
        return getLanguageStringToInt(SPUtils.getInstance().getString(LANGUAGE));
    }

    private static String getLanguageIntToString(int position) {
        switch (position) {
            case 1:
                return "en";
            case 2:
                return "zh";
            default:
                return "auto";
        }
    }

    private static int getLanguageStringToInt(String language) {
        switch (language) {
            case "en":
                return 1;
            case "zh":
                return 2;
            default:
                return 0;
        }
    }

    private static Locale getLanguageLocal(String language) {
        switch (language) {
            case "en":
                return Locale.ENGLISH;
            case "zh":
                return Locale.CHINA;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    return App.getApp().getResources().getConfiguration().getLocales().get(0);
                } else {
                    return Locale.getDefault();
                }
        }
    }

    public static Locale getLanguageLocal() {
        return getLanguageLocal(getLanguageIntToString(getLanguageInt()));
    }
}
