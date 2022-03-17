package com.example.managementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //**시험결과 html 생성**//

        val sendbtn = findViewById<Button>(R.id.result_sendbtn)
        sendbtn.setOnClickListener{

            //**시험결과 html 주소로 전송**//
            val message = "html"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.setPackage("com.kakao.talk")
            startActivity(intent)

        }

        val nextbtn = findViewById<Button>(R.id.result_nextbtn)
        nextbtn.setOnClickListener{
            val result_set_intent = Intent(this, UserActivity::class.java)
            startActivity(result_set_intent)
        }
    }
}