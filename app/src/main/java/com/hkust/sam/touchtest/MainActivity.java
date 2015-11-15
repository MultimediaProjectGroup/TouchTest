package com.hkust.sam.touchtest;

import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        intent.setAction(Constant.ACTION_NOTIFICATION);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        stopService(intent);
    }

    //when up btn clicked
    public void VolumeUp(View view){
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        intent.setAction(Constant.ACTION_VOLUME_UP);
        startService(intent);
    }

    //when down btn clicked
    public void VolumeDown(View view){
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        intent.setAction(Constant.ACTION_VOLUME_DOWN);
        startService(intent);
    }

    //when home btn clicked
    public void Home(View view){
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        intent.setAction(Constant.ACTION_NOTIFICATION);
        startService(intent);
    }

    //when info btn clicked
    public void showInfo(View view){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        int colorDep = getWindowManager().getDefaultDisplay().getPixelFormat();
        //String android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(c.getTime());

        String text = "手机分辨率是："+width+"*"+height+" 手机色深是："+colorDep+" IMEI是："+telephonyManager.getDeviceId()+" Time: "+now;
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

}
