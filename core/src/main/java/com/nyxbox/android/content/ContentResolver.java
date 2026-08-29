package com.nyxbox.android.content;

import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;
import com.nyxbox.reflection.annotation.BStaticField;

@BClassName("android.content.ContentResolver")
public interface ContentResolver {
    @BStaticField
    IInterface sContentService();

    @BField
    String mPackageName();
}
