package com.nyxbox.fake.service;


import com.nyxbox.android.os.BRServiceManager;
import com.nyxbox.android.view.BRIAutoFillManagerStub;
import com.nyxbox.fake.hook.BinderInvocationStub;


public class ISystemUpdateProxy extends BinderInvocationStub {
    public ISystemUpdateProxy() {
        super(BRServiceManager.get().getService("system_update"));
    }

    @Override
    protected Object getWho() {
        return BRIAutoFillManagerStub.get().asInterface(BRServiceManager.get().getService("system_update"));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("system_update");
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }
}
