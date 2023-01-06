package com.example.campusmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class displayStaffData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_staff_data)




        var listView:ListView=findViewById(R.id.stafflistdata)
        var data= ArrayList<String>()


        val db= Firebase.database
        val Ref=db.getReference("StaffData")

        Ref.addValueEventListener(object :ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (obj in snapshot.children)
                {
                    var temp=obj.getValue<staff>()
                    var readData:String = " First Name: ${temp?.fname} \n Last Name: ${temp?.lname} \n Phone Number: ${temp?.ph} \n Staff ID: ${temp?.stId}"
                    data.add(readData)
                }

                var myListAdapter=ArrayAdapter<String>(this@displayStaffData,android.R.layout.simple_list_item_1,data)
                listView.adapter=myListAdapter
            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(this@displayStaffData,"Loading Data UnSuccessfully...",Toast.LENGTH_SHORT).show()
            }


        })














    }
}