package com.nyxbox.android.view;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.view.IGraphicsStats")
public interface IGraphicsStats {
    @BClassName("android.view.IGraphicsStats$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder IBinder0);
    }
}
