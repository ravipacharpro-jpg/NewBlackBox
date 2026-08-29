package com.nyxbox.android.telephony;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BConstructor;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.telephony.CellIdentityGsm")
public interface CellIdentityGsm {
    @BConstructor
    CellIdentityGsm _new();

    @BField
    int mCid();

    @BField
    int mLac();

    @BField
    int mMcc();

    @BField
    int mMnc();
}
