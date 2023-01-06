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

class studentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)


        var fname: EditText =findViewById(R.id.fname)
        var lname: EditText =findViewById(R.id.lname)
        var ph: EditText =findViewById(R.id.phone)
        var uni: EditText =findViewById(R.id.universityName)
        var degree: EditText =findViewById(R.id.degree)


        var btn: Button =findViewById(R.id.Data)
        var btn1: Button =findViewById(R.id.Show)


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
                        min=min+2

                        if(min==100)
                        {
                            progres.dismiss()
                        }
                    }
                }
            }).start()


            // saving data in real time database

            var myStudentData:student= student()

            myStudentData.objectCreation(fname.text.toString(),lname.text.toString()
                ,ph.text.toString(),uni.text.toString(),degree.text.toString())


            fname.setText("")
            lname.setText("")
            ph.setText("")
            uni.setText("")
            degree.setText("")

            val db= Firebase.database
            val Ref=db.getReference("StudentData")

            Ref.child("-> "+System.currentTimeMillis()).setValue(myStudentData)

                .addOnCompleteListener(this)
                {
                        task ->

                    if(task.isSuccessful)
                    {
                        Toast.makeText(this@studentActivity,"Data Stored Successfully...", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(this@studentActivity,"Data Not Stored: " + task.exception.toString(),
                            Toast.LENGTH_LONG).show()
                    }
                }

        }

        btn1.setOnClickListener()
        {
            startActivity(Intent(this@studentActivity,displayStudentData::class.java))
            Toast.makeText(this@studentActivity,"Loading Data Successfully...",Toast.LENGTH_SHORT).show()
        }








    }
}