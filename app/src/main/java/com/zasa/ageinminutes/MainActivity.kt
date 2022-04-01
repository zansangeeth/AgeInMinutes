package com.zasa.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {
            view -> clickedDatePicker(view)

        }
    }


    fun clickedDatePicker(view : View){

        var myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, day ->

            val selectedDate = "$day/${month+1}/$year"
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            tvSelectedDate.setText(selectedDate)

            val theDate = sdf.parse(selectedDate)
            val dateInMinutes = theDate!!.time/60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes= currentDate!!.time/60000

            val differenceInMinutes = currentDateInMinutes-dateInMinutes

            tvSelectedDateInMinutes.text = differenceInMinutes.toString()

        }, year,month,day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }
}


