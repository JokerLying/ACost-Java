package com.gnayuil.acost.ui.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.gnayuil.acost.R;
import com.gnayuil.acost.data.bean.InfoItem;
import com.gnayuil.acost.databinding.FragmentInformationBinding;
import com.gnayuil.acost.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class InformationFragment extends BaseFragment {

    private FragmentInformationBinding mBinding;
    private InformationViewModel mViewModel;

    private InfoAdapter adapter;

    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getFragmentViewModelProvider(this).get(InformationViewModel.class);

        adapter = new InfoAdapter(getActivity());
        List<InfoItem> infoList = new ArrayList<>();
        InfoItem realCost = new InfoItem();
        realCost.setCheck(true);
        infoList.add(realCost);
        InfoItem originalCost = new InfoItem();
        infoList.add(originalCost);
        InfoItem packetOne = new InfoItem();
        infoList.add(packetOne);
        adapter.setList(infoList);
        mBinding.rvInfo.setAdapter(adapter);

        mSharedViewModel.clickOne.observe(mActivity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                adapter.setLambda(s);
            }
        });
    }

}
