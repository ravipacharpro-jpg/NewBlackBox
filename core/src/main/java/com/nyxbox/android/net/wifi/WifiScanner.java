package com.nyxbox.android.net.wifi;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticField;

@BClassName("android.net.wifi.WifiScanner")
public interface WifiScanner {
    @BStaticField
    String GET_AVAILABLE_CHANNELS_EXTRA();
}
