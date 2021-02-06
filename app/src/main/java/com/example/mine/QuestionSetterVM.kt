package com.example.mine

import android.util.Log
import androidx.lifecycle.ViewModel

class QuestionSetterVM : ViewModel() {
    var qNo : Int = 0

    fun sending(num : Int) {
        qNo = num
    }
}