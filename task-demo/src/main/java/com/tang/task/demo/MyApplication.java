package com.tang.task.demo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by tanghongtu on 2020/7/30.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyApplication", "onCreate in porcess: " + getProcessName(this));
    }

    private String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : am.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                if (processInfo.processName != null) {
                    return processInfo.processName;
                }
            }
        }
        return null;
    }
}
