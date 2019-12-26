package com.elemental.firebasechat

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*


class CalendarActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView.setOnDayClickListener(OnDayClickListener { eventDay ->
            val clickedDayCalendar: Calendar = eventDay.calendar
            Log.d("weekyear",clickedDayCalendar.time.toString())
        })
    }
}
