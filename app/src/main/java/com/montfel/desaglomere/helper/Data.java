package com.montfel.desaglomere.helper;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Data {
    private DatePickerDialog datePickerDialog;

    public Data(TextView textView) {
        textView.setText(getTodaysDate());
        initDatePicker(textView);
    }

    public DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        month++;
        int year = cal.get(Calendar.YEAR);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(TextView textView) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = makeDateString(dayOfMonth, month, year);
                textView.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        month++;
        int year = cal.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(textView.getContext(), dateSetListener, year, month, day);

    }

    @SuppressLint("DefaultLocale")
    private String makeDateString(int dayOfMonth, int month, int year) {
        return String.format("%02d", dayOfMonth) + "/" + String.format("%02d", month) + "/" + year;
    }
}
