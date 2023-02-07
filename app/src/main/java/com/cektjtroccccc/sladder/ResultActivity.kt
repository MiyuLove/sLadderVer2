package com.cektjtroccccc.sladder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cektjtroccccc.sladder.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var boomer = intent.getIntExtra("BOOMER",0)

        binding.atariNumber.text = boomer.toString()

        binding.replayBtn.setOnClickListener({
            startActivity(Intent(this,ActivityLadderSetting::class.java))
            finish()
        })
        binding.menuButton.setOnClickListener({
            finish()
        })
    }
}