package com.example.mine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizUiFactory(private val chapname : String,private val questionNumber : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizUiVM::class.java)) {
            return QuizUiVM(chapname, questionNumber) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}