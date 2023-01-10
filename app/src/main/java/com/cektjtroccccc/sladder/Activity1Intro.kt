package com.cektjtroccccc.sladder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cektjtroccccc.sladder.databinding.Activity1IntroBinding
import kotlin.concurrent.timer

class Activity1Intro : AppCompatActivity() {
    private lateinit var binding : Activity1IntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity1IntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var second = 0
        var second2 = 0
        var dp = 5f
        timer(period = 1){
            second ++
            runOnUiThread{
                if(second<1200){
                    binding.IntroText.textSize = dp + (second/20).toFloat()
                }
                else if(second == 1200) dp = dp + (second/20).toFloat()
                else{
                    second2 ++
                    binding.IntroText.textSize = dp - (second2/40).toFloat()
                }
            }
            if(second == 2000){
                cancel()
                acas()
            }
        }
    }

    fun acas(){
        startActivity(Intent(this,MenuActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        exitLadder()
    }
    fun exitLadder(){
        System.runFinalization()
        System.exit(0)
    }
}