package black.com.android.internal.os;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("android.os.IVibratorService")
public interface IVibratorService {
    @BClassName("android.os.IVibratorService$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder IBinder0);
    }
}
