package com.example.managementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //사용자가 입력한 정보
        val input_name = findViewById<EditText>(R.id.signup_name).toString()
        val input_email = findViewById<EditText>(R.id.signup_email).toString()
        val input_pwd = findViewById<EditText>(R.id.signup_pwd).toString()
        val input_phone = findViewById<EditText>(R.id.signup_phone).toString()

        val signupbtn = findViewById<Button>(R.id.signup_btn)
        signupbtn.setOnClickListener{

            //빈칸 있을 경우 알림 띄우기
            if(input_name.trim().length>0) {
                Toast.makeText(this, "성함을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(input_email.trim().length>0) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(input_pwd.trim().length>0) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(input_phone.trim().length>0) {
                Toast.makeText(this, "휴대폰번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {

                //**이메일 중복 검색**//

                //데이터 입력
                val name = input_name.trim()
                val email = input_name.trim()
                val phone = input_name.trim()
                val pwd = input_name.trim()

                val userdb = FirebaseDatabase.getInstance()
                val userref = userdb.getReference("UserList")
                val userId = userref.push().key.toString()
                val user_info = ListUser(name, email, pwd, phone)

                userref.child(userId).setValue(user_info)

                //**예외처리**//
                //정상적으로 회원가입 되었다는 알림 : Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()
                //회원가입 오류 알림 : Toast.makeText(this, "회원가입 오류", Toast.LENGTH_SHORT).show()

                }
                val signup_intent = Intent(this, LoginActivity::class.java)
                startActivity(signup_intent)
            }
        }
}