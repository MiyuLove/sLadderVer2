package com.cektjtroccccc.sladder

import android.app.Application


public class MngApp :Application() {
    init {
        inst = this
    }
    public var horseSpeedInMng :Long = 3
    public var horseSetSppedInMng : Long = 3
    public var boomerNumber = 0
    override fun onCreate() {
        super.onCreate()
    }

    companion object{
        lateinit var inst : MngApp
    }

    public fun setHorseSpeed(a : Long){
        println("cektjtro123 : speed" + horseSpeedInMng)
        println("cektjtro123 : setSpeed" + horseSetSppedInMng)
        horseSpeedInMng = 6 - a
        horseSetSppedInMng = a + 1
    }
}