package com.gnayuil.acost;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class App extends Application implements ViewModelStoreOwner {

    private static Application app;
    private ViewModelProvider.Factory factory;
    private ViewModelStore viewModelStore;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        viewModelStore = new ViewModelStore();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return viewModelStore;
    }

    public static Application getApp() {
        return app;
    }

    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider(this, getAppFactory());
    }

    private ViewModelProvider.Factory getAppFactory() {
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
        }
        return factory;
    }
}
