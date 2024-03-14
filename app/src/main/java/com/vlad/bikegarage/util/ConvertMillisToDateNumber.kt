package com.vlad.bikegarage.util

import java.text.SimpleDateFormat
import java.util.Date

fun  convertMillisToDateMonthNumber(millis: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    return formatter.format(Date(millis))
}

fun convertMillisToDateMonthName(millis: Long): String {
    val formatter = SimpleDateFormat("dd.MMMM.yyyy")
    return formatter.format(Date(millis))
}