package com.tang.annotation_reflect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toSecond(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", "tang");
        intent.putExtra("gender", 1);
        startActivity(intent);
    }
}