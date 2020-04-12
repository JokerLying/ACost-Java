package com.gnayuil.acost.ui.main;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.gnayuil.acost.App;
import com.gnayuil.acost.R;
import com.gnayuil.acost.data.bean.InfoItem;
import com.gnayuil.acost.data.bean.Setting;
import com.gnayuil.acost.data.style.ConsoleStyle;
import com.gnayuil.acost.databinding.ActivityMainBinding;
import com.gnayuil.acost.ui.base.BaseActivity;
import com.gnayuil.acost.ui.calculator.CalculatorFragment;
import com.gnayuil.acost.ui.information.InformationFragment;
import com.gnayuil.acost.utils.DisplayUtils;

import java.util.List;

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
        mBinding.setSetting(getSetting());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_calculator, CalculatorFragment.newInstance())
                    .replace(R.id.frame_information, InformationFragment.newInstance())
                    .commitNow();
        }

        mSharedViewModel.getInfoList().observe(this, new Observer<List<InfoItem>>() {
            @Override
            public void onChanged(List<InfoItem> infoItems) {
                mViewModel.setInfoList(infoItems);
            }
        });

        mViewModel.getConsole().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBinding.tvConsole.setText(s);
            }
        });

        if (mBinding.swSlideSettingAdvanced != null && mBinding.swSlideSettingDarkMode != null) {
            mBinding.swSlideSettingAdvanced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    App.getApp().advancedMode = checked;
                }
            });
            mBinding.swSlideSettingDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    mViewModel.changeDarkMode(checked);
                }
            });
        }
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

    private Setting getSetting() {
        String versionName = "error";
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Setting setting = new Setting();
        setting.setVersionName(getString(R.string.version, versionName));
        setting.setAdvanced(App.getApp().advancedMode);
        setting.setDarkMode(mViewModel.getDarkMode());
        return setting;
    }
}
