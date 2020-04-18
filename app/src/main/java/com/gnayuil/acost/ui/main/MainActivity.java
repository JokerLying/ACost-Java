package com.gnayuil.acost.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.gnayuil.acost.R;
import com.gnayuil.acost.data.bean.InfoItem;
import com.gnayuil.acost.data.style.ConsoleStyle;
import com.gnayuil.acost.databinding.ActivityMainBinding;
import com.gnayuil.acost.ui.base.BaseActivity;
import com.gnayuil.acost.ui.calculator.CalculatorFragment;
import com.gnayuil.acost.ui.information.InformationFragment;
import com.gnayuil.acost.utils.DisplayUtils;
import com.gnayuil.acost.utils.SettingUtils;

import java.util.List;

public class MainActivity extends BaseActivity {

    MainViewModel mViewModel;
    ActivityMainBinding mBinding;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBinding.layoutDrawer != null) {
            outState.putBoolean(SettingUtils.DRAWER_STATUS, mBinding.layoutDrawer.isDrawerOpen(GravityCompat.START));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = getActivityViewModelProvider(this).get(MainViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        mBinding.setCs(getConsoleStyle());
        mBinding.setVm(mViewModel);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_calculator, CalculatorFragment.newInstance())
                    .replace(R.id.frame_information, InformationFragment.newInstance())
                    .commitNow();
        }
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(SettingUtils.DRAWER_STATUS, false)) {
                if (mBinding.layoutDrawer != null) {
                    mBinding.layoutDrawer.openDrawer(GravityCompat.START);
                }
            }
        }

        mSharedViewModel.getInfoList().observe(this, new Observer<List<InfoItem>>() {
            @Override
            public void onChanged(List<InfoItem> infoItems) {
                mBinding.tvConsole.setText(mViewModel.getShowConsole(infoItems));
            }
        });

        mViewModel.getVersion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String version) {
                if (mBinding.tvSlideVersion != null) {
                    mBinding.tvSlideVersion.setText(getString(R.string.version, version));
                }
            }
        });

        mViewModel.getDarkMode().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean darkMode) {
                SettingUtils.changeDarkMode(darkMode);
            }
        });

        if (mBinding.spSlideSettingLanguage != null) {
            ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.language, R.layout.spinner_item);
            mBinding.spSlideSettingLanguage.setAdapter(spinnerAdapter);
            mBinding.spSlideSettingLanguage.setSelection(SettingUtils.getLanguage());
            mBinding.spSlideSettingLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    SettingUtils.changeLanguage(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    private ConsoleStyle getConsoleStyle() {
        ConsoleStyle cs = new ConsoleStyle();
        cs.setRadius(DisplayUtils.dp2px(4));
        cs.setSolidColor(getColor(R.color.solidColor));
        cs.setStrokeColor(getColor(R.color.strokeColor));
        cs.setStrokeWidth(1);
        cs.setSpacing(DisplayUtils.dp2px(5));
        return cs;
    }

}
