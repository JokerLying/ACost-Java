package com.gnayuil.acost.data.style;

import androidx.annotation.ColorInt;

public class InfoStyle {
    private int radius;
    private @ColorInt
    int strokeColor;
    private int strokeWidth;
    private int spacing;
    private @ColorInt
    int normalSolidColor;
    private @ColorInt
    int selectedSolidColor;


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public int getNormalSolidColor() {
        return normalSolidColor;
    }

    public void setNormalSolidColor(int normalSolidColor) {
        this.normalSolidColor = normalSolidColor;
    }

    public int getSelectedSolidColor() {
        return selectedSolidColor;
    }

    public void setSelectedSolidColor(int selectedSolidColor) {
        this.selectedSolidColor = selectedSolidColor;
    }
}
