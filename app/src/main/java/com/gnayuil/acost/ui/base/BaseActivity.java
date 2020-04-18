package com.gnayuil.acost.ui.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gnayuil.acost.App;
import com.gnayuil.acost.utils.SettingUtils;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    protected SharedViewModel mSharedViewModel;
    private ViewModelProvider activityProvider;
    private ViewModelProvider appProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);
    }

    @Override
    protected void attachBaseContext(Context context) {
        Locale locale = SettingUtils.getLanguageLocal();
        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            context = context.createConfigurationContext(configuration);
        } else {
            configuration.setLocale(locale);
            context = context.createConfigurationContext(configuration);
        }
        super.attachBaseContext(context);
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (overrideConfiguration != null) {
            int uiMode = overrideConfiguration.uiMode;
            overrideConfiguration.setTo(getBaseContext().getResources().getConfiguration());
            overrideConfiguration.uiMode = uiMode;
        }
        super.applyOverrideConfiguration(overrideConfiguration);
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
