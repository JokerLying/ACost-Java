package com.gnayuil.acost.ui.base.binding;

import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.gnayuil.acost.data.style.ConsoleStyle;

public class ConsoleBindingAdapter {

    @BindingAdapter(value = {"console_style"})
    public static void setBackground(TextView view, ConsoleStyle style) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(style.getRadius());
        drawable.setColor(style.getSolidColor());
        drawable.setStroke(style.getStrokeWidth(), style.getStrokeColor());
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
