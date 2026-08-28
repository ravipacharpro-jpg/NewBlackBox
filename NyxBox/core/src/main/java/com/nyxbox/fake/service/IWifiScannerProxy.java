package com.nyxbox.fake.service;

import android.content.Context;
import android.os.IBinder;

import com.nyxbox.android.net.wifi.BRIWifiManagerStub;
import com.nyxbox.android.os.BRServiceManager;
import com.nyxbox.fake.hook.BinderInvocationStub;


public class IWifiScannerProxy extends BinderInvocationStub {

    public IWifiScannerProxy() {
        super(BRServiceManager.get().getService("wifiscanner"));
    }

    @Override
    protected Object getWho() {
        return BRIWifiManagerStub.get().asInterface(BRServiceManager.get().getService("wifiscanner"));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("wifiscanner");
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }
}
