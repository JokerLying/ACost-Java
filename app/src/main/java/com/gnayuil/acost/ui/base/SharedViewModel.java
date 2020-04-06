package com.gnayuil.acost.ui.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    public MutableLiveData<String> clickOne = new MutableLiveData<>();
}
