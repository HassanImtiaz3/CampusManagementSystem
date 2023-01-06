package com.example.campusmanagementsystem

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class staffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)


        var fname:EditText=findViewById(R.id.stfname)
        var lname:EditText=findViewById(R.id.stlname)
        var ph:EditText=findViewById(R.id.stphone)
        var stId:EditText=findViewById(R.id.ststaffId)

        var btn:Button=findViewById(R.id.stsaveData)
        var btn1:Button=findViewById(R.id.stshowData)

        var progres:ProgressDialog=ProgressDialog(this)

        progres.setTitle("Saving")
        progres.setMessage("Saving Data...")
        progres.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)


        btn.setOnClickListener()
        {
            // saving data in real time database

            var max=100
            var min=0


            progres.show()

            Thread(object:Runnable{


                override fun run()
                {
                    while (max > min)
                    {
                        Thread.sleep(50)
                        progres.progress=min
                        min=min+5

                        if(min==100)
                        {
                            progres.dismiss()
                        }
                    }
                }
            }).start()



            var myStaffData:staff= staff()

            myStaffData.objectCreation(fname.text.toString(),lname.text.toString()
            ,ph.text.toString(),stId.text.toString())


            fname.setText("")
            lname.setText("")
            ph.setText("")
            stId.setText("")

            val db= Firebase.database
            val Ref=db.getReference("StaffData")

            Ref.child("-> "+System.currentTimeMillis()).setValue(myStaffData)

                .addOnCompleteListener(this)
                {
                    task ->

                    if(task.isSuccessful)
                    {
                        Toast.makeText(this@staffActivity,"Data Stored Successfully...",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(this@staffActivity,"Data Not Stored: " + task.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }

        }

        btn1.setOnClickListener()
        {
            startActivity(Intent(this@staffActivity,displayStaffData::class.java))
            Toast.makeText(this@staffActivity,"Loading Data Successfully...",Toast.LENGTH_SHORT).show()
        }





    }







}