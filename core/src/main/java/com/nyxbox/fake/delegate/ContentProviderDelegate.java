package com.nyxbox.fake.delegate;

import android.net.Uri;
import android.os.Build;
import android.os.IInterface;
import android.util.ArrayMap;

import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import com.nyxbox.android.app.BRActivityThread;
import com.nyxbox.android.app.BRActivityThreadProviderClientRecordP;
import com.nyxbox.android.app.BRIActivityManagerContentProviderHolder;
import com.nyxbox.android.content.BRContentProviderHolderOreo;
import com.nyxbox.android.providers.BRSettingsContentProviderHolder;
import com.nyxbox.android.providers.BRSettingsGlobal;
import com.nyxbox.android.providers.BRSettingsNameValueCache;
import com.nyxbox.android.providers.BRSettingsNameValueCacheOreo;
import com.nyxbox.android.providers.BRSettingsSecure;
import com.nyxbox.android.providers.BRSettingsSystem;
import com.nyxbox.NyxBoxCore;
import com.nyxbox.fake.service.context.providers.ContentProviderStub;
import com.nyxbox.fake.service.context.providers.SystemProviderStub;
import com.nyxbox.utils.compat.BuildCompat;

/**
 * Created by Milk on 3/31/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class ContentProviderDelegate {
    public static final String TAG = "ContentProviderDelegate";
    private static Set<String> sInjected = new HashSet<>();

    public static void update(Object holder, String auth) {
        IInterface iInterface;
        if (BuildCompat.isOreo()) {
            iInterface = BRContentProviderHolderOreo.get(holder).provider();
        } else {
            iInterface = BRIActivityManagerContentProviderHolder.get(holder).provider();
        }

        if (iInterface instanceof Proxy)
            return;
        IInterface bContentProvider;
        switch (auth) {
            case "media":
            case "telephony":
            case "settings":
                bContentProvider = new SystemProviderStub().wrapper(iInterface, NyxBoxCore.getHostPkg());
                break;
            default:
                bContentProvider = new ContentProviderStub().wrapper(iInterface, NyxBoxCore.getHostPkg());
                break;
        }
        if (BuildCompat.isOreo()) {
            BRContentProviderHolderOreo.get(holder)._set_provider(bContentProvider);
        } else {
            BRIActivityManagerContentProviderHolder.get(holder)._set_provider(bContentProvider);
        }
    }

    public static void init() {
        clearSettingProvider();

        NyxBoxCore.getContext().getContentResolver().call(Uri.parse("content://settings"), "", null, null);
        Object activityThread = NyxBoxCore.mainThread();
        ArrayMap<Object, Object> map = (ArrayMap<Object, Object>) BRActivityThread.get(activityThread).mProviderMap();

        for (Object value : map.values()) {
            String[] mNames = BRActivityThreadProviderClientRecordP.get(value).mNames();
            if (mNames == null || mNames.length <= 0) {
                continue;
            }
            String providerName = mNames[0];
            if (!sInjected.contains(providerName)) {
                sInjected.add(providerName);
                final IInterface iInterface = BRActivityThreadProviderClientRecordP.get(value).mProvider();
                BRActivityThreadProviderClientRecordP.get(value)._set_mProvider(new ContentProviderStub().wrapper(iInterface, NyxBoxCore.getHostPkg()));
                BRActivityThreadProviderClientRecordP.get(value)._set_mNames(new String[]{providerName});
            }
        }
    }

    public static void clearSettingProvider() {
        Object cache;
        cache = BRSettingsSystem.get().sNameValueCache();
        if (cache != null) {
            clearContentProvider(cache);
        }
        cache = BRSettingsSecure.get().sNameValueCache();
        if (cache != null) {
            clearContentProvider(cache);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && BRSettingsGlobal.getRealClass() != null) {
            cache = BRSettingsGlobal.get().sNameValueCache();
            if (cache != null) {
                clearContentProvider(cache);
            }
        }
    }

    private static void clearContentProvider(Object cache) {
        if (BuildCompat.isOreo()) {
            Object holder = BRSettingsNameValueCacheOreo.get(cache).mProviderHolder();
            if (holder != null) {
                BRSettingsContentProviderHolder.get(holder)._set_mContentProvider(null);
            }
        } else {
            BRSettingsNameValueCache.get(cache)._set_mContentProvider(null);
        }
    }
}
