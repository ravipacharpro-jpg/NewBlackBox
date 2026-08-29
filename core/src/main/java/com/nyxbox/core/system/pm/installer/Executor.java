package com.nyxbox.core.system.pm.installer;

import com.nyxbox.core.system.pm.BPackageSettings;
import com.nyxbox.entity.pm.InstallOption;


public interface Executor {
    public static final String TAG = "InstallExecutor";

    int exec(BPackageSettings ps, InstallOption option, int userId);
}
