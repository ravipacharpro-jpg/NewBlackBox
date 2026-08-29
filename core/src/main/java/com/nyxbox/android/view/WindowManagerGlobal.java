package com.nyxbox.android.view;

import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticField;

@BClassName("android.view.WindowManagerGlobal")
public interface WindowManagerGlobal {
    @BStaticField
    int ADD_PERMISSION_DENIED();

    @BStaticField
    IInterface sWindowManagerService();
}
