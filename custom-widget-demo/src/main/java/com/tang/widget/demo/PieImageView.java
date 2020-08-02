package com.tang.widget.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * CREATE BY Tanghongtu 2020/8/2
 */
public class PieImageView extends View {

    Paint mArcPaint, mCirclePaint;
    RectF mBound;

    public PieImageView(Context context) {
        this(context, null);
    }

    public PieImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {



    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }


    @Override
    protected void onDraw(Canvas canvas) {

    }
}
