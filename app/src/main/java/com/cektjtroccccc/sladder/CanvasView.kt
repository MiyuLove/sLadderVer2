package com.cektjtroccccc.sladder

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout

class CanvasView (context: Context) : LinearLayout(context) {
    var w = 0
    var h = 0
    private lateinit var param : LayoutParams

    init{
        setDisplay(0.95f,0.95f)
        param.setMargins(0,0,0,0)

        setOrientation(HORIZONTAL)
        layoutParams = param
        setBackgroundColor(Color.WHITE)
        visibility = VISIBLE

    }

    public fun setCanvasColor(colorc : String){
        setBackgroundColor(Color.parseColor("#87CEEB"))
    }

    public fun setOrientationHorizontal(){
        setOrientation(HORIZONTAL)
    }

    public fun setOrientationVertical(){
        setOrientation(VERTICAL)
    }

    private fun setDisplay(a:Float, b:Float){
        w = (resources.displayMetrics.widthPixels*a).toInt()
        h = (resources.displayMetrics.heightPixels*b).toInt()
        param = LayoutParams(w,h)
    }
}