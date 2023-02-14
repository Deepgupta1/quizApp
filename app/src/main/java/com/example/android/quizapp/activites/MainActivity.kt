package com.example.android.quizapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem

import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.quizapp.R
import com.example.android.quizapp.adapter.QuizAdapter
import com.example.android.quizapp.model.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapters: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Activity---", "MAin")


        setupViews()


    }

    fun setupViews() {
        setUpDrawerLayout()
        setUpRecyclerView()
        setUpFireStore()
        setUpDatePicker()

    }

    private fun setUpDatePicker() {
        btnDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "datePicker")
            datePicker.addOnPositiveButtonClickListener {
Log.d("data---",datePicker.headerText)
                val dateFormat=SimpleDateFormat("dd-MM-yyyy")
                val date=dateFormat.format(Date(it))
                Log.d("data--->",date)
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)

            }
            datePicker.addOnNegativeButtonClickListener {

            }
            datePicker.addOnCancelListener {
//12-02-2023
            }
        }
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "there is an error in fetching data", Toast.LENGTH_SHORT)
                    .show()
                return@addSnapshotListener
            }
            Log.d("Data---", value.toObjects(Quiz::class.java).toString())
            //    quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapters.notifyDataSetChanged()
        }

    }

    fun setUpRecyclerView() {
        adapters = QuizAdapter(this, quizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this, 2)
        quizRecyclerView.adapter = adapters
    }

    fun setUpDrawerLayout() {
        setSupportActionBar(topAppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            startActivity(Intent(this,ProfileActivity::class.java))
            mainDrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
}