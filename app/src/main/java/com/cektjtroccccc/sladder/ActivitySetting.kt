package com.cektjtroccccc.sladder

import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.cektjtroccccc.sladder.databinding.ActivitySettingBinding
import java.util.jar.Attributes
import kotlin.concurrent.timer

class ActivitySetting : AppCompatActivity() {
    lateinit var binding : ActivitySettingBinding
    lateinit var t : Toast

    var horseSpeed : Long = 0
    var horseNumDefault = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        horseSpeed = MngApp.inst.horseSpeedInMng

        binding.speedInSetting.text = "-" + horseSpeed.toString() + "-"

        t = Toast.makeText(this,"속도 : " + horseSpeed.toString() + " 저장완료!",Toast.LENGTH_SHORT)
        binding.minusButtonInSetting.setOnClickListener({
            horseSpeedSet(-1)
        })

        binding.plusButtonInSetting.setOnClickListener({
            horseSpeedSet(1)
        })

        binding.ladderSetBtnInSetting.setOnClickListener({
            MngApp.inst.horseSpeedInMng = 6 - horseSpeed
            println("cektjtro123 : Setting HorseSpeed = " + horseSpeed)
            t.cancel()
            t.show()
        })
    }

    private fun horseSpeedSet(a : Int){
        if(horseSpeed + a < 0 || horseSpeed + a > 5)
            return

        horseSpeed+=a
        binding.speedInSetting.text = "-" + horseSpeed.toString() + "-"
        t.setText("속도 : " + horseSpeed.toString() + " 저장완료!")
    }
}