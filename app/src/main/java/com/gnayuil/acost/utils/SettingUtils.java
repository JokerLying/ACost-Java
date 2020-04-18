package com.gnayuil.acost.utils;

import androidx.appcompat.app.AppCompatDelegate;

public class SettingUtils {

    public static String DRAWER_STATUS = "showDrawer";
    private static String DARK_MODE = "darkMode";
    private static String LANGUAGE = "language";

    public static void changeDarkMode(boolean checked) {
        SPUtils.getInstance().put(DARK_MODE, checked);
        AppCompatDelegate.setDefaultNightMode(checked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static boolean getDarkMode() {
        return SPUtils.getInstance().getBoolean(DARK_MODE, false);
    }

    public static void changeLanguage(int language) {
        SPUtils.getInstance().put(LANGUAGE, getLanguageIntToString(language));
    }

    public static int getLanguage() {
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
}
