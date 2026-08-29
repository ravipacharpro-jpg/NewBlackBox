package com.nyxbox.android.hardware.location;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;


@BClassName("android.hardware.location.IContextHubService")
public interface IContextHubService {

    @BClassName("android.hardware.location.IContextHubService$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
