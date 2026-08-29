package com.nyxbox.android.telephony;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BConstructor;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.telephony.CellIdentityCdma")
public interface CellIdentityCdma {
    @BConstructor
    CellIdentityCdma _new();

    @BField
    int mBasestationId();

    @BField
    int mNetworkId();

    @BField
    int mSystemId();
}
