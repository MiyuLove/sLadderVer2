package com.cektjtroccccc.sladder

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.LinearLayout
import com.cektjtroccccc.sladder.databinding.ActivityLadderStartBinding

class ActivityLadderStart : AppCompatActivity() {
    lateinit var binding : ActivityLadderStartBinding
    private var w : Int = 0
    private var h : Int = 0
    private var bLine = 0.0
    private var ladderBaseLine : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLadderStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDisplay(0.9f,0.9f)//ratio - 0.9
        binding.listLayout.setCanvasWH()//setting

        var num = intent.getIntExtra("key",2)
        makeLadder(num)

        binding.finishBtn.setOnClickListener({
            finish()
        })
    }

    private fun makeLadder(num : Int){
        if(num == 1){
            binding.listLayout.addView(makeLadderBase(num)[0])
            return
        }

        var lineBase = makeLadderBase(num)
        val LRstep = makeStepCanvas(num-1)
        binding.listLayout.addView(lineBase[0])
        for(i in 1 until num){
            binding.listLayout.addView(LRstep[i-1])
            binding.listLayout.addView(lineBase[i])
        }
    }

    private fun makeLadderBase(num : Int) : ArrayList<View>{
        val line = arrayListOf<View>()
        ladderBaseLine = (w*0.06).toInt()
        bLine = (w*0.9)/(num-1)-ladderBaseLine+2

        for(i in 0 until num){
            line.add(View(applicationContext))
            line[i].viewCopy(bLine,ladderBaseLine,(h*0.8).toInt())
        }
        line[0].viewCopy((ladderBaseLine/3).toDouble(),ladderBaseLine,(h*0.8).toInt())
        return line
    }

    private fun makeStepCanvas(n : Int):ArrayList<LinearLayout>{
        val LR = arrayListOf<LinearLayout>()
        val stepLine = makeStepMargin(n)//2DArrayList

        for(i in 0 until n){
            LR.add(LinearLayout(applicationContext))
            LR[i].baseSet()
        }

        return LR
    }

    fun LinearLayout.baseSet(){
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        this.setOrientation(LinearLayout.VERTICAL)
        this.layoutParams = param
        this.setLineW(bLine.toInt())
        this.setLineH((h*0.8).toInt())

        this.setBackgroundColor(Color.WHITE)
        this.visibility = View.VISIBLE
    }

    private fun makeStepMargin(n : Int) : ArrayList<ArrayList<Int>>{
        val randNum = arrayListOf<makeColumnRandom>()
        randNum.add(makeColumnRandom())

        var i = 0
        if(n!=1)while(true){
            val mcr = makeColumnRandom()
            if(mcr.isSameNumber(randNum[i].colArrayTop,mcr.colArrayTop))
                continue

            if(mcr.isSameNumber(randNum[i].colArrayMid,mcr.colArrayMid))
                continue

            if(mcr.isSameNumber(randNum[i].colArrayBtm,mcr.colArrayBtm))
                continue

            i ++
            randNum.add(mcr)
            if(i == n-1)
                break
        }
        var r = arrayListOf<ArrayList<Int>>()
        for(x in randNum){
            val rtn = arrayListOf<Int>()
            for(y in x.colArrayTop)
                rtn.add(y)
            for(y in x.colArrayMid)
                rtn.add(y)
            for(y in x.colArrayBtm)
                rtn.add(y)
            r.add(rtn)
        }
        return r
    }

    fun View.viewCopy(bLine : Double,ladderW : Int, ladderH : Int){
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        this.layoutParams = param
        this.setLineW(ladderW)
        this.setLineH(ladderH)

        this.setBackgroundColor(Color.parseColor("#03dac5"))
        this.visibility = View.VISIBLE
    }

    private fun dd():String{//Debug code
        return "cektjtro123 : "
    }

    private fun View.setCanvasWH(){
        val lp = layoutParams
        lp?.let{
            lp.width = w
            lp.height = h
        }
    }

    private fun View.setLineW(a : Int){
        layoutParams?.let{
            layoutParams.width = a
        }
    }

    private fun View.setLineH(a : Int){
        layoutParams?.let{
            layoutParams.height = a
        }
    }

    private fun setDisplay(a:Float, b:Float){
        w = (resources.displayMetrics.widthPixels*a).toInt()
        h = (resources.displayMetrics.heightPixels*b).toInt()
    }//making the value of w,h(whole size of display to ratio a,b)and setting a area of the new canvas
}