package com.nyxbox.android.app;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BMethod;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.app.IApplicationThread")
public interface IApplicationThreadOreo {
    @BMethod
    void scheduleServiceArgs();

    @BClassName("android.app.IApplicationThread$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder IBinder0);
    }
}
