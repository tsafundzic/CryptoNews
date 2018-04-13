package com.example.cobe.cryptonews.common.utils

import android.app.DatePickerDialog
import android.content.Context
import com.example.cobe.cryptonews.common.constants.Constants
import java.util.*

/**
 * Created by cobe on 13/04/2018.
 */
class DialogUtils {

    companion object {

        fun showDatePicker(from: Context, listener: DatePickerDialog.OnDateSetListener) {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(from, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        fun getDate(year: Int, month: Int, day: Int): String {
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            return Constants.format.format(calendar.time)
        }
    }
}