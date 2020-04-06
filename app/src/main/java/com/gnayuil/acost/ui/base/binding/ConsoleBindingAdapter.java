package com.gnayuil.acost.ui.base.binding;

import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.databinding.BindingAdapter;

public class ConsoleBindingAdapter {

    @BindingAdapter(value = {"console_radius", "console_solidColor", "console_strokeColor", "console_strokeWidth"}, requireAll = true)
    public static void setBackground(TextView view, int radius, @ColorInt int solidColor, @ColorInt int strokeColor, int strokeWidth) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setColor(solidColor);
        drawable.setStroke(strokeWidth, strokeColor);
        view.setBackground(drawable);
    }

    @BindingAdapter(value = {"console_spacing"})
    public static void setMargin(TextView view, int spacing) {
        int value = spacing / 2;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.setMargins(value * 2, value * 2, value * 2, 0);
        view.setLayoutParams(params);
    }

}
