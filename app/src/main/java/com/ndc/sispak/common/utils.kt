package com.ndc.sispak.common

import android.content.Context
import android.content.Intent
import android.provider.Settings
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.Toast

fun openLanguageSettings(context: Context) {
    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
    context.startActivity(intent)
}

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
