package com.example.android.quizapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.quizapp.R
import com.example.android.quizapp.adapter.optionAdapter
import com.example.android.quizapp.model.Question
import com.example.android.quizapp.model.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*
import java.lang.ProcessBuilder.Redirect.to

class QuestionActivity : AppCompatActivity() {
    var quizzes:MutableList<Quiz>?=null
    var questions:MutableMap<String,Question>?=null
    var index=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener{
            index--
            bindViews()
        }
        btnNext.setOnClickListener{
            index++
            bindViews()
        }
        btnSubmit.setOnClickListener{
            Log.d("Submit---",questions.toString())
            var intent=Intent(this,ResultActivity::class.java)
            val json=Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }

    private fun setUpFireStore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get().addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        Log.d("data===", it.toObjects((Quiz::class.java)).toString())
                        quizzes=it.toObjects(Quiz::class.java)
                        questions=quizzes!![0].Questions
                        bindViews()
                    }

                }

        }
    }

    private fun bindViews() {
        btnPrevious.visibility=View.GONE
        btnNext.visibility=View.GONE
        btnSubmit.visibility=View.GONE

        if(index==1){
            btnNext.visibility=View.VISIBLE

        }
        else if(index==questions!!.size){
            btnSubmit.visibility=View.VISIBLE
            btnPrevious.visibility=View.VISIBLE
        }
        else{
            btnPrevious.visibility=View.VISIBLE
            btnNext.visibility=View.VISIBLE
        }
      //  val question = Question("question", "A", "B", "C", "D", "B")

        //desciption.text = question.description
        val question = questions!!["Question$index"]

        question?.let {
            desciption.text=it.description
            val optionAdapter = optionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }

    }

}