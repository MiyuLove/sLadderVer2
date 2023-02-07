package com.cektjtroccccc.sladder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout

class movingView(context: Context, w : Int,  h : Int) : View(context) {
    private val param = LinearLayout.LayoutParams(w, h)
    public var line = 0
    public var hX = 0
    public var moveLimit = false

    init{
        layoutParams = param
        setBackgroundColor(Color.parseColor("#03dac5"))
        visibility = VISIBLE
    }

    public fun setMargin(l : Int, t : Int, r : Int, b : Int){
        param.setMargins(l,t,r,b)
    }

    public fun movement(d : Int, x: Int){//1이 오른쪽 2가 밑 3이 왼 4가 위
        if(d == 1)asasX(x)
        else if(d == 2)asasY(x)
        else if(d == 3)asamX(x)
        else asamY(x)
    }

    public fun setMove(t : Boolean){
        moveLimit = t
    }

    public fun asasX(x : Int){
        translationX += x
    }
    public fun asamX(x : Int){
        translationX -=x
    }
    public fun asasY(x : Int){
        translationY += x
    }
    public fun asamY(x : Int){
        translationY -=x
    }
}