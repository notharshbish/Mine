package com.example.mine

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

class QuestionSetter : AppCompatActivity() {
    var chapterName : String = "chapter_name"

    private lateinit var viewmodel : QuestionSetterVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_setter)

        Log.i("In question","setter")

        chapterName = intent.getStringExtra(PhyChapters.chapName)!!

        viewmodel = ViewModelProvider(this).get(QuestionSetterVM::class.java)


        val ten_btn = findViewById<Button>(R.id.ten_btn)
        val fifteen_btn = findViewById<Button>(R.id.fifteen_btn)
        val twenty_btn = findViewById<Button>(R.id.twenty_btn)

        ten_btn.setOnClickListener {
            viewmodel.sending(10)
            forward()
        }
        fifteen_btn.setOnClickListener {
            viewmodel.sending(15)
            forward()
        }
        twenty_btn.setOnClickListener {
            viewmodel.sending(20)
            forward()
        }

    }



    private fun forward() {
        val intent = Intent(this, QuizUiProgram::class.java)
        Log.i("forward","mein hu")
        intent.putExtra("QuestionNumber",viewmodel.qNo)
        Log.i("viewmodel qno","${viewmodel.qNo}")
        intent.putExtra(PhyChapters.chapName,chapterName)
        Log.i("chapName","$chapterName")
        startActivity(intent)
        finish()
    }
}