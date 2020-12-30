package com.mfathurz.moviecatalogue.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

object UtilsHelper {
    @SuppressLint("SimpleDateFormat")
    fun changeDateFormat(text: String): String? {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("MMMM dd, yyyy")
            val date = parser.parse(text)
            formatter.format(date as Date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}