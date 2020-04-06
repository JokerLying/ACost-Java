package com.gnayuil.acost.ui.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gnayuil.acost.App;

public class BaseFragment extends Fragment {
    protected AppCompatActivity mActivity;
    protected SharedViewModel mSharedViewModel;
    private ViewModelProvider fragmentProvider;
    private ViewModelProvider activityProvider;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);
    }

    public ViewModelProvider getFragmentViewModelProvider(Fragment fragment) {
        if (fragmentProvider == null) {
            fragmentProvider = new ViewModelProvider(fragment);
        }
        return fragmentProvider;
    }

    public ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        if (activityProvider == null) {
            activityProvider = new ViewModelProvider(activity);
        }
        return activityProvider;
    }

    public ViewModelProvider getAppViewModelProvider() {
        return ((App) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }
}
