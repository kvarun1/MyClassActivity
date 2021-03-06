package com.mbinfo.myclassactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.mbinfo.MyApplication;
import com.mbinfo.util.ConnectivityReceiver;
import com.mbinfo.util.ConnectivityReceiverListener;
import com.mbinfo.util.MyJobIntentService;
import com.mbinfo.util.MyReceiver;

import java.util.List;

public class MainService extends AppCompatActivity implements ConnectivityReceiverListener {
Button start,stop,notification,jobintent;
    private BroadcastReceiver MyReceiver = null;
    LinearLayout linearLayout;
    ClickToCall clickToCall;
    int intArray[] = {1,2,3,4};
    String name [] = {"Arun","Varun","Satya"};
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME
    };
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);
        linearLayout =  findViewById(R.id.linear_out);
        start =  findViewById(R.id.btnStart);
        stop =  findViewById(R.id.btnstop);
        jobintent =  findViewById(R.id.button);
        notification =  findViewById(R.id.senNotification);
         ClickToCall.show();
        MyReceiver = new MyReceiver();
        checkInternet();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startServiceIntent = new Intent(MainService.this, DataFlairService.class);
                startService(startServiceIntent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopServiceIntent = new Intent(MainService.this, DataFlairService.class);
                stopService(stopServiceIntent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgroundServiceIntent = new Intent(MainService.this, ForGroundService.class);
                startService(forgroundServiceIntent);

            }
        });
        jobintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent job = new Intent(MainService.this, MyJobIntentService.class);
                job.putExtra("maxCountValue", 1000);
                MyJobIntentService.enqueueWork(MainService.this, job);
            }
        });
    }

    private void checkInternet() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }
        // Make and display Snackbar
        Snackbar snackbar = Snackbar.make(linearLayout, message,
                Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
          showSnack(isConnected);
    }
}