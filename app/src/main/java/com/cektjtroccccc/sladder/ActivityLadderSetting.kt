package com.cektjtroccccc.sladder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.cektjtroccccc.sladder.databinding.ActivityLadderSettingBinding

class ActivityLadderSetting : AppCompatActivity() {
    lateinit var binding : ActivityLadderSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLadderSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ladderSetButton.isEnabled = false
        val intent = Intent(this,ActivityLadderStart::class.java)
        val et1 = binding.ET1
        binding.btn68.setOnClickListener({
            var es1 : String = et1.text.toString()
            if(isInteger(es1)) {
                Toast.makeText(this, es1, Toast.LENGTH_SHORT).show()
                intent.putExtra("key", es1.toInt())
                binding.ladderSetButton.isEnabled = true
                startActivity(intent)
                finish()
            }
            else {
                binding.ladderSetButton.isEnabled = false
                Toast.makeText(this, es1, Toast.LENGTH_SHORT).show()
            }
        })
        binding.ladderSetButton.setOnClickListener({
            startActivity(intent)
            finish()
        })
    }

    fun isInteger(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}