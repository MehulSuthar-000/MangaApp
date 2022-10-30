package com.example.storyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login_page : AppCompatActivity() {
    private lateinit var tvRedirectSignUp: TextView
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button

    // Creating firebaseAuth object
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hiding the top tool Bar
        supportActionBar?.hide()
        setContentView(R.layout.activity_login_page)


        val actionBar = supportActionBar

//        Set whether home should be displayed as an "up" affordance.
//        Set this to true if selecting "home" returns up by a single level in your UI rather than back to the top level or front page.
        actionBar?.setDisplayHomeAsUpEnabled(true)


        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmailAddress)
        etPass = findViewById(R.id.etPassword)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this, registration_page::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right)
        }
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()



        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Log In Successfull ", Toast.LENGTH_SHORT).show()
                intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out)
            } else {
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
            }
        }
    }

}