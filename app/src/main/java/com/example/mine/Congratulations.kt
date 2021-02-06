package com.example.mine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Congratulations : AppCompatActivity() {
    var chapName : String? = null
    var corrAns : Int = 0
    var tQuestions : Int = 0

    var name  : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulations)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val score_displayer = findViewById<TextView>(R.id.score_displayer)
        val finish_btn = findViewById<Button>(R.id.finish_btn)

        chapName = intent.getStringExtra("chapName")

        tQuestions = intent.getIntExtra(PhyChapters.tQue,10)
        textView2.text = "$chapName "

        corrAns = intent.getIntExtra("mCorrectAnswers",0)
        score_displayer.text = "Your score is : ${corrAns}/$tQuestions"

        val intent = Intent(this, MainActivity::class.java)

        finish_btn.setOnClickListener {
            startActivity(intent)
            finish()
        }



    }
}