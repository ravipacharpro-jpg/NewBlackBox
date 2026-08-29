package black.com.android.internal.appwidget;

import android.os.IBinder;
import android.os.IInterface;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BStaticMethod;

@BClassName("com.android.internal.appwidget.IAppWidgetService")
public interface IAppWidgetService {
    @BClassName("com.android.internal.appwidget.IAppWidgetService$Stub")
    interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder IBinder0);
    }
}
