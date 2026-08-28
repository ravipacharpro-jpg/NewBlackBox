package com.nyxbox.android.content;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BConstructor;
import com.nyxbox.reflection.annotation.BField;
import com.nyxbox.reflection.annotation.BMethod;


@BClassName("android.content.AttributionSource")
public interface AttributionSource {
    @BConstructor
    Object _new(int i,int i2,String str,String str2);

    @BConstructor
    Object _new(int i,String str,String str2);

    @BField
    Object mAttributionSourceState();

    @BMethod
    Object getNext();
}
