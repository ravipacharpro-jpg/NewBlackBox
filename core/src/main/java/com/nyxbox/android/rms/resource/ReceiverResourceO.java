package com.nyxbox.android.rms.resource;

import java.util.Map;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.rms.resource.ReceiverResource")
public interface ReceiverResourceO {
    @BField
    Map<Integer, java.util.List<String>> mWhiteListMap();
}
