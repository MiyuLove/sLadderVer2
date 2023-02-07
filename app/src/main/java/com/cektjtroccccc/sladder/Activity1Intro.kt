package com.cektjtroccccc.sladder

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cektjtroccccc.sladder.databinding.Activity1IntroBinding
import kotlin.concurrent.timer

class Activity1Intro : AppCompatActivity() {
    private lateinit var binding : Activity1IntroBinding
    private val duration : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity1IntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.IntroImage.animate()
            .alpha(1f)
            .setDuration(1500)
            .withEndAction{acas()}
    }

    fun acas(){
        startActivity(Intent(this,MenuActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        System.runFinalization()
        System.exit(0)
    }
}