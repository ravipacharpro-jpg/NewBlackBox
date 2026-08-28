package com.nyxbox.fake.hook;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import com.nyxbox.NyxBoxCore;
import com.nyxbox.fake.delegate.AppInstrumentation;

import com.nyxbox.fake.service.HCallbackProxy;
import com.nyxbox.fake.service.IAccessibilityManagerProxy;
import com.nyxbox.fake.service.IAccountManagerProxy;
import com.nyxbox.fake.service.IActivityClientProxy;
import com.nyxbox.fake.service.IActivityManagerProxy;
import com.nyxbox.fake.service.IActivityTaskManagerProxy;
import com.nyxbox.fake.service.IAlarmManagerProxy;
import com.nyxbox.fake.service.IAppOpsManagerProxy;
import com.nyxbox.fake.service.IAppWidgetManagerProxy;
import com.nyxbox.fake.service.IAttributionSourceProxy;
import com.nyxbox.fake.service.IAutofillManagerProxy;
import com.nyxbox.fake.service.ISensitiveContentProtectionManagerProxy;
import com.nyxbox.fake.service.ISettingsSystemProxy;
import com.nyxbox.fake.service.IConnectivityManagerProxy;
import com.nyxbox.fake.service.ISystemSensorManagerProxy;
import com.nyxbox.fake.service.IContentProviderProxy;
import com.nyxbox.fake.service.IXiaomiAttributionSourceProxy;
import com.nyxbox.fake.service.IXiaomiSettingsProxy;
import com.nyxbox.fake.service.IXiaomiMiuiServicesProxy;
import com.nyxbox.fake.service.IDnsResolverProxy;
import com.nyxbox.fake.service.IContextHubServiceProxy;
import com.nyxbox.fake.service.IDeviceIdentifiersPolicyProxy;
import com.nyxbox.fake.service.IDevicePolicyManagerProxy;
import com.nyxbox.fake.service.IDisplayManagerProxy;
import com.nyxbox.fake.service.IFingerprintManagerProxy;
import com.nyxbox.fake.service.IGraphicsStatsProxy;
import com.nyxbox.fake.service.IJobServiceProxy;
import com.nyxbox.fake.service.ILauncherAppsProxy;
import com.nyxbox.fake.service.ILocationManagerProxy;
import com.nyxbox.fake.service.IMediaRouterServiceProxy;
import com.nyxbox.fake.service.IMediaSessionManagerProxy;
import com.nyxbox.fake.service.IAudioServiceProxy;
import com.nyxbox.fake.service.ISensorPrivacyManagerProxy;
import com.nyxbox.fake.service.ContentResolverProxy;
import com.nyxbox.fake.service.IWebViewUpdateServiceProxy;
import com.nyxbox.fake.service.IMiuiSecurityManagerProxy;
import com.nyxbox.fake.service.SystemLibraryProxy;
import com.nyxbox.fake.service.ReLinkerProxy;
import com.nyxbox.fake.service.WebViewProxy;
import com.nyxbox.fake.service.WebViewFactoryProxy;
import com.nyxbox.fake.service.MediaRecorderProxy;
import com.nyxbox.fake.service.AudioRecordProxy;
import com.nyxbox.fake.service.MediaRecorderClassProxy;
import com.nyxbox.fake.service.SQLiteDatabaseProxy;
import com.nyxbox.fake.service.ClassLoaderProxy;
import com.nyxbox.fake.service.FileSystemProxy;
import com.nyxbox.fake.service.GmsProxy;
import com.nyxbox.fake.service.LevelDbProxy;
import com.nyxbox.fake.service.DeviceIdProxy;
import com.nyxbox.fake.service.GoogleAccountManagerProxy;
import com.nyxbox.fake.service.AuthenticationProxy;
import com.nyxbox.fake.service.AndroidIdProxy;
import com.nyxbox.fake.service.AudioPermissionProxy;

import com.nyxbox.fake.service.INetworkManagementServiceProxy;
import com.nyxbox.fake.service.INotificationManagerProxy;
import com.nyxbox.fake.service.IPackageManagerProxy;
import com.nyxbox.fake.service.IPermissionManagerProxy;
import com.nyxbox.fake.service.IPersistentDataBlockServiceProxy;
import com.nyxbox.fake.service.IPhoneSubInfoProxy;
import com.nyxbox.fake.service.IPowerManagerProxy;
import com.nyxbox.fake.service.ApkAssetsProxy;
import com.nyxbox.fake.service.ResourcesManagerProxy;
import com.nyxbox.fake.service.IShortcutManagerProxy;
import com.nyxbox.fake.service.IStorageManagerProxy;
import com.nyxbox.fake.service.IStorageStatsManagerProxy;
import com.nyxbox.fake.service.ISystemUpdateProxy;
import com.nyxbox.fake.service.ITelephonyManagerProxy;
import com.nyxbox.fake.service.ITelephonyRegistryProxy;
import com.nyxbox.fake.service.IUserManagerProxy;
import com.nyxbox.fake.service.IVibratorServiceProxy;
import com.nyxbox.fake.service.IVpnManagerProxy;
import com.nyxbox.fake.service.IWifiManagerProxy;
import com.nyxbox.fake.service.IWifiScannerProxy;
import com.nyxbox.fake.service.IWindowManagerProxy;
import com.nyxbox.fake.service.context.ContentServiceStub;
import com.nyxbox.fake.service.context.RestrictionsManagerStub;
import com.nyxbox.fake.service.libcore.OsStub;
import com.nyxbox.utils.Slog;
import com.nyxbox.utils.compat.BuildCompat;
import com.nyxbox.fake.service.ISettingsProviderProxy;
import com.nyxbox.fake.service.FeatureFlagUtilsProxy;
import com.nyxbox.fake.service.WorkManagerProxy;
import com.nyxbox.fake.service.ILocaleManagerProxy;
import com.nyxbox.fake.service.vivo.IVivoPermissionServiceProxy;



public class HookManager {
    public static final String TAG = "HookManager";

    private static final HookManager sHookManager = new HookManager();

    private final Map<Class<?>, IInjectHook> mInjectors = new HashMap<>();

    public static HookManager get() {
        return sHookManager;
    }

    public void init() {
        if (NyxBoxCore.get().isBlackProcess() || NyxBoxCore.get().isServerProcess()) {
            addInjector(new IDisplayManagerProxy());
            addInjector(new OsStub());
            addInjector(new IActivityManagerProxy());
            addInjector(new IPackageManagerProxy());
            addInjector(new ITelephonyManagerProxy());
            addInjector(new HCallbackProxy());
            addInjector(new IAppOpsManagerProxy());
            addInjector(new INotificationManagerProxy());
            addInjector(new IAlarmManagerProxy());
            addInjector(new IAppWidgetManagerProxy());
            addInjector(new ContentServiceStub());
            addInjector(new IWindowManagerProxy());
            addInjector(new IUserManagerProxy());
            addInjector(new RestrictionsManagerStub());
            addInjector(new IMediaSessionManagerProxy());
            addInjector(new IAudioServiceProxy());
            addInjector(new ISensorPrivacyManagerProxy());
            addInjector(new ContentResolverProxy());
            addInjector(new IWebViewUpdateServiceProxy());
            addInjector(new SystemLibraryProxy());
            addInjector(new ReLinkerProxy());
            addInjector(new WebViewProxy());
            addInjector(new WebViewFactoryProxy());
            addInjector(new WorkManagerProxy());
            addInjector(new MediaRecorderProxy());
            addInjector(new AudioRecordProxy());
            addInjector(new IMiuiSecurityManagerProxy());
            addInjector(new ISettingsProviderProxy());
            addInjector(new FeatureFlagUtilsProxy());
            addInjector(new MediaRecorderClassProxy());
            addInjector(new SQLiteDatabaseProxy());
            addInjector(new ClassLoaderProxy());
            addInjector(new FileSystemProxy());
            addInjector(new GmsProxy());
            addInjector(new LevelDbProxy());
            addInjector(new DeviceIdProxy());
            addInjector(new GoogleAccountManagerProxy());
            addInjector(new AuthenticationProxy());
            addInjector(new AndroidIdProxy());
            addInjector(new AudioPermissionProxy());
            addInjector(new ILocationManagerProxy());
            addInjector(new IStorageManagerProxy());
            addInjector(new ILauncherAppsProxy());
            addInjector(new IJobServiceProxy());
            addInjector(new IAccessibilityManagerProxy());
            addInjector(new ITelephonyRegistryProxy());
            addInjector(new IDevicePolicyManagerProxy());
            addInjector(new IAccountManagerProxy());
            addInjector(new IConnectivityManagerProxy());
            addInjector(new IDnsResolverProxy());
                    addInjector(new IAttributionSourceProxy());
        addInjector(new IContentProviderProxy());
        addInjector(new ISettingsSystemProxy());
        addInjector(new ISystemSensorManagerProxy());
        
        
        addInjector(new IXiaomiAttributionSourceProxy());
        addInjector(new IXiaomiSettingsProxy());
        addInjector(new IXiaomiMiuiServicesProxy());
            addInjector(new IPhoneSubInfoProxy());
            addInjector(new IMediaRouterServiceProxy());
            addInjector(new IPowerManagerProxy());
            addInjector(new IContextHubServiceProxy());
            
            addInjector(new IVibratorServiceProxy());
            addInjector(new IPersistentDataBlockServiceProxy());
            addInjector(new ILocaleManagerProxy());
            addInjector(new IVivoPermissionServiceProxy());
            addInjector(AppInstrumentation.get());
            
            addInjector(new IWifiManagerProxy());
            addInjector(new IWifiScannerProxy());
            addInjector(new ApkAssetsProxy());
            addInjector(new ResourcesManagerProxy());
            
            if (BuildCompat.isS()) {
                addInjector(new IActivityClientProxy(null));
                addInjector(new IVpnManagerProxy());
            }
            
            if (BuildCompat.isS()) {
                addInjector(new ISensitiveContentProtectionManagerProxy());
            }
            
            if (BuildCompat.isR()) {
                addInjector(new IPermissionManagerProxy());
            }
            
            if (BuildCompat.isQ()) {
                addInjector(new IActivityTaskManagerProxy());
            }
            
            if (BuildCompat.isPie()) {
                addInjector(new ISystemUpdateProxy());
            }
            
            if (BuildCompat.isOreo()) {
                addInjector(new IAutofillManagerProxy());
                addInjector(new IDeviceIdentifiersPolicyProxy());
                addInjector(new IStorageStatsManagerProxy());
            }
            
            if (BuildCompat.isN_MR1()) {
                addInjector(new IShortcutManagerProxy());
            }
            
            if (BuildCompat.isN()) {
                addInjector(new INetworkManagementServiceProxy());
            }
            
            if (BuildCompat.isM()) {
                addInjector(new IFingerprintManagerProxy());
                addInjector(new IGraphicsStatsProxy());
            }
            
            if (BuildCompat.isL()) {
                addInjector(new IJobServiceProxy());
            }
        }
        injectAll();
    }

    public void checkEnv(Class<?> clazz) {
        IInjectHook iInjectHook = mInjectors.get(clazz);
        if (iInjectHook != null && iInjectHook.isBadEnv()) {
            Log.d(TAG, "checkEnv: " + clazz.getSimpleName() + " is bad env");
            iInjectHook.injectHook();
        }
    }

    public void checkAll() {
        for (Class<?> aClass : mInjectors.keySet()) {
            IInjectHook iInjectHook = mInjectors.get(aClass);
            if (iInjectHook != null && iInjectHook.isBadEnv()) {
                Log.d(TAG, "checkEnv: " + aClass.getSimpleName() + " is bad env");
                iInjectHook.injectHook();
            }
        }
    }

    void addInjector(IInjectHook injectHook) {
        mInjectors.put(injectHook.getClass(), injectHook);
    }

    void injectAll() {
        for (IInjectHook value : mInjectors.values()) {
            try {
                Slog.d(TAG, "hook: " + value);
                value.injectHook();
            } catch (Exception e) {
                Slog.d(TAG, "hook error: " + value);
                
                handleHookError(value, e);
            }
        }
    }

    
    private void handleHookError(IInjectHook hook, Exception e) {
        String hookName = hook.getClass().getSimpleName();
        
        
        Slog.e(TAG, "Hook failed: " + hookName + " - " + e.getMessage(), e);
        
        
        if (hookName.contains("ActivityManager") || 
            hookName.contains("PackageManager") ||
            hookName.contains("WebView") ||
            hookName.contains("ContentProvider")) {
            
            Slog.w(TAG, "Critical hook failed: " + hookName + ", attempting recovery");
            
            try {
                
                if (hook.isBadEnv()) {
                    Slog.d(TAG, "Attempting to recover hook: " + hookName);
                    hook.injectHook();
                }
            } catch (Exception recoveryException) {
                Slog.e(TAG, "Hook recovery failed: " + hookName, recoveryException);
            }
        }
    }

    
    public boolean areCriticalHooksInstalled() {
        String[] criticalHooks = {
            "IActivityManagerProxy",
            "IPackageManagerProxy", 
            "WebViewProxy",
            "IContentProviderProxy"
        };
        
        for (String hookName : criticalHooks) {
            boolean found = false;
            for (Class<?> hookClass : mInjectors.keySet()) {
                if (hookClass.getSimpleName().equals(hookName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Slog.w(TAG, "Critical hook missing: " + hookName);
                return false;
            }
        }
        
        Slog.d(TAG, "All critical hooks are installed");
        return true;
    }

    
    public void reinitializeHooks() {
        Slog.d(TAG, "Reinitializing all hooks");
        
        
        mInjectors.clear();
        
        
        init();
        
        Slog.d(TAG, "Hook reinitialization completed");
    }
}
