package com.example.mine

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class QuizUiVM(chapname : String,questionNumber : Int) : ViewModel() {

    var questionNumber = questionNumber
    var chapname = chapname
    var mCurrentQuestion : Int = 0
    var mCorrectOption : Int? = null
    var correctAnswers : Int = 0

    private var _onGameFinish = MutableLiveData<Boolean>()
    val onGameFinish : LiveData<Boolean>
        get() = _onGameFinish

    init {
        _onGameFinish.value = false
    }
    var index = 0


    var running = true
    var min = 0
    var second = 0


    var pMax = 0
    var mQuestions = ArrayList<Questions>()
    var mTotalQuestions : Int = 0


    private fun randomGen(ran : Int) : ArrayList<Int>{
        var listt : ArrayList<Int> = ArrayList<Int>()
        var index = 0
//        Log.i("ran","$ran")
        while (index < ran){
//            Log.i("do","ke andar")
            var r = (1..mQuestions.size).random()
            if(!listt.contains(r)) {
//                Log.i("r","$r")
                listt.add(r)
                index++
//                Log.i("list size","${listt.size}")
            }
        }
        Log.i("list","$listt")
        return listt
    }

    fun essential() {
        if (index == 0) {
            theChecker()
        }
        when(questionNumber) {
            10 -> {
                mTotalQuestions = 10
                pMax =10
            }
            15 -> {
                mTotalQuestions = 15
                pMax = 15

            }
            20 -> {
                mTotalQuestions = 20
                pMax = 20
            }
        }
    }


    fun setter(mQ : ArrayList<Questions>) {
        mQuestions = mQ
        val randList : ArrayList<Int> = randomGen(questionNumber.toInt())
        val qList : ArrayList<Questions> = ArrayList(questionNumber.toInt()-1)
        for(items in randList) {
            qList.add(mQuestions[items-1])
        }
        mQuestions = qList
    }


    fun theChecker() {

        when(chapname) {
            "Interference And Diffraction" -> {
                    setter(Interfe.getQuestions())
            }
        }

    }

    fun onGameFinish() {
        Log.i("Ongamefinish ke","andar")
        _onGameFinish.value = true
    }


    fun onStop() {
        running = false
    }



    fun onRunning(tv : TextView) {
        if(running) {
            var x = "0$min:0$second"
            if (second >= 10) {
                x = "0$min:$second"

            }
            else if(min >= 10) {
                x = "$min:0$second"
            }
            else if(second >= 10 && min >= 10) {
                x = "$min:$second"
            }
            if (second == 59) {
                min++
                second = -1
            }
            second++
            Executors.newSingleThreadScheduledExecutor().schedule(
                    {
                        tv.text = x
                        onRunning(tv)
                    },
                    1, TimeUnit.SECONDS
            )
        }

    }




}