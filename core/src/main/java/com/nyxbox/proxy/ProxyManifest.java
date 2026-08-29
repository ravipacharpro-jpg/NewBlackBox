package com.nyxbox.proxy;

import java.util.Locale;

import com.nyxbox.NyxBoxCore;

/**
 * Created by Milk on 4/1/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class ProxyManifest {
    public static final int FREE_COUNT = 50;

    public static boolean isProxy(String msg) {
        return getBindProvider().equals(msg) || msg.contains("proxy_content_provider_");
    }

    public static String getBindProvider() {
        return NyxBoxCore.getHostPkg() + ".core.SystemCallProvider";
    }

    public static String getProxyAuthorities(int index) {
        return String.format(Locale.CHINA, "%s.proxy_content_provider_%d", NyxBoxCore.getHostPkg(), index);
    }

    public static String getProxyPendingActivity(int index) {
        return String.format(Locale.CHINA, "com.nyxbox.proxy.ProxyPendingActivity$P%d", index);
    }

    public static String getProxyActivity(int index) {
        return String.format(Locale.CHINA, "com.nyxbox.proxy.ProxyActivity$P%d", index);
    }

    public static String TransparentProxyActivity(int index) {
        return String.format(Locale.CHINA, "com.nyxbox.proxy.TransparentProxyActivity$P%d", index);
    }

    public static String getProxyService(int index) {
        return String.format(Locale.CHINA, "com.nyxbox.proxy.ProxyService$P%d", index);
    }

    public static String getProxyJobService(int index) {
        return String.format(Locale.CHINA, "com.nyxbox.proxy.ProxyJobService$P%d", index);
    }

    public static String getProxyFileProvider() {
        return NyxBoxCore.getHostPkg() + ".core.FileProvider";
    }

    public static String getProxyReceiver() {
        return NyxBoxCore.getHostPkg() + ".stub_receiver";
    }

    public static String getProcessName(int bPid) {
        return NyxBoxCore.getHostPkg() + ":p" + bPid;
    }
}
