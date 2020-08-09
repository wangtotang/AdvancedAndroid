package com.tanghongtu.advanced;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static Runnable myRunnable = new Runnable() {

        private Handler handler;

        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            //创建子线程的looper和绑定handler子对象
            Looper.prepare();
            handler = new Handler() {

                @Override
                public void handleMessage(@NonNull Message msg) {

                }
            };
            Looper.loop();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = findViewById(R.id.textView);
        viewById.setText("怎么回事？");

    }

}