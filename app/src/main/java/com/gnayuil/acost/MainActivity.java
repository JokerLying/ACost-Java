package com.gnayuil.acost;

import android.os.Bundle;

import com.gnayuil.acost.ui.base.BaseActivity;
import com.gnayuil.acost.ui.calculator.CalculatorFragment;
import com.gnayuil.acost.ui.information.InformationFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_calculator, CalculatorFragment.newInstance())
                    .replace(R.id.frame_information, InformationFragment.newInstance())
                    .commitNow();
        }
    }
}
