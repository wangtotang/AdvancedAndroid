package com.tang.classloader.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

public class MainActivity extends AppCompatActivity {

    ISay say;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClassLoader classLoader = MainActivity.class.getClassLoader();
        System.out.println(classLoader.toString());

        View btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //热修复,不支持模拟器
                final File jarFile = new File("storage/self/primary/say_hotfix.jar");
                if (!jarFile.exists()) {
                    say = new SayException();
                    Toast.makeText(MainActivity.this, say.saySomething(), Toast.LENGTH_SHORT).show();
                }else{
                    DexClassLoader dexClassLoader = new DexClassLoader(jarFile.getAbsolutePath(), getExternalCacheDir().getAbsolutePath(), null, getClassLoader());
                    try {
                        Class clazz = dexClassLoader.loadClass("com.tang.classloader.demo.SayHotFix");
                        ISay iSay = (ISay) clazz.newInstance();
                        Toast.makeText(MainActivity.this, iSay.saySomething(), Toast.LENGTH_SHORT).show();
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
