package com.gnayuil.acost.ui.base.binding;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.gnayuil.acost.data.style.InfoStyle;

public class InfoBindingAdapter {

    @BindingAdapter(value = {"info_style", "info_check"}, requireAll = false)
    public static void setBackground(View view, InfoStyle style, boolean check) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(style.getRadius());
        if (check) {
            drawable.setColor(style.getSelectedSolidColor());
        } else {
            drawable.setColor(style.getNormalSolidColor());
        }
        drawable.setStroke(style.getStrokeWidth(), style.getStrokeColor());
        view.setBackground(drawable);
    }

    @BindingAdapter(value = {"lambda_visibility"})
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
