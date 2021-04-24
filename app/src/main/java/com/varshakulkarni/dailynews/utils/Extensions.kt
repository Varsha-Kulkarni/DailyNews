package com.varshakulkarni.dailynews.utils

import java.text.SimpleDateFormat

fun String.toDate(): String {
    return SimpleDateFormat("yyyy-MM-dd").parse(this).toString().dropLast(23)
}