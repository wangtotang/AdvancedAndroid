package com.tang.interceptor;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author tanghongtu
 * @date 2020/10/9
 */
public class MyViewGroup extends FrameLayout {

    public MyViewGroup(@NonNull Context context) {
        super(context);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("tang", "MyViewGroup dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("tang", "MyViewGroup onInterceptTouchEvent: " + ev.getAction());
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
//        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("tang", "MyViewGroup onTouchEvent: " + event.getAction());
        return true;
    }
}
