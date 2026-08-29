package com.nyxbox.android.location;


import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BMethod;

@BClassName("android.location.GpsStatus")
public interface GpsStatusL {
    @BMethod
    void setStatus(int int0, int[] ints1, float[] floats2, float[] floats3, float[] floats4, int[] ints5, int[] ints6, int[] ints7);
}
