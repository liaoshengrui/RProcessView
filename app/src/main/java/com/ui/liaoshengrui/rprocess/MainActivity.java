package com.ui.liaoshengrui.rprocess;


import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    RProcessView rp;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rp=findViewById(R.id.mRpView);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!thread.isAlive()) {
                    thread.start();

                }
            }
        });
    }

    public  Thread thread =new Thread(){
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                SystemClock.sleep(200);
                rp.setProcess(i);
            }
        }
    };

}
