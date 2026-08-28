package com.nyxbox.android.bluetooth;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;

/**
 * @author gm
 * @function
 * @date :2024/4/23 15:24
 **/
@BClassName("android.bluetooth.IBluetooth")
public interface IBluetooth {
    @BClassName("android.bluetooth.IBluetooth$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder IBinder0);
    }
}
