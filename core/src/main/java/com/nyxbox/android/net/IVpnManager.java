package com.nyxbox.android.net;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;


@BClassName("android.net.IVpnManager")
public interface IVpnManager {

    @BClassName("android.net.IVpnManager$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder IBinder0);
    }
}
