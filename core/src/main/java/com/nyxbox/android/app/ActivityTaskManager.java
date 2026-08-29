package com.nyxbox.android.app;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticField;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.app.ActivityTaskManager")
public interface ActivityTaskManager {

    @BStaticMethod
    Object getService();

    @BStaticField
    Object IActivityTaskManagerSingleton();
}
