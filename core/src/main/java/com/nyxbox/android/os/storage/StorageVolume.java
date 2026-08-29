package com.nyxbox.android.os.storage;

import java.io.File;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.os.storage.StorageVolume")
public interface StorageVolume {
    @BField
    File mInternalPath();

    @BField
    File mPath();
}
