package com.tanghongtu.advanced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.tang.eventbus.EventBus;
import com.tang.eventbus.Subscribe;
import com.tang.eventbus.ThreadMode;

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
        Log.d("task", "MainActivity: " + getTaskId());
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        EventBus.getDefault().register(this);

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("App", "onConfigurationChanged: " + getComponentName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onReceived(String msg) {
        Log.d("tang", "onReceived: "+msg);
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}