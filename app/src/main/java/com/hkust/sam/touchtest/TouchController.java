package com.hkust.sam.touchtest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hkust.sam.ipc.RootShellCmd;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hozdanny on 15/11/16.
 */
public class TouchController extends Service {
    public static final String TAG = "TouchController";
    RootShellCmd rootShellCmd = new RootShellCmd();

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "TouchController Service Created", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service: onCreate()");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "TouchController Service Destroy", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service: onDestroy()");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service: onStartCommand()");
        handleIntent(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void handleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) return;
        // get the action specified in the intent. The actioins are given in Constants.
        String action = intent.getAction();
        if (intent.getAction().equalsIgnoreCase(Constant.ACTION_NOTIFICATION)) {
            home();
        } else if (intent.getAction().equalsIgnoreCase(Constant.ACTION_VOLUME_UP)) {
            volumeUp();
        } else if (intent.getAction().equalsIgnoreCase(Constant.ACTION_VOLUME_DOWN)) {
            volumeDown();
        }
    }


    public void volumeUp() {
//        RootShellCmd rootShellCmd = new RootShellCmd();
        rootShellCmd.simulateKey(KeyEvent.KEYCODE_VOLUME_UP);
    }

    public void volumeDown() {
        rootShellCmd.simulateKey(KeyEvent.KEYCODE_VOLUME_DOWN);
    }


    public void home() {
//        rootShellCmd.simulateKey(KeyEvent.KEYCODE_MOVE_HOME);

        Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        int y = displaymetrics.heightPixels;
        int x = displaymetrics.widthPixels;
        String temp = x / 2 + " 1 " + x / 2 + " " + y;
        rootShellCmd.simulateSwip(temp);
    }
}
