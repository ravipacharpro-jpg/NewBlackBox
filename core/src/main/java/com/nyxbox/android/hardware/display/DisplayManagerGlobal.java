package com.nyxbox.android.hardware.display;

import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.hardware.display.DisplayManagerGlobal")
public interface DisplayManagerGlobal {
    @BField
    IInterface mDm();

    @BStaticMethod
    Object getInstance();
}
