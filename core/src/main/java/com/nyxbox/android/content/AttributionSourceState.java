package com.nyxbox.android.content;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;


@BClassName("android.content.AttributionSourceState")
public interface AttributionSourceState {
    @BField
    String packageName();

    @BField
    int uid();

    @BField
    int pid();
}
