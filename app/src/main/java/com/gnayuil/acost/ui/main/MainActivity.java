package com.gnayuil.acost.ui.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.gnayuil.acost.R;
import com.gnayuil.acost.data.ConsoleStyle;
import com.gnayuil.acost.databinding.ActivityMainBinding;
import com.gnayuil.acost.ui.base.BaseActivity;
import com.gnayuil.acost.ui.calculator.CalculatorFragment;
import com.gnayuil.acost.ui.information.InformationFragment;
import com.gnayuil.acost.utils.DisplayUtils;

public class MainActivity extends BaseActivity {

    MainViewModel mViewModel;
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getActivityViewModelProvider(this).get(MainViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        mBinding.setCs(getConsoleStyle());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_calculator, CalculatorFragment.newInstance())
                    .replace(R.id.frame_information, InformationFragment.newInstance())
                    .commitNow();
        }

        mSharedViewModel.clickOne.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mViewModel.addChar(s);
            }
        });

        mViewModel.getConsole().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBinding.tvConsole.setText(s);
            }
        });
    }

    private ConsoleStyle getConsoleStyle() {
        ConsoleStyle cs = new ConsoleStyle();
        cs.setRadius(DisplayUtils.dp2px(4));
        cs.setSolidColor(0x88ffffff);
        cs.setStrokeColor(0x33666666);
        cs.setStrokeWidth(1);
        cs.setSpacing(DisplayUtils.dp2px(5));
        return cs;
    }
}
