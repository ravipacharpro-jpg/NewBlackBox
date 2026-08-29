package com.nyxbox.android.rms;

import java.util.Map;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.rms.HwSysResImpl")
public interface HwSysResImplP {
    @BField
    Map<Integer, java.util.ArrayList<String>> mWhiteListMap();
}
