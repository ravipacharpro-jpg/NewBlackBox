package com.nyxbox.proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.nyxbox.NyxBoxCore;
import com.nyxbox.entity.am.PendingResultData;
import com.nyxbox.proxy.record.ProxyBroadcastRecord;


public class ProxyBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "ProxyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setExtrasClassLoader(context.getClassLoader());
        ProxyBroadcastRecord record = ProxyBroadcastRecord.create(intent);
        if (record.mIntent == null) {
            return;
        }
        PendingResult pendingResult = goAsync();
        try {
            NyxBoxCore.getBActivityManager().scheduleBroadcastReceiver(record.mIntent, new PendingResultData(pendingResult), record.mUserId);
        } catch (RemoteException e) {
            pendingResult.finish();
        }
    }
}