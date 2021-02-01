package com.tang.animationdemo

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    var i = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun click(view: View){
//        translationY()
//        i++
//        argbEvaluator()
//        animatorSet()
//        animatorRes()
        viewWrapperAnimator()
//        performAnimation(btn, btn.width, 500)
    }

    /**
     * kotlin没有基本类型，所以每次碰到类型不一样的时候，都要进行类型转换
     *
     * 属性动画默认是从头开始，所以这里没设置延迟的话，第二次会看不到有任何变化
     */
    fun translationY(){
        ObjectAnimator.ofFloat(view, "translationY",  -view.height.toFloat() * i).start()
    }

    /**
     * ArgbEvaluator提供颜色缓慢过渡，即渐变
     */
    fun argbEvaluator(){
        val colorAnim = ObjectAnimator.ofInt(view, "backgroundColor", 0xffff8080.toInt(), 0xff8080ff.toInt())
        colorAnim.duration = 3000
        colorAnim.setEvaluator(ArgbEvaluator())
        colorAnim.repeatCount = ValueAnimator.INFINITE
        colorAnim.repeatMode = ValueAnimator.REVERSE
        colorAnim.start()
    }

    /**
     * rotation是以圆心为轴转动
     */
    fun animatorSet(){
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "rotationX", 0f, 360f),
                ObjectAnimator.ofFloat(view, "rotationY", 0f, 180f),
                ObjectAnimator.ofFloat(view, "rotation", 0f, -90f),
                ObjectAnimator.ofFloat(view, "translationX", 0f, 90f),
                ObjectAnimator.ofFloat(view, "translationY", 0f, 90f),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.5f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0.25f, 1f)
        )
        animatorSet.duration = 5 * 1000
        animatorSet.start()
    }

    fun animatorRes(){
        val loadAnimator = AnimatorInflater
                .loadAnimator(this, R.animator.animator_property) as AnimatorSet
        loadAnimator.setTarget(view)
        loadAnimator.start()
    }

    /**
     * s使用包装器类无法对view的初始状态设置，导致从0开始动画
     */
    fun viewWrapperAnimator(){
        val viewWrapper = ViewWrapper(btn)
        ObjectAnimator.ofInt(viewWrapper, "width", 500)
                .setDuration(3000)
                .start()
    }

    fun performAnimation(view: View, start: Int, end: Int){
        val ofInt = ValueAnimator.ofInt(1, 100)
        ofInt.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {

            val mEvaluator = IntEvaluator()

            override fun onAnimationUpdate(animation: ValueAnimator) {
                view.layoutParams.width = mEvaluator.evaluate(animation.animatedFraction, start, end)
                view.requestLayout()
            }
        })
        ofInt.setDuration(5000).start()
    }

    class ViewWrapper(val view: View) {

        fun getWidth(): Int{
            return view.layoutParams.width
        }

        fun setWidth(width: Int){
            view.layoutParams.width = width
            view.requestLayout()
        }

    }
}