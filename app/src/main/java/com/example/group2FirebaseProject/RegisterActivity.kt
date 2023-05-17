package com.example.group2FirebaseProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerEmailET : EditText
    private lateinit var registerPasswordET : EditText
    private lateinit var registerBtn : Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
        listeners()
    }

    private fun listeners() {
        registerBtn.setOnClickListener {
            val email = registerEmailET.text.toString()
            val password = registerPasswordET.text.toString()

            if (email.isEmpty() || password.isEmpty() ||
                    !email.contains('@') || password.length < 5)
                return@setOnClickListener

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init(){
        registerEmailET = findViewById(R.id.registerEmailET)
        registerPasswordET = findViewById(R.id.registerPasswordET)
        registerBtn = findViewById(R.id.registerBtn)
    }

}