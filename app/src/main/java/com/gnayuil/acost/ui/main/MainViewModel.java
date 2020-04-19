package com.gnayuil.acost.ui.main;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gnayuil.acost.App;
import com.gnayuil.acost.data.bean.InfoItem;
import com.gnayuil.acost.utils.SettingUtils;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> version = new MutableLiveData<>();
    public MutableLiveData<Boolean> darkMode = new MutableLiveData<>();

    public MutableLiveData<String> getVersion() {
        version.setValue(getVersionString());
        return version;
    }

    String getVersionString() {
        String versionName = "error";
        try {
            PackageManager packageManager = App.getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(App.getApp().getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    MutableLiveData<Boolean> getDarkMode() {
        darkMode.setValue(SettingUtils.getDarkMode());
        return darkMode;
    }

    String getShowConsole(List<InfoItem> infoItems) {
        for (InfoItem one : infoItems) {
            if (one.isCheck()) {
                String[] cost = one.getLambda().split("\\+");
                return cost[cost.length - 1];
            }
        }
        return "0";
    }

}
