package com.example.campusmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class displayStudentData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_student_data)

        var listView: ListView =findViewById(R.id.studentlistdata)
        var data= ArrayList<String>()


        val db= Firebase.database
        val Ref=db.getReference("StudentData")

        Ref.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (obj in snapshot.children)
                {
                    var temp=obj.getValue<student>()
                    var readData:String = " First Name: ${temp?.fname} \n Last Name: ${temp?.lname} \n Phone Number: ${temp?.ph} \n University Name: ${temp?.uniName} \n Degree Name: ${temp?.degree}"
                    data.add(readData)
                }

                var myListAdapter= ArrayAdapter<String>(this@displayStudentData,android.R.layout.simple_list_item_1,data)
                listView.adapter=myListAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@displayStudentData,"Loading Data UnSuccessfully...", Toast.LENGTH_SHORT).show()
            }


        })





    }
}