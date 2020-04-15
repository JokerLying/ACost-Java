package com.gnayuil.acost.ui.base.binding;

import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;

import com.gnayuil.acost.R;

public class ButtonBindingAdapter {

    @BindingAdapter(value = {"btn_spacing"})
    public static void setMargin(Button view, int spacing) {
        int value = spacing / 2;
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        if (view.getId() == R.id.btn_add) {
            params.setMargins(value * 2, 0, value, 0);
        } else if (view.getId() == R.id.btn_del) {
            params.setMargins(value, value * 2, value, value);
        } else if (view.getId() == R.id.btn_dot) {
            params.setMargins(value * 2, value, value, value);
        } else if (view.getId() == R.id.btn_0) {
            params.setMargins(value, value * 2, 0, value);
        } else if (view.getId() == R.id.btn_7 || view.getId() == R.id.btn_8) {
            params.setMargins(value, value, value, value * 2);
        } else if (view.getId() == R.id.btn_3 || view.getId() == R.id.btn_6 || view.getId() == R.id.btn_9) {
            params.setMargins(value, value, value * 2, value);
        } else {
            params.setMargins(value, value, value, value);
        }
        view.setLayoutParams(params);
        ConstraintLayout layout = (ConstraintLayout) view.getParent();
        layout.setPadding(value, value, value, value);
    }

}
