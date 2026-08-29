package com.nyxbox.fake.service;

import android.os.IInterface;

import java.lang.reflect.Method;

import com.nyxbox.android.hardware.display.BRDisplayManagerGlobal;
import com.nyxbox.fake.hook.ClassInvocationStub;
import com.nyxbox.fake.hook.MethodHook;
import com.nyxbox.fake.hook.ProxyMethod;
import com.nyxbox.fake.service.base.PkgMethodProxy;
import com.nyxbox.utils.MethodParameterUtils;


public class IDisplayManagerProxy extends ClassInvocationStub {

    public IDisplayManagerProxy() {
    }

    @Override
    protected Object getWho() {
        return BRDisplayManagerGlobal.get(BRDisplayManagerGlobal.get().getInstance()).mDm();
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        Object dmg = BRDisplayManagerGlobal.get().getInstance();
        BRDisplayManagerGlobal.get(dmg)._set_mDm(getProxyInvocation());
    }

    @Override
    public boolean isBadEnv() {
        Object dmg = BRDisplayManagerGlobal.get().getInstance();
        IInterface mDm = BRDisplayManagerGlobal.get(dmg).mDm();
        return mDm != getProxyInvocation();
    }


    @ProxyMethod("createVirtualDisplay")
    public static class CreateVirtualDisplay extends MethodHook {

        @Override
        protected String getMethodName() {
            return "createVirtualDisplay";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return method.invoke(who, args);
        }
    }
}
