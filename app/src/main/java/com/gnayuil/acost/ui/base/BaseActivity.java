package com.gnayuil.acost.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gnayuil.acost.App;

public class BaseActivity extends AppCompatActivity {
    protected SharedViewModel mSharedViewModel;
    private ViewModelProvider activityProvider;
    private ViewModelProvider appProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);
    }

    public ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        if (activityProvider == null) {
            activityProvider = new ViewModelProvider(activity);
        }
        return activityProvider;
    }

    public ViewModelProvider getAppViewModelProvider() {
        return ((App) getApplicationContext()).getAppViewModelProvider(this);
    }
}
