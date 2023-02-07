package com.cektjtroccccc.sladder

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cektjtroccccc.sladder.databinding.ActivityLadderSettingBinding
import org.w3c.dom.Text

class ActivityLadderSetting : AppCompatActivity() {
    lateinit var binding : ActivityLadderSettingBinding
    lateinit var mtal : ArrayList<TextView>
    var num = 2
    var boom = 0
    var createSign = false
    private val clickNumColor = "#FFF8DC"
    private val prettyBackgroundColorSky = "#BAE8FF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLadderSettingBinding.inflate(layoutInflater)
        val intent = Intent(this,ActivityLadderStart::class.java)
        horseNumSet(0)
        mtal = makeArrayTextView()
        mtal[0].setBackgroundColor(Color.parseColor(clickNumColor))
        for (i in 0 until num) mtal[i].visibility = View.VISIBLE
        for (i in num until 6) mtal[i].visibility = View.INVISIBLE
        mtal[0].setOnClickListener({
            numClick(0)
        })
        mtal[1].setOnClickListener({
            numClick(1)
        })
        mtal[2].setOnClickListener({
            numClick(2)
        })
        mtal[3].setOnClickListener({
            numClick(3)
        })
        mtal[4].setOnClickListener({
            numClick(4)
        })
        mtal[5].setOnClickListener({
            numClick(5)
        })
        binding.minusButton.setOnClickListener ({
            horseNumSet(-1)
        })

        binding.plusButton.setOnClickListener({
            horseNumSet(1)
        })

        createSign = true
        binding.ladderSetButton.setOnClickListener({
            intent.putExtra("key", num)
            intent.putExtra("BOOM",boom)
            startActivity(intent)
            finish()
        })
        setContentView(binding.root)
    }

    fun horseNumSet(a : Int){
        if(num + a < 2 || num + a > 6)
            return

        num += a
        binding.horseNum.text = String.format("-%d-",num)
        if(createSign) {
            if(num <= boom){
                numClick(num-1)
            }
            for (i in 0 until num) mtal[i].visibility = View.VISIBLE
            for (i in num until 6) mtal[i].visibility = View.INVISIBLE
        }
    }

    private fun numClick(clickNum : Int){
        println(dd() + clickNum)
        for(i in 0 until mtal.size){
            if(i == 0 || i == 3 || i == 4)mtal[i].setBackgroundColor(Color.WHITE)
            else mtal[i].setBackgroundColor(Color.parseColor(prettyBackgroundColorSky))
        }
        mtal[clickNum].setBackgroundColor(Color.parseColor(clickNumColor))
        boom = clickNum
    }

    private fun dd():String{
        return "cektjtro123 : "
    }

    private fun makeArrayTextView():ArrayList<TextView>{
        val rtnAL = ArrayList<TextView>()
        rtnAL.add(binding.atariText1)//binding 친구들 string이나 key set 등으로 다루는 방법 좀 찾아보자 ,, ㅠ
        rtnAL.add(binding.atariText2)
        rtnAL.add(binding.atariText3)
        rtnAL.add(binding.atariText4)
        rtnAL.add(binding.atariText5)
        rtnAL.add(binding.atariText6)
        return rtnAL
    }
}