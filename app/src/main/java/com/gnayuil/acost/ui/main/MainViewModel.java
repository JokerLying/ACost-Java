package com.gnayuil.acost.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gnayuil.acost.data.bean.InfoItem;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> console = new MutableLiveData<>();

    public MainViewModel() {
        console.setValue("0");
    }

    public MutableLiveData<String> getConsole() {
        return console;
    }

    public void setInfoList(List<InfoItem> infoItems) {
        for (InfoItem one : infoItems) {
            if (one.isCheck()) {
                String[] cost = one.getLambda().split("\\+");
                console.setValue(cost[cost.length - 1]);
            }
        }
    }
}
