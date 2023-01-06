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

class displayFacultyData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_faculty_data)



        var listView: ListView =findViewById(R.id.facultylistdata)
        var data= ArrayList<String>()


        val db= Firebase.database
        val Ref=db.getReference("FacultyData")

        Ref.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (obj in snapshot.children)
                {
                    var temp=obj.getValue<faculty>()
                    var readData:String = " Faculty Name: ${temp?.facname} \n Department Name: ${temp?.deptname} \n Building Name: ${temp?.building} \n Faculty Head: ${temp?.fachead} \n Total Faculty Member: ${temp?.totalfacnum} \n Phone Number: ${temp?.phone} "
                    data.add(readData)
                }

                var myListAdapter= ArrayAdapter<String>(this@displayFacultyData,android.R.layout.simple_list_item_1,data)
                listView.adapter=myListAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@displayFacultyData,"Loading Data UnSuccessfully...", Toast.LENGTH_SHORT).show()
            }


        })





    }
}