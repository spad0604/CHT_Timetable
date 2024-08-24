package com.example.appsmarthome

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.sql.Date

class Humidity : AppCompatActivity() {
    private val timerInterval = 2000
    val dataValues : ArrayList<Entry> = ArrayList()
    val dataValues2 : ArrayList<Entry> = ArrayList()
    val dataValues3 : ArrayList<Entry> = ArrayList()
    var k1 = 0f
    var k2 = 0f
    var k3 = 0f
    var arrayList1: ArrayList<Float> = ArrayList()
    var arrayList2: ArrayList<Float> = ArrayList()
    var arrayList3: ArrayList<Float> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        arrayList1.add(0f)
        arrayList2.add(0f)
        arrayList3.add(0f)
        super.onCreate(savedInstanceState)
        val database = Firebase.database("https://test-8cd27-default-rtdb.firebaseio.com/")
        val hum1 = database.getReference("Humidity1")
        val hum2 = database.getReference("Humidity2")
        val hum3 = database.getReference("Humidity3")
        setContentView(R.layout.activity_humidity)
        var Humidity1 = 0f
        var Humidity2 = 0f
        var Humidity3 = 0f
        hum1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Humidity1 = dataSnapshot.getValue(Float::class.java) ?: 0f
                arrayList1.add(Humidity1)
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        hum2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Humidity2 = snapshot.getValue(Float::class.java) ?: 0f
                arrayList2.add(Humidity2)
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        hum3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Humidity3 = snapshot.getValue(Float::class.java) ?: 0f
                arrayList3.add(Humidity3)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val lineChart = findViewById<LineChart>(R.id.linechart2)

        val linedataset = LineDataSet(dataValues(),"Humidity1")
        val linedataset2 = LineDataSet(dataValues2(), "Humidity2")
        val linedataset3 = LineDataSet(dataValues3(), "Humidity3")
                val timer2 = object : CountDownTimer(timerInterval.toLong(), timerInterval.toLong()) {
                    override fun onTick(millisUntilFinished: Long) {
                        dataValues2()
                        dataValues()
                        dataValues3()

                        k2 += 1
                        k1++
                        k3++

                        linedataset.lineWidth = 3f
                        linedataset.valueTextSize = 10f
                        linedataset.setColors(Color.BLUE)

                        linedataset2.lineWidth = 3f
                        linedataset2.valueTextSize = 10f
                        linedataset2.setColors(Color.RED)

                        linedataset3.lineWidth = 3f
                        linedataset3.valueTextSize = 10f
                        linedataset3.setColors(Color.GREEN)

                        val dataSets = ArrayList<ILineDataSet>()
                        dataSets.add(linedataset)
                        dataSets.add(linedataset2)
                        dataSets.add(linedataset3)

                        val data = LineData(dataSets)
                        lineChart.setData(data)
                        lineChart.setBackgroundColor(Color.WHITE)
                        lineChart.setVisibleXRangeMaximum(10f)
                        lineChart.axisLeft.axisMinimum = 0f
                        lineChart.axisLeft.axisMaximum = 100f
                        lineChart.axisRight.isEnabled = false
                        lineChart.xAxis.axisMinimum = 0f
                        lineChart.xAxis.axisMaximum = 10000f
                        lineChart.moveViewToX(k2 - 8f)
                        lineChart.invalidate()

                        val currentTime = System.currentTimeMillis()
                        lineChart.xAxis.valueFormatter = object : IAxisValueFormatter {
                            override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                                return SimpleDateFormat("HH:mm:ss").format(Date((currentTime + (value * timerInterval)).toLong()))
                            }
                        }
                    }

                    override fun onFinish() {
                        start()
                    }
        }
        timer2.start()
    }
    fun dataValues(): List<Entry>? {
        dataValues.add(Entry(k1, arrayList1.last()))

        return dataValues
    }
    fun dataValues2() : List<Entry>? {
        dataValues2.add(Entry(k2, arrayList2.last()))

        return dataValues2
    }
    fun dataValues3() : List<Entry>? {
        dataValues3.add(Entry(k3, arrayList3.last()))

        return dataValues3
    }
}