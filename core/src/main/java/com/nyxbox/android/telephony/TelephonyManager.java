package com.nyxbox.android.telephony;

import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticField;
import com.nyxbox.reflection.annotation.BStaticMethod;


@BClassName("android.telephony.TelephonyManager")
public interface TelephonyManager {

    @BStaticMethod
    Object getSubscriberInfoService();

    @BStaticField
    boolean sServiceHandleCacheEnabled();

    @BStaticField
    IInterface sIPhoneSubInfo();
}
