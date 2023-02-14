package com.example.android.quizapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.quizapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_intro.*

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        Log.d("Activity---", "LoginIntro")

        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            redirect("MAIN")
        }
        btnGetStarted.setOnClickListener {
            redirect("Login")
        }
    }

    private fun redirect(name: String) {


        if (name.equals("Login")) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}