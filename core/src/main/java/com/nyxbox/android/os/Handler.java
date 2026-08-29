package com.nyxbox.android.os;

import android.os.Handler.Callback;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.os.Handler")
public interface Handler {
    @BField
    Callback mCallback();
}
