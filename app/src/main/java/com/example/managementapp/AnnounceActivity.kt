package com.example.managementapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate

class AnnounceActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announce)

        val announce_text = findViewById<TextView>(R.id.announce_textView)

        //**이전 화면에서 전달받은 정보**//
        val announce_start_intent = getIntent()
        val get_email = announce_start_intent.getStringExtra("email").toString()

        //오늘 날짜 가져오기
        val today_date = LocalDate.now().toString()

        //**오늘 날짜 검색 - 공지사항 출력**//
        val announce_date: String = "defalut"
        val announce_context: String = "defalut"

        if(today_date == announce_date){
            announce_text.setText(announce_context)
        } else {
            announce_text.setText("공지사항이 없습니다")
        }

        val confirmbtn = findViewById<Button>(R.id.announce_checkbtn)
        confirmbtn.setOnClickListener{

            val adate = "default"
            val atext = "default"
            val rname = "default"
            val rnum_before: Int = 0

            //**get_email 이용해서 UserName 검색**//
            //**today_date 이용해서 ReadUserNum 검색**//
            //**rname = UserName**//
            //**rnum_before = ReadUserNum**//

            val rnum_after = rnum_before + 1
            val rnum_str = rnum_after.toString()

            val adb = FirebaseDatabase.getInstance()
            val aref = adb.getReference("AnnounceList")
            val aId = aref.push().key.toString()
            val a_info = ListAnnounce(adate, atext, rname, rnum_str)

            aref.child(aId).setValue(a_info)

            val announce_end_intent = Intent(this, UserActivity::class.java)

            //**다음 화면으로 넘겨줄 정보**//
            announce_end_intent.putExtra("name", rname)

            startActivity(announce_end_intent)
        }
    }
}