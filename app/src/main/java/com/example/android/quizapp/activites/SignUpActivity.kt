package com.example.android.quizapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android.quizapp.R
import com.google.firebase.auth.FirebaseAuth


import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Log.d("Activity---","Signup")


        firebaseAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            signUpUser()

        }

        txtSignUp.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun signUpUser(){
        val email :String=etEmailAddress.text.toString()
        val password :String=etPassword.text.toString()
        val confirmPassword :String=etConfirmPassword.text.toString()

        if(email.isBlank()||password.isBlank()||confirmPassword.isBlank()){
            Toast.makeText(this,"please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }
        if(password!=confirmPassword){
            Toast.makeText(this,"password and confirm pass do not match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                }
                else{
                    Toast.makeText(this,"Error in creating User", Toast.LENGTH_SHORT).show()


                }
            }
    }
}