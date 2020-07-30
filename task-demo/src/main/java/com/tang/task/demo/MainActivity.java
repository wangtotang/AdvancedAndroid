package com.tang.task.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                        intent.putExtra("bean", new Bean());
                        startActivity(intent);
                    }
                });
    }

    static class Bean implements Serializable {
        private transient byte[] data = new byte[1024 * 1024];
        String str = "data string";
    }
}
