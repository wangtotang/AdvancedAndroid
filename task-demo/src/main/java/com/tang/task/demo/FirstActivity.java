package com.tang.task.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView textView = findViewById(R.id.btn);
        textView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(FirstActivity.this, SecondActivity.class));
                    }
                });
    }
}