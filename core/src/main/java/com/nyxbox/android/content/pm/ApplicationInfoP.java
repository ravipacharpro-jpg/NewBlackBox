package com.nyxbox.android.content.pm;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;
import com.nyxbox.reflection.annotation.BMethod;

@BClassName("android.content.pm.ApplicationInfo")
public interface ApplicationInfoP {
    @BField
    String[] splitClassLoaderNames();

    @BMethod
    void setHiddenApiEnforcementPolicy(int int0);
}
