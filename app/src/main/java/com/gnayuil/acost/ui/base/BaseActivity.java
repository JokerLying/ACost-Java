package com.gnayuil.acost.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class BaseActivity extends AppCompatActivity {
    private ViewModelProvider activityProvider;

    public ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        if (activityProvider == null) {
            new ViewModelProvider(activity);
        }
        return activityProvider;
    }
}
