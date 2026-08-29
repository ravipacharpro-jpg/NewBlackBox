package com.nyxbox.android.os.storage;

import android.os.storage.StorageVolume;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.os.storage.StorageManager")
public interface StorageManager {
    @BStaticMethod
    StorageVolume[] getVolumeList(int int0, int int1);
}
