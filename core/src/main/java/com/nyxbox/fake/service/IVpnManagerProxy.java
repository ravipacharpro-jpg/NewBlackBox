package com.nyxbox.fake.service;

import com.nyxbox.android.net.BRIVpnManagerStub;
import com.nyxbox.android.os.BRServiceManager;
import com.nyxbox.fake.hook.BinderInvocationStub;
import com.nyxbox.fake.hook.ScanClass;


@ScanClass(VpnCommonProxy.class)
public class IVpnManagerProxy extends BinderInvocationStub {
    public static final String TAG = "IVpnManagerProxy";
    public static final String VPN_MANAGEMENT_SERVICE = "vpn_management";

    public IVpnManagerProxy() {
        super(BRServiceManager.get().getService(VPN_MANAGEMENT_SERVICE));
    }

    @Override
    protected Object getWho() {
        return BRIVpnManagerStub.get().asInterface(BRServiceManager.get().getService(VPN_MANAGEMENT_SERVICE));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService(VPN_MANAGEMENT_SERVICE);
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }
}
