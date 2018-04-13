package com.example.cobe.cryptonews.common.helpers

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import com.example.cobe.cryptonews.common.constants.DEFAULT_FORMATTER
import java.util.*

/**
 * Created by cobe on 13/04/2018.
 */

fun showDatePicker(from: Context, listener: DatePickerDialog.OnDateSetListener) = with(Calendar.getInstance()) {
    DatePickerDialog(from,
            listener,
            get(Calendar.YEAR),
            get(Calendar.MONTH),
            get(Calendar.DAY_OF_MONTH))
}.show()

fun getDate(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    return DEFAULT_FORMATTER.format(calendar.time)
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()