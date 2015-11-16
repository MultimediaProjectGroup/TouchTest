package com.hkust.sam.touchtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hkust.sam.ipc.RootShellCmd;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RootShellCmd rootShellCmd = new RootShellCmd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button up = (Button) findViewById(R.id.up_btn);
        up.setOnClickListener(this);

        Button down = (Button) findViewById(R.id.down_btn);
        down.setOnClickListener(this);

        Button info = (Button) findViewById(R.id.info_btn);
        info.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.home_btn);
        home.setOnClickListener(this);



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
    public void volumeUp(){
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        intent.setAction(Constant.ACTION_VOLUME_UP);
        startService(intent);
    }

    //when down btn clicked
    public void volumeDown(){
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        intent.setAction(Constant.ACTION_VOLUME_DOWN);
        startService(intent);
    }

    //when home btn clicked
    public void home(){
        Intent intent = new Intent(getApplicationContext(), TouchController.class);
        intent.setAction(Constant.ACTION_NOTIFICATION);
        startService(intent);
    }

    //when info btn clicked
    public void showInfo(){
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.up_btn:
                volumeUp();
                break;
            case R.id.down_btn:
                volumeDown();
                break;
            case R.id.info_btn:
                showInfo();
                break;
            case R.id.home_btn:
                home();
                break;
            default:
        }
    }
}
