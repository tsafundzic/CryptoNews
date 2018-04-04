package com.example.cobe.cryptonews.dialog;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.example.cobe.cryptonews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cobe on 04/04/2018.
 */

public class DialogDatePicker {

    private static String date;

    public static String showDatePicker(final Context from) {

        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                date = format.format(calendar.getTime());
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(from, R.style.AppTheme, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

        return date;
    }
}
