package com.tanghongtu.advanced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String value = null;
        if (savedInstanceState != null) {
            value = savedInstanceState.getString("key");
        }
        TextView tv = new TextView(this);
        tv.setText(value == null ? "onClick" : value);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post("Hello World");
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });
        addContentView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Log.d("task", "SecondActivity: " + getTaskId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("App", "onRestart: " + getComponentName());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String value = savedInstanceState.getString("key");
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "value");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("App", "onNewIntent: " + getComponentName());
    }
}