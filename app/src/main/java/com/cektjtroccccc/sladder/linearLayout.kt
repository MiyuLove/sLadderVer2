package com.cektjtroccccc.sladder

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable.Orientation
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.setMargins

class linearLayout(context: Context, w : Int,  h : Int) : LinearLayout(context) {
    private val param = LayoutParams(w, h)

    init{
        param.setMargins(0,0,0,0)
        setOrientation(HORIZONTAL)
        layoutParams = param
        setBackgroundColor(Color.WHITE)
        visibility = VISIBLE

    }

    public fun setMargin(l : Int, t : Int, r : Int, b : Int){
        param.setMargins(l,t,r,b)
    }

    public fun setOrientationHorizontal(){
        setOrientation(HORIZONTAL)
    }

    public fun setOrientationVertical(){
        setOrientation(VERTICAL)
    }
}