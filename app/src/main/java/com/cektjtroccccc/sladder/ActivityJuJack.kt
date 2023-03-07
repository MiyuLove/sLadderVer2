package com.cektjtroccccc.sladder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cektjtroccccc.sladder.databinding.ActivityJuJackBinding

class ActivityJuJack : AppCompatActivity() {
    lateinit var binding : ActivityJuJackBinding
    lateinit var t : Toast
    var goodNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuJackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goodNum = MngApp.inst.boomerNumber

        binding.speedInSetting.text = "-" + (if(goodNum == 0) "X" else goodNum.toString() )+ "-"

        t = Toast.makeText(this,"번호 : " + (if(goodNum == 0) "X" else goodNum.toString() ) + " 저장완료!",Toast.LENGTH_SHORT)
        binding.minusButtonInSetting.setOnClickListener({
            goodNumSet(-1)
        })

        binding.plusButtonInSetting.setOnClickListener({
            goodNumSet(1)
        })

        binding.ladderSetBtnInSetting.setOnClickListener({
            MngApp.inst.boomerNumber = goodNum
            t.setText("번호 : " + (if(goodNum == 0) "X" else goodNum.toString() ) + " 저장완료!")
            t.cancel()
            t.show()
        })
    }

    private fun goodNumSet(a : Int){
        if(goodNum + a < 0 || goodNum + a > 6)
            return

        goodNum+=a
        binding.speedInSetting.text = "-" + (if(goodNum == 0) "X" else goodNum.toString() )+ "-"
        println("cektjtro123 : " + MngApp.inst.boomerNumber)
    }
}