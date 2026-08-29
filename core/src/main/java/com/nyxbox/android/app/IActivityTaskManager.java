package com.nyxbox.android.app;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.app.IActivityTaskManager")
public interface IActivityTaskManager {
    @BClassName("android.app.IActivityTaskManager$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder IBinder0);
    }
}
