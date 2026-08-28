package com.nyxbox.core.system.pm.installer;

import com.nyxbox.core.env.BEnvironment;
import com.nyxbox.core.system.pm.BPackageSettings;
import com.nyxbox.entity.pm.InstallOption;
import com.nyxbox.utils.FileUtils;


public class RemoveAppExecutor implements Executor {
    @Override
    public int exec(BPackageSettings ps, InstallOption option, int userId) {
        FileUtils.deleteDir(BEnvironment.getAppDir(ps.pkg.packageName));
        return 0;
    }
}
