package com.montfel.desaglomere;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.widget.TextView;

import java.util.Calendar;

public class Horario {
    private TimePickerDialog timePickerDialog;

    public Horario(TextView textView) {
        textView.setText(getTodaysHour());
        initTimePicker(textView);
    }

    public TimePickerDialog getTimePickerDialog() {
        return timePickerDialog;
    }

    private String getTodaysHour() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return makeTimeString(hour, minute);
    }

    private void initTimePicker(TextView textView) {
        TimePickerDialog.OnTimeSetListener timeSetListener = (view, hourOfDay, minute) -> {
            String time = makeTimeString(hourOfDay, minute);
            textView.setText(time);
        };
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(textView.getContext(), timeSetListener,
                hour, minute, true);
    }

    @SuppressLint("DefaultLocale")
    private String makeTimeString(int hourOfDay, int minute) {
        return String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
    }
}
