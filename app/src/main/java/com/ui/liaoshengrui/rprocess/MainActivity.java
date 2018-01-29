package com.ui.liaoshengrui.rprocess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    RProcessView rProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rProcess = findViewById(R.id.rprocess);
        new Thread() {
            @Override
            public void run() {
                int process = rProcess.getProcess();

                while (process < 100) {
                    process++;
                    rProcess.setProcess(process);
                    Log.i("tag", "当前进度" + process);
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }
}
