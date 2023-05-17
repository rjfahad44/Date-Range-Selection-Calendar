package com.ft.daterangeselectioncalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import android.widget.Toast
import com.ft.daterangeselectioncalendar.databinding.ActivityMainBinding
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val barChartList = arrayListOf(
        BarChartModel(4, 0, R.color.light_green),
        BarChartModel(5, 5, R.color.light_orange),
        BarChartModel(6, 5, R.color.light_blue),
        BarChartModel(7, 5, R.color.light_cyan),
        BarChartModel(6, 5, R.color.light_ash),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getDateRangeButton.setOnClickListener {
            val selectedDates = binding.calendarPicker.getSelectedDates()
            if (selectedDates != null) {
                val firstDate = DateFormat.getDateInstance().format(Date(selectedDates.first))
                val secondDate = DateFormat.getDateInstance().format(Date(selectedDates.second))
                Toast.makeText(this, "$firstDate   $secondDate", Toast.LENGTH_SHORT).show()
            }
        }

//        binding.calendarPicker.addEvents(
//            CalendarEvent(
//                eventName = "event  1",
//                eventDescription = "event 1 desc",
//                date = Calendar.getInstance().time
//            ),
//            CalendarEvent(
//                eventName = "event  2",
//                eventDescription = "event 2 desc",
//                date = Calendar.Builder().setDate(2021, 8, 19).build().time
//            ),
//            CalendarEvent(
//                eventName = "event  3",
//                eventDescription = "event 3 desc",
//                date = Calendar.Builder().setDate(2021, 8, 1).build().time
//            ),
//            CalendarEvent(
//                eventName = "event  4",
//                eventDescription = "event 4 desc",
//                date = Calendar.Builder().setDate(2021, 11, 10).build().time
//            ),
//            CalendarEvent(
//                eventName = "event  5",
//                eventDescription = "event 5 desc",
//                date = Calendar.Builder().setDate(2021, 0, 29).build().time
//            ),
//        )

//        binding.calendarPicker.setFirstSelectedDate(year = 2021, month = 8, day = 9)
//        binding.calendarPicker.setSecondSelectedDate(year = 2021, month = 8, day = 19)
//
//        binding.calendarPicker.initCalendar() // REFRESHES CALENDAR AFTER SELECTING DATES


        customizeBarChart()
    }

    private fun customizeBarChart() {

        val lH = resources.displayMetrics.heightPixels
        val lW = resources.displayMetrics.widthPixels

        Log.d("LHW", "$lH   $lW")

        barChartList.onEach {
            val view = View(this)
            view.layoutParams = LinearLayout.LayoutParams(lW / it.layoutWidth, LayoutParams.MATCH_PARENT)
            view.setMargins(it.margin, 0, 0, 0)
            view.setBackgroundColor(getColor(it.color))
            binding.customizeBarChart.addView(view)
        }
    }
}

data class BarChartModel(
    val layoutWidth: Int,
    val margin: Int,
    val color: Int,
)