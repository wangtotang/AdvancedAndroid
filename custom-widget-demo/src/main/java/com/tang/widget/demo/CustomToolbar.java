package com.tang.widget.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * CREATE BY Tanghongtu 2020/8/2
 */
public class CustomToolbar extends RelativeLayout {

    private TextView tvTitle;
    private ImageView leftImage, rightImage;

    public CustomToolbar(Context context) {
        this(context, null);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        tvTitle = new TextView(getContext());

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar);

        String title = typedArray.getString(R.styleable.CustomToolbar_title);
        tvTitle.setText(title);
        float titleTextSize = typedArray.getDimension(R.styleable.CustomToolbar_titleTextSize, 16);
        tvTitle.setTextSize(titleTextSize);
        int titleTextColor = typedArray.getColor(R.styleable.CustomToolbar_titleTextColor, Color.BLACK);
        tvTitle.setTextColor(titleTextColor);

        leftImage = new ImageView(getContext());
        rightImage = new ImageView(getContext());
        Drawable leftDrawable = typedArray.getDrawable(R.styleable.CustomToolbar_leftImageSrc);
        Drawable rightDrawable = typedArray.getDrawable(R.styleable.CustomToolbar_rightImageSrc);

        leftImage.setImageDrawable(leftDrawable);
        rightImage.setImageDrawable(rightDrawable);

        LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(tvTitle, titleParams);

        LayoutParams leftImageParams = new LayoutParams(30, 30);
        addView(leftImage, leftImageParams);

        LayoutParams rightImageParams = new LayoutParams(30, 30);
        addView(rightImage, rightImageParams);

        typedArray.recycle();
    }


}
