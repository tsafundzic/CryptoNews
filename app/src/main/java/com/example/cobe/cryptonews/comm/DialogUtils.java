package com.example.cobe.cryptonews.comm;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.example.cobe.cryptonews.constants.Constants;

import java.util.Calendar;

/**
 * Created by cobe on 05/04/2018.
 */

public class DialogUtils {


    public static void showDatePicker(Context from, DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(from, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    public static String getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        String date = Constants.format.format(calendar.getTime());
        return date;
    }
}
