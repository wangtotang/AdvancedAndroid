package com.tang.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var animationSet = AnimationSet(false)
    }
}
