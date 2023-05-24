package com.example.group2FirebaseProject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.snapshots
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var userIDTV : TextView
    private lateinit var userPhotoUrlET : EditText
    private lateinit var userIDET : EditText
    private lateinit var uploadBtn : Button

    private val db = FirebaseDatabase.getInstance().getReference("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        listeners()

        db.child(FirebaseAuth.getInstance().uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java) ?: return
                userIDTV.text = user.id
                Glide.with(this@ProfileActivity).load(user.url).into(imageView)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

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
            db.child(FirebaseAuth.getInstance().uid!!).setValue(user)
        }
    }
}