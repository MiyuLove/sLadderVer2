package com.cektjtroccccc.sladder

import android.app.Application


public class MngApp :Application() {
    init {
        inst = this
    }
    public var horseSpeedInMng :Long = 3

    override fun onCreate() {
        super.onCreate()
    }

    companion object{
        lateinit var inst : MngApp
    }
}