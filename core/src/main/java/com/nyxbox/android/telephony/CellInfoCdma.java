package com.nyxbox.android.telephony;

import android.telephony.CellIdentityCdma;
import android.telephony.CellSignalStrengthCdma;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BConstructor;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.telephony.CellInfoCdma")
public interface CellInfoCdma {
    @BConstructor
    CellInfoCdma _new();

    @BField
    CellIdentityCdma mCellIdentityCdma();

    @BField
    CellSignalStrengthCdma mCellSignalStrengthCdma();
}
