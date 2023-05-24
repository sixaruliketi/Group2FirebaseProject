package com.example.group2FirebaseProject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var userIDTV : TextView
    private lateinit var userPhotoUrlET : EditText
    private lateinit var userIDET : EditText
    private lateinit var uploadBtn : Button

    private val db = Firebase.database.getReference("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        listeners()
    }

    private fun init(){
        imageView = findViewById(R.id.imageView)
        userIDTV = findViewById(R.id.userIDTV)
        userPhotoUrlET = findViewById(R.id.userPhotoUrlET)
        userIDET = findViewById(R.id.userIDET)
        uploadBtn = findViewById(R.id.uploadBtn)
    }
    private fun listeners() {
        uploadBtn.setOnClickListener {
            val url = userPhotoUrlET.text.toString()
            val id = userIDET.text.toString()
            val user = User(id, url)
            db.child("rame").setValue("user").addOnCompleteListener {
                if (it.isSuccessful) Toast.makeText(this, "good", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }

            //Glide.with(this).load(url).into(imageView)


        }
    }
}