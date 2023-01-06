package com.example.campusmanagementsystem

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class facultyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty)


        var facname: EditText = findViewById(R.id.facName)
        var deptname: EditText = findViewById(R.id.deptName)
        var ph: EditText = findViewById(R.id.facPhone)
        var building: EditText = findViewById(R.id.buildingName)
        var fachead: EditText = findViewById(R.id.facHead)
        var factotalnum: EditText = findViewById(R.id.facNumber)


        var btn: Button = findViewById(R.id.facData)
        var btn1: Button = findViewById(R.id.facShow)



        var progres: ProgressDialog = ProgressDialog(this)

        progres.setTitle("Saving")
        progres.setMessage("Saving Data...")
        progres.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)


        btn.setOnClickListener()
        {

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
                        min=min+1

                        if(min==100)
                        {
                            progres.dismiss()
                        }
                    }
                }
            }).start()

            // saving data in real time database

            var myFacultyData: faculty = faculty()

            myFacultyData.objectCreation(
                facname.text.toString(),
                deptname.text.toString(),
                building.text.toString(),
                fachead.text.toString(),
                factotalnum.text.toString(),
                ph.text.toString()
            )


            facname.setText("")
            deptname.setText("")
            ph.setText("")
            factotalnum.setText("")
            building.setText("")
            fachead.setText("")


            val db = Firebase.database
            val Ref = db.getReference("FacultyData")

            Ref.child("-> "+System.currentTimeMillis()).setValue(myFacultyData)

                .addOnCompleteListener(this)
                { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@facultyActivity,
                            "Data Stored Successfully...",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@facultyActivity, "Data Not Stored: " + task.exception.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }

        btn1.setOnClickListener()
        {
            startActivity(Intent(this@facultyActivity,displayFacultyData::class.java))
            Toast.makeText(this@facultyActivity,"Loading Data Successfully...",Toast.LENGTH_SHORT).show()
        }
    }


}