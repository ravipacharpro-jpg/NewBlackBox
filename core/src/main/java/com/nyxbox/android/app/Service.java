package com.nyxbox.android.app;


import android.app.ActivityThread;
import android.app.Application;
import android.content.Context;
import android.os.IBinder;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BMethod;
import com.nyxbox.reflection.annotation.BParamClass;

@BClassName("android.app.Service")
public interface Service {
    @BMethod
    void attach(Context context,
                @BParamClass(ActivityThread.class) Object thread, String className, IBinder token,
                Application application, Object activityManager);
}
