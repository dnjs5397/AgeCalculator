package com.Dev_won.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? = null
    private var resultMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.dateBtn)

        selectedDate = findViewById(R.id.selectedDate)
        resultMinutes = findViewById(R.id.resultMinutes)

        btnDatePicker.setOnClickListener {

            clickedDatePicker()

        }

    }

    private fun clickedDatePicker() {

        Toast.makeText(this, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, dayOfMonth ->

                val selDate = "${selectedYear}/${selectedMonth + 1}/$dayOfMonth"

                selectedDate?.text = selDate

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREAN)

                val theDate = sdf.parse(selDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val resultTime = currentDateInMinutes - selectedDateInMinutes

                        resultMinutes?.text = resultTime.toString()
                    }
                }
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}