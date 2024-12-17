package com.ndc.sispak.common

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.Toast


fun Long.toDateString(pattern: String = "dd MMMM yyyy", locale: Locale = Locale("id", "ID")): String {
    if (this == 0L) return ""
    val dateFormat = SimpleDateFormat(pattern, locale)
    val date = Date(this)
    return dateFormat.format(date)
}

class MakeToast(
    private val context: Context,
) {
    fun short(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    fun long(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
