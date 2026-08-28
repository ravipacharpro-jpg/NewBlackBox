package com.nyxbox.utils.compat;

import android.content.ContentProvider;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Binder;

import com.nyxbox.android.app.BRContextImpl;
import com.nyxbox.android.app.BRContextImplKitkat;
import com.nyxbox.android.content.AttributionSourceStateContext;
import com.nyxbox.android.content.BRAttributionSource;
import com.nyxbox.android.content.BRAttributionSourceState;
import com.nyxbox.android.content.BRContentResolver;
import com.nyxbox.BlackBoxCore;
import com.nyxbox.app.BActivityThread;
/**
 * Created by @RIYAZXERO on 3/31/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class ContextCompat {
	public static final String TAG = "ContextCompat";

	public static void fixAttributionSourceState(Object obj, int uid) {
		fixAttributionSourceState(obj, uid, 0);
	}

	public static void fixAttributionSourceState(Object obj, int uid, int depth) {
		if (depth >= 10) return;
		if (obj != null && BRAttributionSource.get(obj)._check_mAttributionSourceState() != null) {
			Object mAttributionSourceState = BRAttributionSource.get(obj).mAttributionSourceState();
			AttributionSourceStateContext attributionSourceStateContext = BRAttributionSourceState.get(mAttributionSourceState);
			attributionSourceStateContext._set_packageName(BlackBoxCore.getHostPkg());
			attributionSourceStateContext._set_uid(uid);
			fixAttributionSourceState(BRAttributionSource.get(obj).getNext(), uid, depth + 1);
		}
	}

	public static void fix(Context context) {
        if (context == null) return;
		try {
			int deep = 0;
			while (context instanceof ContextWrapper) {
				context = ((ContextWrapper) context).getBaseContext();
				deep++;
				if (deep >= 10) {
					return;
				}
			}
			BRContextImpl.get(context)._set_mPackageManager(null);
			try {
				context.getPackageManager();
			} catch (Throwable e) {
				e.printStackTrace();
			}

			BRContextImpl.get(context)._set_mBasePackageName(BlackBoxCore.getHostPkg());
			BRContextImplKitkat.get(context)._set_mOpPackageName(BlackBoxCore.getHostPkg());
			BRContentResolver.get(context.getContentResolver())._set_mPackageName(BlackBoxCore.getHostPkg());

			if (BuildCompat.isS()) {
				fixAttributionSourceState(BRContextImpl.get(context).getAttributionSource(), BActivityThread.getBUid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
