package com.example.appsmarthome

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database
import android.widget.TextView;
import com.example.testapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    var btn1count = 0
    var btn2count = 0
    var btn3count = 0
    var btn4count = 0
    var btn5count = 0
    var btn6count = 0
    val Humidity1 = 0
    val Humidity2 = 0
    val Humidity3 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        val Temperature : Int
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        val database = Firebase.database("https://test-8cd27-default-rtdb.firebaseio.com/")
        val led1 = database.getReference("Led1")
        val led2 = database.getReference("Led2")
        val led3 = database.getReference("Led3")
        val fan1 = database.getReference("Fan1")
        val fan2 = database.getReference("Fan2")
        val fan3 = database.getReference("Fan3")

        val temp1 = database.getReference("Temperature1")
        val temp2  = database.getReference("Temperature2")
        val temp3 = database.getReference("Temperature3")
        val hum1 = database.getReference("Humidity1")
        val hum2 = database.getReference("Humidity2")
        val hum3 = database.getReference("Humidity3")
        val txt1 = findViewById<TextView>(R.id.textView1)
        val txt2 = findViewById<TextView>(R.id.textView2)
        val txt3 = findViewById<TextView>(R.id.textView3)
        val txt4 = findViewById<TextView>(R.id.textView4)
        val txt5 = findViewById<TextView>(R.id.textView5)
        val txt6 = findViewById<TextView>(R.id.textView6)


        temp1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val Temperature1 = dataSnapshot.getValue()
                txt1.text = Temperature1.toString()
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        temp2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val Temperature2 = snapshot.getValue()
                txt2.text = Temperature2.toString()
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        temp3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val Temperature3 = snapshot.getValue()
                txt3.text = Temperature3.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        hum1.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val Humidity1 = snapshot.getValue().toString()
                txt4.text = Humidity1.toString()
            }
        })
        hum2.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val Humidity2 = snapshot.getValue().toString()
                txt5.text = Humidity2
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        hum3.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val Humdity3 = snapshot.getValue().toString()
                txt6.text = Humdity3
            }
        })
        findViewById<Button>(R.id.button1)
            .setOnClickListener {
                btn1count += 1
                if (btn1count % 2 == 1) {
                    led1.setValue(0)
                } else {
                    led1.setValue(1)
                }
            }
        findViewById<Button>(R.id.button2)
            .setOnClickListener {
                btn2count += 1
                if (btn2count % 2 == 1) {
                    led2.setValue(0)
                } else {
                    led2.setValue(1)
                }
            }

        findViewById<Button>(R.id.button3)
            .setOnClickListener {
                btn3count += 1
                if (btn3count % 2 == 1) {
                    led3.setValue(0)
                } else {
                    led3.setValue(1)
                }
            }

        findViewById<Button>(R.id.button4)
            .setOnClickListener {
                btn4count += 1
                if (btn4count % 2 == 1) {
                    fan1.setValue(0)
                } else {
                    fan1.setValue(1)
                }
            }

        findViewById<Button>(R.id.button5)
            .setOnClickListener {
                btn5count += 1
                if (btn5count % 2 == 1) {
                    fan2.setValue(0)
                } else {
                    fan2.setValue(1)
                }
            }

        findViewById<Button>(R.id.button6)
            .setOnClickListener {
                btn6count += 1
                if (btn6count % 2 == 1) {
                    fan3.setValue(0)
                } else {
                    fan3.setValue(1)
                }
            }
        findViewById<Button>(R.id.button7)
            .setOnClickListener {
                val intent = Intent(this, Humidity::class.java)
                startActivity(intent)
            }
        findViewById<Button>(R.id.button8)
            .setOnClickListener {
                val intent = Intent(this, Phong1::class.java)
                startActivity(intent)
            }
    }
}