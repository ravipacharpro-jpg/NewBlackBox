package com.nyxbox.fake.service;

import static com.nyxbox.app.BActivityThread.getUid;

import java.lang.reflect.Method;

import com.nyxbox.android.os.BRINetworkManagementServiceStub;
import com.nyxbox.android.os.BRServiceManager;
import com.nyxbox.fake.hook.BinderInvocationStub;
import com.nyxbox.fake.hook.MethodHook;
import com.nyxbox.fake.hook.ProxyMethod;
import com.nyxbox.fake.service.base.UidMethodProxy;
import com.nyxbox.utils.MethodParameterUtils;
import com.nyxbox.utils.Slog;


public class INetworkManagementServiceProxy extends BinderInvocationStub {
    public static final String NAME = "network_management";

    public INetworkManagementServiceProxy() {
        super(BRServiceManager.get().getService(NAME));
    }

    @Override
    protected Object getWho() {
        return BRINetworkManagementServiceStub.get().asInterface(BRServiceManager.get().getService(NAME));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService(NAME);
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new UidMethodProxy("setUidCleartextNetworkPolicy", 0));
        addMethodHook(new UidMethodProxy("setUidMeteredNetworkBlacklist", 0));
        addMethodHook(new UidMethodProxy("setUidMeteredNetworkWhitelist", 0));
    }

    @ProxyMethod("getNetworkStatsUidDetail")
    public static class getNetworkStatsUidDetail extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstUid(args);
            MethodParameterUtils.replaceFirstAppPkg(args);
            return method.invoke(who, args);
        }
    }
}
