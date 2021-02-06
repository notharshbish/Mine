package com.example.mine

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class QuizUiProgram : AppCompatActivity() , View.OnClickListener{
    var chapName : String? = null
    var qNo : Int? = null



    private lateinit var viewmodel : QuizUiVM

     private lateinit var viewmodelfactory : QuizUiFactory


    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_ui)
        chapName = intent.getStringExtra(PhyChapters.chapName)
        qNo = intent.getIntExtra("QuestionNumber",15)


        viewmodelfactory = QuizUiFactory(chapName!!, qNo!!)
        viewmodel = ViewModelProvider(this, viewmodelfactory).get(QuizUiVM::class.java)

        viewmodel.onGameFinish.observe(this , Observer<Boolean>{ gameFinish ->
            if (gameFinish) forwardWeGo()
        })


//        viewmodel.running = false

        val option_one : RadioButton= findViewById(R.id.option_one)
        val option_two : RadioButton = findViewById(R.id.option_two)
        val option_three : RadioButton = findViewById(R.id.option_three)
        val option_four : RadioButton = findViewById(R.id.option_four)
        val stopwatch = findViewById<TextView>(R.id.stopwatch)


        setQuestion()

        option_one.setOnClickListener(this)
        option_two.setOnClickListener(this)
        option_three.setOnClickListener(this)
        option_four.setOnClickListener(this)
    }



    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        Log.i("Set Question","ke andar")
        val stopwatch = findViewById<TextView>(R.id.stopwatch)
//        viewmodel.running = true
        viewmodel.onRunning(stopwatch)
        viewmodel.essential()

        val questionNoDisplayer = findViewById<TextView>(R.id.question_no_displayer)
        val progressBar : ProgressBar = findViewById(R.id.progressBar)
        val progressView : TextView= findViewById(R.id.progressView)
        val option_one : RadioButton= findViewById(R.id.option_one)
        val option_two : RadioButton = findViewById(R.id.option_two)
        val option_three : RadioButton = findViewById(R.id.option_three)
        val option_four : RadioButton = findViewById(R.id.option_four)
        val question_tag : TextView = findViewById(R.id.question_tag)


        progressBar.max = viewmodel.pMax



        defaultDisplay()


        progressBar.progress = viewmodel.mCurrentQuestion
        progressView.text = "${viewmodel.mCurrentQuestion}/${viewmodel.mTotalQuestions}"

        if(viewmodel.mCurrentQuestion < viewmodel.mQuestions.size){
            Log.i("If ke andar","")
            questionNoDisplayer.text = "Q.${viewmodel.mCurrentQuestion+1}) "
            question_tag.text = "${viewmodel.mQuestions[viewmodel.mCurrentQuestion].question}"
            option_one.text = viewmodel.mQuestions[viewmodel.mCurrentQuestion].choice1
            option_two.text = viewmodel.mQuestions[viewmodel.mCurrentQuestion].choice2
            option_three.text = viewmodel.mQuestions[viewmodel.mCurrentQuestion].choice3
            option_four.text = viewmodel.mQuestions[viewmodel.mCurrentQuestion].choice4
            viewmodel.index++
        }else{
            forwardWeGo()
        }

    }



    private fun forwardWeGo() {
        val intent = Intent(this, Congratulations::class.java)
        intent.putExtra("chapName",chapName)
        intent.putExtra("mCorrectAnswers",viewmodel.correctAnswers)
        intent.putExtra(PhyChapters.tQue,viewmodel.mTotalQuestions)
        startActivity(intent)
        finish()
    }

    private fun defaultDisplay() {


        val option_one : RadioButton= findViewById(R.id.option_one)
        val option_two : RadioButton = findViewById(R.id.option_two)
        val option_three : RadioButton = findViewById(R.id.option_three)
        val option_four : RadioButton = findViewById(R.id.option_four)


        val radioButtonList = ArrayList<RadioButton>()
        radioButtonList.add(option_one)
        radioButtonList.add(option_two)
        radioButtonList.add(option_three)
        radioButtonList.add(option_four)

        for(option in radioButtonList) {
            option.background = ContextCompat.getDrawable(this, R.drawable.default_bg_color)
        }
        option_one.isChecked = true
        option_two.isChecked = false
        option_three.isChecked = false
        option_four.isChecked = false
    }

    override fun onClick(v: View?) {

        viewmodel.onStop()
        viewmodel.min = 0
        viewmodel.second = 0
        val option_one : RadioButton= findViewById(R.id.option_one)
        val option_two : RadioButton = findViewById(R.id.option_two)
        val option_three : RadioButton = findViewById(R.id.option_three)
        val option_four : RadioButton = findViewById(R.id.option_four)


        when(v?.id) {
            R.id.option_one -> {
                selectedOption(option_one,1)
            }

            R.id.option_two -> {
                selectedOption(option_two,2)
            }

            R.id.option_three -> {
                selectedOption(option_three,3)
            }

            R.id.option_four -> {
                selectedOption(option_four,4)
            }

        }

    }





    private fun selectedOption(r : RadioButton, mSelectedOptionNum : Int) {
        val option_one : RadioButton= findViewById(R.id.option_one)
        val option_two : RadioButton = findViewById(R.id.option_two)
        val option_three : RadioButton = findViewById(R.id.option_three)
        val option_four : RadioButton = findViewById(R.id.option_four)
        val stopwatch = findViewById<TextView>(R.id.stopwatch)


        viewmodel.mCorrectOption = viewmodel.mQuestions[viewmodel.mCurrentQuestion].correctChoice

        when(viewmodel.mCorrectOption) {

            1 -> {
                option_one.setBackgroundColor(Color.GREEN)
            }

            2 -> {
                option_two.setBackgroundColor(Color.GREEN)
            }

            3 -> {
                option_three.setBackgroundColor(Color.GREEN)
            }

            4 -> {
                option_four.setBackgroundColor(Color.GREEN)

            }
        }



        if(viewmodel.mCurrentQuestion<= viewmodel.mQuestions.size ) {

            if(mSelectedOptionNum != viewmodel.mCorrectOption) {
                r.setBackgroundColor(Color.RED)
            }
            else {
                viewmodel.correctAnswers++
            }

            Executors.newSingleThreadScheduledExecutor().schedule(
                    {
                        viewmodel.mCurrentQuestion++
                        viewmodel.running = true
                        setQuestion()
                    },
                    2, TimeUnit.SECONDS
            )
        }
    }
}
