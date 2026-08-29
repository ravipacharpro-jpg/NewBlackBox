package com.nyxbox.android.app.servertransaction;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;

@BClassName("android.app.servertransaction.LaunchActivityItem")
public interface LaunchActivityItem {
    @BField
    ActivityInfo mInfo();

    @BField
    Intent mIntent();
}
