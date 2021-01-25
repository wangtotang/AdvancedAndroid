package com.tang.animationdemo

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        customAnimation()
//        systemAnimation()
        layoutAnimation()
    }

    fun click(view: View) {
//        tv.startAnimation(animation)
//        frameAnimation()
        startActivity(Intent(this, SecondActivity::class.java))
//        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit)
    }

    fun layoutAnimation(){
        val loadAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_item)
        val layoutAnimationController = LayoutAnimationController(loadAnimation)
        layoutAnimationController.delay = 0.5f
        layoutAnimationController.order = LayoutAnimationController.ORDER_NORMAL
        constraintLayout.layoutAnimation = layoutAnimationController
    }

    fun frameAnimation(){
        tv.setBackgroundResource(R.drawable.my_frame_anim)
        (tv.background as AnimationDrawable).start()
    }

    fun systemAnimation(){
        animation = AnimationUtils.loadAnimation(this, R.anim.my_view_anim)
    }

    fun customAnimation(){
        animation = Rotate3DAnimation(0f, 90f, 0f, 0f, 10f, true)
        animation.duration = 6000
        animation.fillAfter = true
    }
}
