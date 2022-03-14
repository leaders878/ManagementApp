package com.example.managementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginbtn = findViewById<Button>(R.id.login_loginbtn)
        loginbtn.setOnClickListener{

            //정보 확인

            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        val signupbtn = findViewById<Button>(R.id.login_signupbtn)
        signupbtn.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}