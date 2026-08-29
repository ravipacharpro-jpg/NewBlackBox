package com.nyxbox.android.content.pm;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BConstructor;
import com.nyxbox.reflection.annotation.BStaticField;

@BClassName("android.content.pm.UserInfo")
public interface UserInfo {
    @BConstructor
    Object _new(int id, String name, int flags);

    @BStaticField
    int FLAG_PRIMARY();
}
