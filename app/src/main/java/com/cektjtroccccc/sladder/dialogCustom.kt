package com.cektjtroccccc.sladder

import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class dialogCustom(private val context : AppCompatActivity) {
    private lateinit var binding : ActivityLadderStart
    private val dlg = Dialog(context)

    fun show(content : String){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setCancelable(false)

        dlg.show()
    }
}