package com.example.managementapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate

class EnterActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)

        //**이전 화면에서 전달받은 정보**//
        val enter_start_intent = getIntent()
        val get_username = enter_start_intent.getStringExtra("username").toString()
        val get_class = enter_start_intent.getStringExtra("class").toString()
        val get_stuname = enter_start_intent.getStringExtra("stuname").toString()
        val get_area = enter_start_intent.getStringExtra("area").toString()
        val get_subject = enter_start_intent.getStringExtra("subject").toString()
        val get_content = enter_start_intent.getStringExtra("content").toString()
        val get_progress = enter_start_intent.getStringExtra("progress").toString()
        val get_homework = enter_start_intent.getStringExtra("homework").toString()

        //사용자 입력 값
        val stuname = findViewById<TextView>(R.id.manage2_stuname)
        val todaydate = findViewById<TextView>(R.id.manage2_date)
        val attend_yes = findViewById<CheckBox>(R.id.manage2_checkBox_attend_yes)
        val attend_no = findViewById<CheckBox>(R.id.manage2_checkBox_attend_no)
        val sup_date = findViewById<TextView>(R.id.manage2_sup_date)
        val sup_time = findViewById<TextView>(R.id.manage2_sup_time)
        val test_yes = findViewById<CheckBox>(R.id.manage2_checkBox_test_yes)
        val test_no = findViewById<CheckBox>(R.id.manage2_checkBox_test_no)
        val test_score = findViewById<TextView>(R.id.manage2_score)
        val retest_yes = findViewById<CheckBox>(R.id.manage2_checkBox_retest_yes)
        val retest_no = findViewById<CheckBox>(R.id.manage2_checkBox_retest_no)
        val retest_date = findViewById<TextView>(R.id.manage2_retest_date)
        val retest_time = findViewById<TextView>(R.id.manage2_retest_time)
        val homework_score = findViewById<TextView>(R.id.manage2_homework).toString()
        val ettitude_score = findViewById<TextView>(R.id.manage2_ettitude).toString()

        //학생명 변경하기
        stuname.setText(get_stuname)

        //날짜 변경하기
        todaydate.setText(LocalDate.now().toString())

        //처음에 보이지 않기
        val layout_sup = findViewById<LinearLayout>(R.id.manage2_layout_sup)
        val layout_score = findViewById<LinearLayout>(R.id.manage2_layout_score)
        val layout_retest = findViewById<LinearLayout>(R.id.manage2_layout_retest)
        val layout_retestdate = findViewById<LinearLayout>(R.id.manage2_layout_retestdate)
        layout_sup.visibility = View.INVISIBLE
        layout_score.visibility = View.INVISIBLE
        layout_retest.visibility = View.INVISIBLE
        layout_retestdate.visibility = View.INVISIBLE

        //출석 - 보충
        var attend_result: String = "defalut"
        var sup_date_result: String = "defalut"
        var sup_time_result: String = "defalut"

        if(attend_yes.isChecked()){
            attend_result = "true"
            sup_date_result = "none"
            sup_time_result = "none"
        } else if(attend_no.isChecked()) {
            attend_result = "false"
            layout_sup.visibility = View.VISIBLE
            sup_date_result = sup_date.toString()
            sup_time_result = sup_time.toString()
        } else {
            Toast.makeText(this, "출석 파트 오류", Toast.LENGTH_SHORT).show()
        }

        //시험 - 재시
        var test_result: String = "defalut"
        var test_score_result: String = "defalut"
        var retest_result: String = "defalut"
        var retest_date_result: String = "defalut"
        var retest_time_result: String = "defalut"

        if(test_yes.isChecked()){
            test_result = "true"
            layout_score.visibility = View.VISIBLE
            test_score_result = test_score.toString()
            retest_result = "none"
            retest_date_result = "none"
            retest_time_result = "none"
        } else if(test_no.isChecked()) {
            test_result = "false"
            test_score_result = "none"
            layout_retest.visibility = View.VISIBLE
            if(retest_yes.isChecked()){
                retest_result = "true"
                layout_retestdate.visibility = View.VISIBLE
                retest_date_result = retest_date.toString()
                retest_time_result = retest_time.toString()
            } else if(retest_no.isChecked()){
                retest_result = "false"
                retest_date_result = "none"
                retest_time_result = "none"
            } else {
                Toast.makeText(this, "재시 파트 오류", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "시험 파트 오류", Toast.LENGTH_SHORT).show()
        }

        val confirmbtn = findViewById<Button>(R.id.manage2_confirmbtn)
        confirmbtn.setOnClickListener{

            //데이터 입력
            val date = todaydate.toString().trim()
            val username = get_username.trim()
            val classname = get_class.trim()
            val studentname = get_stuname.trim()
            val area = get_area.trim()
            val subject = get_subject.trim()
            val content = get_content.trim()
            val progress = get_progress.trim()
            val attend = attend_result.trim()
            val supdate = sup_date_result.trim()
            val suptime = sup_time_result.trim()
            val test = test_result.trim()
            val score = test_score_result.trim()
            val retest = retest_result.trim()
            val retestdate = retest_date_result.trim()
            val retesttime = retest_time_result.trim()
            val homework_text = get_homework.trim()
            val homework = homework_score.trim()
            val ettitude = ettitude_score.trim()

            val userdb = FirebaseDatabase.getInstance()
            val userref = userdb.getReference("UserList")
            val scoreId = userref.push().key.toString()
            val score_info = ListScore(date, username, classname, studentname, area, subject, content, progress,
                attend, supdate, suptime,
                test, score, retest, retestdate, retesttime,
                homework_text, homework, ettitude)

            userref.child(scoreId).setValue(score_info)

            val enter_set_intent = Intent(this, ResultActivity::class.java)

            startActivity(enter_set_intent)
        }
    }
}