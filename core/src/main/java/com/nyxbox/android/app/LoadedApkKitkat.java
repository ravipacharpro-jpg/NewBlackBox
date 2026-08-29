package com.nyxbox.android.app;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.app.LoadedApk")
public interface LoadedApkKitkat {
    @BField
    Object mDisplayAdjustments();
}
