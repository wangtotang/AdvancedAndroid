package com.tang.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinearDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private int mOrientation = RecyclerView.VERTICAL;
    private Drawable divider;
    private int weight = 1;

    public LinearDecoration(Context context, int orientation) {
        mOrientation = orientation;
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        divider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    public void setDivider(Drawable divider) {
        this.divider = divider;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation == RecyclerView.HORIZONTAL) {
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();


        for (int i = 0; i < parent.getChildCount(); i++) {

        }

        int top = 0;
        int bottom = top + weight;

        divider.setBounds(left, top, right, bottom);
        divider.draw(c);

        divider.setBounds(left, top + 10, right, bottom + 10);
        divider.draw(c);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {

    }
}
