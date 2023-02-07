package com.cektjtroccccc.sladder

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log.w
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.size
import com.cektjtroccccc.sladder.databinding.ActivityLadderStartBinding
import kotlinx.coroutines.NonCancellable.cancel
import java.lang.Math.abs
import kotlin.concurrent.timer

class ActivityLadderStart : AppCompatActivity() {
    lateinit var binding : ActivityLadderStartBinding
    lateinit var stepLine : ArrayList<ArrayList<Int>>//allocation in makeStepCanvas(Int) in makeLadder(Int)
    lateinit var newStep : ArrayList<ArrayList<Int>>
    lateinit var btn : Button
    private var w : Int = 0
    private var h : Int = 0
    private var bLine = 0.0
    private var ladderBaseLine : Int = 0
    private var timerExit = false

    private var boom = 0
    private var hn = 0
    private var boomer = 0

    private var horseTimer = MngApp.inst.horseSpeedInMng
    private var stopper = false
    private var btnLocker = false

    private var colorT = true
    private val horseColor = "#FFFFFF"
    private val ladderColor = "#BAE8FF"
    private val finalColor = "#FFFFFF"
    private val layoutColor = "#FFFFFF"
    private val sfColor = "#FFFFFF"
    private val btnColor = "#87CEEB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLadderStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDisplay(0.9f,0.9f)//ratio = 0.9

        var num = intent.getIntExtra("key",2)
        hn = num
        boom = intent.getIntExtra("BOOM",0)
        ladderBaseLine = (w*0.06).toInt()
        bLine = (w*0.9)/(num-1)//Object의 w값 만큼 빼줘야 합니다...-ladderBaseLine or - horseWH
        val horse = makeHorse(num)
        makeLadder(num)
        colorT = false
        movingHorse(horse)
        val resultHorse = makeHorse(num)

        btn = setButton(0,(h*0.12).toInt())
        binding.wholeLayout.addView(btn)

        btn.setOnClickListener({
            resultHorseGame()
        })
    }

    private fun resultHorseGame(){
        if(btnLocker)return
        stopper = true
        acas()
    }

    private fun acas(){
        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra("BOOMER",boomer)
        startActivity(intent)

        finish()
    }

    private fun setButton(bW : Int, bH : Int):Button{
        val rtnBtn = Button(applicationContext)
        val param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, bH)
        rtnBtn.setBackgroundColor(Color.parseColor(btnColor))
        rtnBtn.translationY += h*0.03f
        rtnBtn.text = "결 과 보 기"
        rtnBtn.setTextColor(Color.WHITE)
        rtnBtn.setTextSize(40f)
        rtnBtn.layoutParams = param

        return rtnBtn
    }

    private fun makeHorse(num : Int):ArrayList<movingText>{
        var horseBase = linearLayout(applicationContext,w,(h*0.05).toInt())
        horseBase.setBackgroundColor(Color.parseColor(sfColor))
        horseBase.setHorizontalGravity(1)
        horseBase.translationZ += 12
        horseBase.outlineProvider = null
        horseBase.setMargin((w*0.06).toInt(),(w*0.06).toInt(),(w*0.06).toInt(),0)
        var hC = ""
        if(colorT){
            hC = horseColor
        } else {
            horseBase.translationZ -=3
            hC = finalColor
        }

        val horse = arrayListOf<movingText>()
        val horseWH = (w*0.15).toInt()
        for(i in 0 until num) {
            var qt = (i+1).toString()
            if(i == boom && !colorT) {qt = "당"}
            horse.add(movingText(applicationContext,horseWH,horseWH,qt))

            horse[i].setMargin(bLine.toInt()-horseWH,0,0,0)
            horse[i].movingTextSize(w*0.035f)
            horse[i].background = ContextCompat.getDrawable(this,R.drawable.btn_bd)
            horseBase.addView(horse[i])
        }
        horse[0].setMargin(0,0,0,0)

        binding.wholeLayout.addView(horseBase)
        colorT = false
        return horse
    }

    private fun makeResult(AAPII:ArrayList<ArrayList<Pair<Int,Int>>>): ArrayList<Int>{
        val rtnAL = ArrayList<Int>()

        for(x in AAPII){
            var q = if(x[x.size-1].second > 0)x[x.size-1].first+1
                else x[x.size-1].first -1
            rtnAL.add(q)
        }
        var cnt = 1
        for(x in rtnAL){
            if(x == boom) {
                boomer = cnt
                break
            }
            cnt ++
        }
        return rtnAL
    }

    private fun movingHorse(horse : ArrayList<movingText>){
        var newRoute = searchRoute(newStep)
        makeResult(newRoute)
        var cnt = 0
        println(dd() + "this is new Route")
        for(x in newRoute){
            print(dd() + "line" + cnt)
            cnt ++
            print(" : ")
            for(qqq in x){
                print(qqq.first )
                print(" ")
                print(qqq.second)
                print(" ")
                print(" ")
            }
            println()
        }

        //val qt = Array(horse.size){true}
        var setter = Array(horse.size+3){false}
        var qupid = bLine.toInt()
        timer(period = horseTimer){
            cnt = 0
            var idx = 0

            for(x in horse){
                if(x.y.toInt() > h *0.81){
                    idx ++
                    cnt ++
                    if(horse.size == cnt){
                        println(dd() + "hi")
                        acas()
                        cancel()
                    }
                    continue
                }
                if(setter[idx]) {
                    x.movement(2, (h * 0.001f).toInt())
                    idx++
                    continue
                }
                if(idx >= newRoute.size) idx = newRoute.size-1//setter랑 cnt 수치 렉걸려서 작용 안되는 ... 있을 수 있는 최악의 상황
                if(idx < 0) idx =0

                if(x.y.toInt() >= abs(newRoute[idx][x.line].second) ){
                    val d = if (newRoute[idx][x.line].second > 0) 1 else 3
                    x.movement(d,(h*0.001f).toInt())
                    x.hX ++

                    if(x.hX >= qupid) {
                        x.hX = 0
                        x.movement(2, (h * 0.001f).toInt())
                        x.line ++
                        if(newRoute[idx].size <= x.line)setter[idx] = true
                    }
                }
                else x.movement(2,(h*0.001f).toInt())

//                if(x.y.toInt() >= abs(newRoute[idx][x.line].second))
  //                  qt[idx] = true

                idx ++
            }
            if(stopper)cancel()
        }
        return
    }

    fun searchRoute(newStep : ArrayList<ArrayList<Int>>) : ArrayList<ArrayList<Pair<Int,Int>>>{
        var horseRoute = ArrayList<ArrayList<Pair<Int,Int>>>()
        for(i in 0 until newStep.size){
            var line = i
            var fd = 0
            horseRoute.add(ArrayList())
            while(true){
                var minAL = ArrayList<Int>()
                for(x in newStep[line]){
                    if(abs(fd) < abs(x)){
                        minAL.add(x)
                    }
                }
                var min = 0
                for(x in minAL){
                    if(min == 0 || min > abs(x)) min = x
                }

                if(min == 0)break
                fd = min
                horseRoute[i].add(Pair(line,fd))
                if(fd < 0)line -- else line ++
            }
        }
        return horseRoute
    }

    private fun makeHorseArea() : ArrayList<ArrayList<Int>>{
        var horseArea = ArrayList<ArrayList<Int>>()
        for(i in 0 until stepLine.size){
            horseArea.add(ArrayList())
            var idx = 0
            for (x in stepLine[i]) {
                idx ++
                horseArea[i].add(x * (h * 0.009).toInt())
            }

            if(i > 0) {
                for (x in stepLine[i - 1]) {
                    horseArea[i].add(-(x *  (h * 0.009).toInt()))
                }
            }
        }

        horseArea.add(ArrayList())
        for (x in stepLine[stepLine.size-1]) {
            horseArea[stepLine.size].add(-(x *  (h * 0.009).toInt()))
        }
        return horseArea
    }

    private fun makeLadder(num : Int){//ladderBase - ladderStep - ... - ladderBase on the ladderCanvas
        val ladderCanvas = linearLayout(applicationContext,w,(h*0.8).toInt())
        ladderCanvas.setBackgroundColor(Color.parseColor(layoutColor))
        ladderCanvas.setMargin((w*0.06).toInt(),0,(w*0.06).toInt(),0)
        ladderCanvas.setHorizontalGravity(1)

        if(num == 1){//prohibit null error
            ladderCanvas.addView(makeLadderBase(num)[0])
            binding.wholeLayout.addView(ladderCanvas)
            return
        }

        var lineBase = makeLadderBase(num)
        val LRstep = makeStepCanvas(num-1)
        ladderCanvas.addView(lineBase[0])

       for(i in 1 until num){
            ladderCanvas.addView(LRstep[i-1])
            ladderCanvas.addView(lineBase[i])
       }

        binding.wholeLayout.addView(ladderCanvas)
    }

    private fun makeLadderBase(num : Int) : ArrayList<movingView>{
        val line = arrayListOf<movingView>()
        if(num == 1)bLine = 1.0

        for(i in 0 until num) {
            line.add(movingView(applicationContext, ladderBaseLine, (h * 0.85).toInt()))
            line[i].setBackgroundColor(Color.parseColor(ladderColor))
        }

        return line
    }

    private fun makeStepCanvas(n : Int):ArrayList<linearLayout>{
        val LR = arrayListOf<linearLayout>()

        stepLine = makeStepMargin(n)//2DArrayList
        newStep = makeHorseArea()

        for(i in 0 until n){
            LR.add(linearLayout(applicationContext,bLine.toInt()-ladderBaseLine,(h*0.85).toInt()))
            LR[i].setBackgroundColor(Color.parseColor(layoutColor))
            LR[i].clipChildren = false
            LR[i].setOrientationVertical()
            val mov = arrayListOf<movingView>()
            for(x in 0 until newStep[i].size){
                if(newStep[i][x] < 0)
                    break

                mov.add(movingView(applicationContext,bLine.toInt()-ladderBaseLine,ladderBaseLine))
                mov[x].setMargin(0,-ladderBaseLine,0,0)
                mov[x].setBackgroundColor(Color.parseColor(ladderColor))
                LR[i].addView(mov[x])
            }

        //step col margin
            for(x in 0 until newStep[i].size){
                if(newStep[i][x] < 0)
                    break
                 mov[x].translationY = newStep[i][x].toFloat()
            }
        }
        return LR
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

    public fun dd():String{//Debug code
        return "cektjtro123 : "
    }

    private fun setDisplay(a:Float, b:Float){
        w = (resources.displayMetrics.widthPixels*a).toInt()
        h = (resources.displayMetrics.heightPixels*b).toInt()
    }
//making the value of w,h(whole size of display to ratio a,b)and setting a area of the new canvas

    override fun onBackPressed() {
        stopper = true
        timerExit = true
        finish()
    }
}