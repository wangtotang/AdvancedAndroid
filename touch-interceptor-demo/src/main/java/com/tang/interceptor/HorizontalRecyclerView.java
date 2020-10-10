package com.tang.interceptor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author tanghongtu
 * @date 2020/10/10
 *
 */
public class HorizontalRecyclerView extends RecyclerView {

    private float lastX;
    private float lastY;

    public HorizontalRecyclerView(@NonNull Context context) {
        super(context);
    }

    public HorizontalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean intercept = false;
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - lastX;
                float deltaY = y - lastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        return intercept;
    }
}
