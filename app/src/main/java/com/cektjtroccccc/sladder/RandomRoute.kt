package com.cektjtroccccc.sladder

import java.lang.Math.abs
/*
val q = RandomRoute(2500, 40,4)

fun main(){
    q.stepLine = q.makeStepMargin(4)
    var newStep = q.makeHorseArea()
    var newRoute = q.searchRoute(newStep)

    var cnt = 0
    for(x in newRoute){
        println("line : " + cnt)
        cnt ++
        for(qqq in x){
            print(qqq.first )
            print(" ")
            print(qqq.second)
            print(" ")
            print(" ")
        }
        println()
    }
}*/
public class RandomRoute (_h : Int,_ladderBaseLine : Int ){
    lateinit var stepLine : ArrayList<ArrayList<Int>>
    var h = _h
    var ladderBaseLine = _ladderBaseLine

    public fun searchRoute(newStep : ArrayList<ArrayList<Int>>) : ArrayList<ArrayList<Pair<Int,Int>>>{
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
                if(fd < 0)line -- else line ++
                horseRoute[i].add(Pair(line,fd))
            }
            println()
        }
        return horseRoute
    }

    public fun makeHorseArea() : ArrayList<ArrayList<Int>>{
        var horseArea = ArrayList<ArrayList<Int>>()

        for(i in 0 until stepLine.size){
            horseArea.add(ArrayList())
            for (x in stepLine[i]) {
                horseArea[i].add(x * (h * 0.007).toInt() + (ladderBaseLine * 1.3).toInt())
            }

            if(i > 0) {
                for (x in stepLine[i - 1]) {
                    horseArea[i].add(-(x * (h * 0.007).toInt() + (ladderBaseLine * 1.3).toInt()))
                }
            }
        }//새로운 마진 값과 영역 설정
        horseArea.add(ArrayList())
        for (x in stepLine[stepLine.size-1]) {
            horseArea[stepLine.size].add(-(x * (h * 0.007).toInt() + (ladderBaseLine * 1.3).toInt()))
        }
        var cnt  = 0
        for(x in horseArea){
            print(dd() + cnt)
            cnt++
            print(" : ")
            for(y in x){
                print(y)
                print(" ")
            }
            println()
        }

        return horseArea
    }

    public fun makeStepMargin(n : Int) : ArrayList<ArrayList<Int>>{
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

    public fun dd():String{
        return "cektjtro123 : "
    }
}