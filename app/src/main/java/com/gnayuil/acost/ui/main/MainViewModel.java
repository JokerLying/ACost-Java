package com.gnayuil.acost.ui.main;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gnayuil.acost.data.bean.InfoItem;
import com.gnayuil.acost.utils.SPUtils;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> console = new MutableLiveData<>();

    private static String DARK_MODE = "darkMode";
    private static String LANGUAGE = "language";

    public MainViewModel() {
        console.setValue("0");
    }

    public MutableLiveData<String> getConsole() {
        return console;
    }

    void setInfoList(List<InfoItem> infoItems) {
        for (InfoItem one : infoItems) {
            if (one.isCheck()) {
                String[] cost = one.getLambda().split("\\+");
                console.setValue(cost[cost.length - 1]);
            }
        }
    }

    void changeDarkMode(boolean checked) {
        SPUtils.getInstance().put(DARK_MODE, checked);
        AppCompatDelegate.setDefaultNightMode(checked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    boolean getDarkMode() {
        return SPUtils.getInstance().getBoolean(DARK_MODE, false);
    }

    void changeLanguage(String language) {
        SPUtils.getInstance().put(LANGUAGE, language);
    }

    String getLanguage() {
        return SPUtils.getInstance().getString(LANGUAGE);
    }
}
