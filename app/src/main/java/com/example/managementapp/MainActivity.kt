package com.example.managementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startbtn = findViewById<Button>(R.id.main_startbtn)
        startbtn.setOnClickListener{
            val main_intent = Intent(this, LoginActivity::class.java)
            startActivity(main_intent)
        }
    }
}