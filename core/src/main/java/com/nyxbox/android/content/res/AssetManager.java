package com.nyxbox.android.content.res;

import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BConstructor;
import com.nyxbox.reflection.annotation.BMethod;

@BClassName("android.content.res.AssetManager")
public interface AssetManager {
    @BConstructor
    android.content.res.AssetManager _new();

    @BMethod
    Integer addAssetPath(String String0);

    @BMethod
    Configuration getConfiguration();

    @BMethod
    DisplayMetrics getDisplayMetrics();
}
