package com.nyxbox.android.telephony;

import android.telephony.CellIdentityGsm;
import android.telephony.CellSignalStrengthGsm;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BConstructor;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.telephony.CellInfoGsm")
public interface CellInfoGsm {
    @BConstructor
    CellInfoGsm _new();

    @BField
    CellIdentityGsm mCellIdentityGsm();

    @BField
    CellSignalStrengthGsm mCellSignalStrengthGsm();
}
