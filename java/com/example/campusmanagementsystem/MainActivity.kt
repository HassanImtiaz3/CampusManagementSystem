package com.example.campusmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val student:Button=findViewById(R.id.studentBtn)
        val faculty:Button=findViewById(R.id.facultyBtn)
        val staff:Button=findViewById(R.id.staffBtn)

        student.setOnClickListener()
        {
            startActivity(Intent(this@MainActivity,studentActivity::class.java))
        }

        faculty.setOnClickListener()
        {
            startActivity(Intent(this@MainActivity,facultyActivity::class.java))
        }

        staff.setOnClickListener()
        {
            startActivity(Intent(this@MainActivity,staffActivity::class.java))
        }




    }
}