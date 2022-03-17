package com.example.managementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //사용자가 입력한 정보
        val input_email: String = findViewById<EditText>(R.id.login_email).toString()
        val input_pwd: String = findViewById<EditText>(R.id.login_pwd).toString()

        //로그인하기
        val loginbtn = findViewById<Button>(R.id.login_loginbtn)
        loginbtn.setOnClickListener{

            //**이메일 존재 검색 과정 필요**//

            //**데이터베이스에서 변수 가져오기**//
            val store_email: String = "defalut"
            val store_pwd: String = "defalut"

            if(store_email == input_email){
                if(store_pwd == input_pwd){
                    val login_intent = Intent(this, AnnounceActivity::class.java)

                    //**다음 화면으로 넘겨줄 정보**//
                    login_intent.putExtra("email", input_email)

                    startActivity(login_intent)

                } else {
                    Toast.makeText(this, "비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "없는 계정입니다", Toast.LENGTH_SHORT).show()
            }

        }

        val signupbtn = findViewById<Button>(R.id.login_signupbtn)
        signupbtn.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}