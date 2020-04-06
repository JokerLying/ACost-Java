package com.gnayuil.acost.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> console = new MutableLiveData<>();

    public MainViewModel() {
        console.setValue("0");
    }

    public MutableLiveData<String> getConsole() {
        return console;
    }

    public void addNumeral(String num) {
        console.setValue(console.getValue() + num);
    }

}
