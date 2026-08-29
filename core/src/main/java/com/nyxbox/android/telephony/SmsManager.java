package com.nyxbox.android.telephony;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BMethod;

@BClassName("android.telephony.SmsManager")
public interface SmsManager {
    @BMethod
    Boolean getAutoPersisting();

    @BMethod
    void setAutoPersisting(boolean boolean0);
}
