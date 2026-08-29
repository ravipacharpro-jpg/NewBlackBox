package com.nyxbox.android.webkit;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BMethod;

@BClassName("android.webkit.IWebViewUpdateService")
public interface IWebViewUpdateService {
    @BMethod
    String getCurrentWebViewPackageName();

    @BMethod
    Object waitForAndGetProvider();
}
