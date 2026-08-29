package com.nyxbox.android.content;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.content.ContentProviderNative")
public interface ContentProviderNative {
    @BStaticMethod
    IInterface asInterface(IBinder IBinder0);
}
