package com.gnayuil.acost.ui.calculator;

import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.gnayuil.acost.R;
import com.gnayuil.acost.data.style.ButtonStyle;
import com.gnayuil.acost.databinding.FragmentCalculatorBinding;
import com.gnayuil.acost.ui.base.BaseFragment;
import com.gnayuil.acost.utils.DisplayUtils;

import static android.view.HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING;

public class CalculatorFragment extends BaseFragment {

    private FragmentCalculatorBinding mBinding;
    private CalculatorViewModel mViewModel;

    public static CalculatorFragment newInstance() {
        return new CalculatorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false);
        mBinding.setBs(getButtonStyle());
        mBinding.setClick(new ClickProxy());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getFragmentViewModelProvider(this).get(CalculatorViewModel.class);

        mBinding.btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mSharedViewModel.getInfoList().getValue() != null) {
                    mSharedViewModel.cleanData();
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, FLAG_IGNORE_GLOBAL_SETTING);
                }
                return true;
            }
        });
    }

    private ButtonStyle getButtonStyle() {
        ButtonStyle bs = new ButtonStyle();
        bs.setSpacing(DisplayUtils.dp2px(5));
        return bs;
    }

    public class ClickProxy {

        public void addNumeral(int num) {
            mSharedViewModel.clickOne(String.valueOf(num));
        }

        public void addDot() {
            mSharedViewModel.clickOne(".");
        }

        public void addBack() {
            mSharedViewModel.clickOne("DEL");
        }

        public void addPlus() {
            mSharedViewModel.clickOne("+");
        }
    }
}
