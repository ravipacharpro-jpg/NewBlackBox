package com.nyxbox.fake.service;

import android.content.Context;

import java.lang.reflect.Method;

import com.nyxbox.android.app.BRIAlarmManagerStub;
import com.nyxbox.android.os.BRServiceManager;
import com.nyxbox.fake.hook.BinderInvocationStub;
import com.nyxbox.fake.hook.MethodHook;
import com.nyxbox.fake.hook.ProxyMethod;


public class IAlarmManagerProxy extends BinderInvocationStub {

    public IAlarmManagerProxy() {
        super(BRServiceManager.get().getService(Context.ALARM_SERVICE));
    }

    @Override
    protected Object getWho() {
        return BRIAlarmManagerStub.get().asInterface(BRServiceManager.get().getService(Context.ALARM_SERVICE));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService(Context.ALARM_SERVICE);
    }

    @ProxyMethod("set")
    public static class Set extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }
}
