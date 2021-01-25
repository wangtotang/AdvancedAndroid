package com.tang.animationdemo;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3DAnimation extends Animation {

    private float mFromDegrees;
    private float mToDegrees;
    private float mCenterX;
    private float mCenterY;
    private float mDepthZ;
    private boolean mReverse;
    private Camera mCamera;

    public Rotate3DAnimation(float fromDegrees, float toDegrees, float centerX, float centerY,
                             float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
    }

    /**
     * initialize初始化
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    /**
     * 矩阵变化，借助Camera实现简单化
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        //对于频繁调用的方法，将成员赋值给局部final变量，是一种极端优化，可以使得编译器优化，并且取值会从缓存中取出
        final float fromDegrees = mFromDegrees;
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final float depthZ = mDepthZ;
        final Camera camera = mCamera;
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
        final Matrix matrix = t.getMatrix();
        camera.save();
//        if (mReverse) {
//            camera.translate(0.0f, 0.0f, depthZ * interpolatedTime);
//        }else{
//            camera.translate(0.0f, 0.0f, depthZ * (1 - interpolatedTime));
//        }
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();

//        matrix.preTranslate(-centerX, -centerY);
//        matrix.postTranslate(centerX, centerY);
    }
}
