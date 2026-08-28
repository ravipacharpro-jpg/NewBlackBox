package com.nyxbox.fake.service;

import android.content.ComponentName;

import java.lang.reflect.Method;

import com.nyxbox.android.os.BRServiceManager;
import com.nyxbox.android.view.BRIAutoFillManagerStub;
import com.nyxbox.NyxBoxCore;
import com.nyxbox.app.BActivityThread;
import com.nyxbox.fake.hook.BinderInvocationStub;
import com.nyxbox.fake.hook.MethodHook;
import com.nyxbox.fake.hook.ProxyMethod;
import com.nyxbox.proxy.ProxyManifest;


public class IAutofillManagerProxy extends BinderInvocationStub {
    public static final String TAG = "AutofillManagerStub";

    public IAutofillManagerProxy() {
        super(BRServiceManager.get().getService("autofill"));
    }

    @Override
    protected Object getWho() {
        return BRIAutoFillManagerStub.get().asInterface(BRServiceManager.get().getService("autofill"));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("autofill");
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @ProxyMethod("startSession")
    public static class StartSession extends MethodHook {

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] == null)
                        continue;
                    if (args[i] instanceof ComponentName) {
                        args[i] = new ComponentName(NyxBoxCore.getHostPkg(), ProxyManifest.getProxyActivity(NyxBoxCore.getAppPid()));
                    }
                }
            }
            return method.invoke(who, args);
        }
    }
}
