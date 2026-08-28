package com.nyxbox.android.view;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BMethod;

@BClassName("android.view.DisplayAdjustments")
public interface DisplayAdjustments {
    @BMethod
    void setCompatibilityInfo();
}
