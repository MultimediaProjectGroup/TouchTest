package com.hkust.sam.touchtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hkust.sam.ipc.RootShellCmd;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    RootShellCmd rootShellCmd = new RootShellCmd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void VolumeUp(View view){
//        RootShellCmd rootShellCmd = new RootShellCmd();
        rootShellCmd.simulateKey(KeyEvent.KEYCODE_VOLUME_UP);
    }

    public void VolumeDown(View view){
        rootShellCmd.simulateKey(KeyEvent.KEYCODE_VOLUME_DOWN);
    }

    public void showInfo(View view){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        int colorDep = getWindowManager().getDefaultDisplay().getPixelFormat();
//        String android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(c.getTime());

        String text = "手机分辨率是："+width+"*"+height+" 手机色深是："+colorDep+" IMEI是："+telephonyManager.getDeviceId()+" Time: "+now;
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    public void Home(View view){
//        rootShellCmd.simulateKey(KeyEvent.KEYCODE_MOVE_HOME);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int y = displaymetrics.heightPixels;
        int x = displaymetrics.widthPixels;
        String temp = x/2 + " 1 "+ x/2 +" "+y;
        rootShellCmd.simulateSwip(temp);
    }
}
