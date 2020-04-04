package com.gnayuil.acost.ui.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gnayuil.acost.R;
import com.gnayuil.acost.ui.base.BaseFragment;

public class InformationFragment extends BaseFragment {

    private InformationViewModel mViewModel;

    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = getFragmentViewModelProvider(this).get(InformationViewModel.class);
    }
}
