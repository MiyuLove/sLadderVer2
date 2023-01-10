package com.cektjtroccccc.sladder

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.cektjtroccccc.sladder.databinding.ActivitySettingBinding

class ActivitySetting : AppCompatActivity() {
    lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        var t = ArrayList<TextView>()
        setContentView(binding.root)
        for(i in 0..5) {
            t.add(TextView(applicationContext))
            t[i].viewCopy(10.0+i)
            binding.listLayout.addView(t[i])
        }



    }
    fun TextView.viewCopy(bLine : Double){
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        this.text = bLine.toString()
        this.textSize = 20f
        this.layoutParams = param
        this.setBackgroundColor(Color.parseColor("#03dac5"))
        this.visibility = View.VISIBLE
    }

}