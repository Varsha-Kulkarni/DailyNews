package com.varshakulkarni.dailynews.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * get a week older date
 */
fun getOneWeekOldDateFormatted() : String{
    val calender = Calendar.getInstance()
    calender.add(Calendar.DAY_OF_YEAR, -7)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
    return dateFormat.format(calender.time)
}