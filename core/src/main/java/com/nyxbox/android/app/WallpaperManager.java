package com.nyxbox.android.app;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;
import com.nyxbox.reflection.annotation.BStaticField;

@BClassName("android.app.WallpaperManager")
public interface WallpaperManager {
    @BStaticField
    Object sGlobals();

    @BClassName("android.app.WallpaperManager$Globals")
    interface Globals {
        @BField
        Object mService();
    }
}
