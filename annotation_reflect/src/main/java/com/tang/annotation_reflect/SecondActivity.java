package com.tang.annotation_reflect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Autowired
    private String name;

    @Autowired
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = findViewById(R.id.textView);
        IntentInject.inject(this);

        tv.setText("name:" + name + ",gender:" + (gender == 1 ? "男" : "女"));
    }
}