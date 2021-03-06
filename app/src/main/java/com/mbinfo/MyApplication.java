package com.mbinfo;

import android.app.Application;

import com.mbinfo.util.ConnectivityReceiver;
import com.mbinfo.util.ConnectivityReceiverListener;

public class MyApplication extends Application {
    private static MyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener( ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
