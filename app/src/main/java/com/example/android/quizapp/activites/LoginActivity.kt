package com.example.android.quizapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android.quizapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("Activity---","Login")

        firebaseAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            login()
        }
        txtSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()

        }

    }

    private fun login() {
        val email = etEmailAddress.text.toString()
        val pass = etPassword.text.toString()
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Error in Login", Toast.LENGTH_SHORT).show()

            }


        }


    }
}