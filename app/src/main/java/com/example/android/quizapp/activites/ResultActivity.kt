package com.example.android.quizapp.activites

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.android.quizapp.R
import com.example.android.quizapp.model.Quiz
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*
import java.lang.StringBuilder

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpView()
    }

    private fun setUpView() {
        val quizData = intent.getStringExtra("QUIZ")
         quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
      val builder=StringBuilder("")
        for(entry in quiz.Questions.entries){
            val  question=entry.value
            builder.append("<font color'#18206F'><b>Question :${question.description}</b></font><br/><br/>")
            builder.append("<font color'#009688'>Answer :${question.answer}</font><br/><br/>")
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            txtAnswer.text=Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT)

            }
        else{
            txtAnswer.text=Html.fromHtml(builder.toString())
        }
    }

    private fun calculateScore() {
        var score=0
        for(entry in quiz.Questions.entries){
            val question=entry.value
            if(question.answer==question.useranswer){
                score+=10
            }
        }

        txtScore.text="Your Score :$score"
    }
}