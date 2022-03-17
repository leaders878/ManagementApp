package com.example.managementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class ManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        val stuname = findViewById<TextView>(R.id.manage1_stuname)
        val content = findViewById<TextView>(R.id.manage1_content)
        val progress = findViewById<TextView>(R.id.manage1_progress)
        val homework = findViewById<TextView>(R.id.manage1_homework)

        //**이전 화면에서 전달받은 정보**//
        val manage_start_intent = getIntent()
        val get_username = manage_start_intent.getStringExtra("username").toString()
        val get_class = manage_start_intent.getStringExtra("class").toString()
        val get_stuname = manage_start_intent.getStringExtra("stuname").toString()

        //학생명 변경하기
        stuname.setText(get_stuname)

        //리사이클러뷰에 영역 목록 불러오기
        val arearecyclerView = findViewById<RecyclerView>(R.id.manage1_recyclerview_area)
        val arealist = ArrayList<ListArea>()
        //**arealist.add()**//

        val areaadapter = AreaAdapter(arealist)
        arearecyclerView.adapter = areaadapter

        arearecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //**리사이클러뷰에서 선택한 영역**//
        val set_area: String = "default"

        //**리사이클러뷰에 과목 목록 불러오기 - 영역 검색**//
        val subjectrecyclerView = findViewById<RecyclerView>(R.id.manage1_recyclerview_subject)
        val subjectlist = ArrayList<ListSubject>()
        //**subjectlist.add()**//

        val subjectadapter = SubjectAdapter(subjectlist)
        subjectrecyclerView.adapter = subjectadapter

        subjectrecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //**리사이클러뷰에서 선택한 과목**//
        val set_subject: String = ""

        //**영역,과목 검색해서 정보 가져오고 변경하기**//
        val get_content: String = "default"
        val get_progress: String = "default"
        val get_homework: String = "default"

        content.setText(get_content)
        progress.setText(get_progress)
        homework.setText(get_homework)

        val nextbtn = findViewById<Button>(R.id.manage1_nextbtn)
        nextbtn.setOnClickListener{
            val manage_end_intent = Intent(this, EnterActivity::class.java)

            //**다음 화면으로 넘겨줄 정보**//
            manage_end_intent.putExtra("username", get_username)
            manage_end_intent.putExtra("class", get_class)
            manage_end_intent.putExtra("stuname", get_stuname)
            manage_end_intent.putExtra("area", set_area)
            manage_end_intent.putExtra("subject", set_subject)
            manage_end_intent.putExtra("content", get_content)
            manage_end_intent.putExtra("progress", get_progress)
            manage_end_intent.putExtra("homework", get_homework)

            startActivity(manage_end_intent)
        }
    }
}