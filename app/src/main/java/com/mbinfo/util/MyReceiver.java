package com.mbinfo.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    String status ;
    @Override
    public void onReceive(Context context, Intent intent){
        NetworkUtil networkUtil = new NetworkUtil();
        status = NetworkUtil.getConnectivityStatusString(context);
    }
}
