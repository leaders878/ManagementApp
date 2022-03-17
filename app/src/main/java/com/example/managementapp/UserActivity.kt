package com.example.managementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val username = findViewById<TextView>(R.id.user_username)

        //**이전 화면에서 전달받은 정보**//
        val user_start_intent = getIntent()
        val get_username = user_start_intent.getStringExtra("name").toString()

        //사용자 명 변경
        username.setText(get_username)

        //리사이클러뷰에 클래스 목록 불러오기
        val classrecyclerView = findViewById<RecyclerView>(R.id.user_recyclerview_class)
        val classlist = ArrayList<ListClass>()
        //**classlist.add()**//

        val classadapter = ClassAdapter(classlist)
        classrecyclerView.adapter = classadapter
        classrecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //**리사이클러뷰에서 선택한 클래스**//
        val set_class: String = "default"

        //**리사이클러뷰에 학생 목록 불러오기  - 클래스 검색**//
        val studentrecyclerView = findViewById<RecyclerView>(R.id.user_recyclerview_student)
        val studentlist = ArrayList<ListStudent>()
        //**studentlist.add()**//

        val studentadapter = StudentAdapter(studentlist)
        studentrecyclerView.adapter = studentadapter

        classrecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //**리사이클러뷰에서 선택한 학생**//
        val set_student: String = "default"

        val checkbtn = findViewById<Button>(R.id.user_contectbtn)
        checkbtn.setOnClickListener{
            val user_end_intent = Intent(this, ManagementActivity::class.java)

            //**다음 화면으로 넘겨줄 정보**//
            user_end_intent.putExtra("username", get_username)
            user_end_intent.putExtra("class", set_class)
            user_end_intent.putExtra("stuname", set_student)

            startActivity(user_end_intent)
        }

        //**재시,보충 목록 달력**//

    }
}