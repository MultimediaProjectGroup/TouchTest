package com.hkust.sam.touchtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

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
    public void VolumeMute(View view){
        rootShellCmd.simulateKey(KeyEvent.KEYCODE_VOLUME_MUTE);
    }
}
