package com.cektjtroccccc.sladder

import java.util.*
/*Debugging
fun main(){
    var q = makeColumnRandom()
    var a = q.colArrayTop
    var b = q.colArrayMid
    var c = q.colArrayBtm
    a.sort()
    b.sort()
    c.sort()
    println(Arrays.toString(a))
    println(Arrays.toString(b))
    println(Arrays.toString(c))
}*/

class makeColumnRandom constructor(){
    public lateinit var colArrayTop : Array<Int>
    public lateinit var colArrayMid : Array<Int>
    public lateinit var colArrayBtm : Array<Int>
    private lateinit var makeArrayRandom : Array<Int>

    public var topStart = 10
    public var topSize = 10
    public var midStart = 20
    public var midSize = 40
    public var btmStart = 75
    public var btmSize = 15

    init{
        makeArrayRandom = Array(3){0}
        makeArrayRandom[0] = (1..2).random()
        makeArrayRandom[1] = (3..5).random()
        makeArrayRandom[2] = (1..2).random()

        println(makeArrayRandom[1])
        colArrayTop = Array(makeArrayRandom[0]){0}
        colArrayMid = Array(makeArrayRandom[1]){0}
        colArrayBtm = Array(makeArrayRandom[2]){0}
        shuffle()
        //Made each area's numbers of Random bar and initializing
    }
    private fun shuffle(){
        //the new bar's position of Y
        suffleTop()
        suffleMid()
        suffleBtm()
    }

    //Large Shuffle
    private fun suffleRand(randSize : Int, startNum : Int, count : Int):Array<Int>{//for not similar random number in Array
        var randomNumbers = Array(randSize){0}
        var cnt = count
        var rtnArray = Array(cnt){0}

        while(cnt > 0){
            cnt --
            var r = (startNum until startNum+randSize).random()
            randomNumbers[r-startNum]++
            rtnArray[cnt] = r
            for(i in 0 until randSize){
                if(randomNumbers[i] == 2){
                    randomNumbers[i] --
                    cnt ++
                }
            }
        }
        if(count == 1)return rtnArray

        rtnArray.sort()
        var areaNum = rtnArray[0]
        for(i in 1 until count){
            if(rtnArray[i] - areaNum < 4)
                rtnArray = suffleRand(randSize, startNum, count)//if not Assignment in rtnArray,
            //all disappear in heap Area ㅡㅡ...

            areaNum = rtnArray[i]
        }
        return rtnArray
    }

    //These will be used in ActivityLadderStart when the its random number is same to the other Object's
    public fun suffleTop(){
        colArrayTop = suffleRand(topSize,topStart,makeArrayRandom[0])
    }

    public fun suffleMid(){
        colArrayMid = suffleRand(midSize,midStart,makeArrayRandom[1])
    }

    public fun suffleBtm(){
        colArrayBtm = suffleRand(btmSize,btmStart,makeArrayRandom[2])
    }

    //Detailed Shuffle
    public fun isSameNumber(a : Array<Int>, b : Array<Int>):Boolean{
        var numbers = Array(200){false}
        for(x in a)
            numbers[x] = true
        for(x in b)
            if(numbers[x]) return true
        return false
    }
}