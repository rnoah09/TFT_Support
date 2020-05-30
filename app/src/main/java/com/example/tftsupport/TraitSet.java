package com.example.tftsupport;

import android.os.Parcel;
import android.os.Parcelable;

public class TraitSet {
    private String style;
    private int min;
    private int max;

    public TraitSet() {

    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
