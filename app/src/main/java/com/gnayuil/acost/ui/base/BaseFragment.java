package com.gnayuil.acost.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class BaseFragment extends Fragment {
    private ViewModelProvider fragmentProvider;
    private ViewModelProvider activityProvider;

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
}
