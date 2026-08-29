package com.nyxbox.fake.service;

import java.lang.reflect.Method;

import com.nyxbox.android.os.BRServiceManager;
import com.nyxbox.android.view.BRIGraphicsStatsStub;
import com.nyxbox.fake.hook.BinderInvocationStub;
import com.nyxbox.fake.hook.MethodHook;
import com.nyxbox.fake.hook.ProxyMethod;
import com.nyxbox.utils.MethodParameterUtils;


public class IGraphicsStatsProxy extends BinderInvocationStub {

    public IGraphicsStatsProxy() {
        super(BRServiceManager.get().getService("graphicsstats"));
    }

    @Override
    protected Object getWho() {
        return BRIGraphicsStatsStub.get().asInterface(BRServiceManager.get().getService("graphicsstats"));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("graphicsstats");
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @ProxyMethod("requestBufferForProcess")
    public static class RequestBufferForProcess extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return method.invoke(who, args);
        }
    }
}
