package com.cektjtroccccc.sladder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.cektjtroccccc.sladder.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding : ActivityMenuBinding
    var exit_btn = 0
    var back_btn = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener({
            startActivity(Intent(this,ActivityLadderSetting::class.java))
        })

        binding.ladderSetButton.setOnClickListener({
            startActivity(Intent(this,ActivitySetting::class.java))
        })

        binding.exitButton.setOnClickListener({
            Toast.makeText(this,"종료 버튼을 한번 더\n눌러주세요", Toast.LENGTH_SHORT).show()
            exit_btn ++
            if(exit_btn >=2)
                exitLadder()

            Handler().postDelayed({
                exit_btn = 0
            },4000)
        })
    }

    fun exitLadder(){
        System.runFinalization()
        System.exit(0)
    }

    override fun onBackPressed() {
        Toast.makeText(this,"뒤로 가기 버튼을 한번 더\n눌러주세요", Toast.LENGTH_SHORT).show()
        //Toast.makeText(this,"Press One More Back Button\nfor exit this APP", Toast.LENGTH_SHORT).show()
        back_btn ++
        if(back_btn >=2)
            exitLadder()
        Handler().postDelayed({
            back_btn = 0
        },4000)
    }
}