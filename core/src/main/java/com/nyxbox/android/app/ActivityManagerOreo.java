package com.nyxbox.android.app;

import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticField;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.app.ActivityManager")
public interface ActivityManagerOreo {
    @BStaticField
    Object IActivityManagerSingleton();

    @BStaticMethod
    IInterface getService();
}
