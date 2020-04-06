package com.gnayuil.acost.ui.main;

import android.text.TextUtils;

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

    public void addChar(String str) {
        switch (str) {
            case "+":
                plus();
                break;
            case ".":
                dot();
                break;
            case "DEL":
                del();
                break;
            default:
                numeral(str);
                break;
        }
    }

    private void plus() {

    }

    private void dot() {
        if (console.getValue() == null) {
            return;
        }
        if (!console.getValue().contains(".")) {
            console.setValue(console.getValue() + ".");
        }
    }

    private void del() {
        if (console.getValue() == null) {
            return;
        }
        if (console.getValue().length() > 0) {
            console.setValue(console.getValue().substring(0, console.getValue().length() - 1));
        }
        if (TextUtils.isEmpty(console.getValue())) {
            console.setValue("0");
        }
    }

    private void numeral(String num) {
        if ("0".equals(console.getValue())) {
            console.setValue(num);
        } else {
            console.setValue(console.getValue() + num);
        }
    }

}
