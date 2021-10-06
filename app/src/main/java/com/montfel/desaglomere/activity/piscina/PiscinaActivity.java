package com.montfel.desaglomere.activity.piscina;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomere.R;
import com.montfel.desaglomere.helper.Data;
import com.montfel.desaglomere.helper.Horario;

public class PiscinaActivity extends AppCompatActivity {

    private TextView tvHorarioPiscina, tvDataPiscina;
    private Horario horario;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piscina);
        setTitle(R.string.piscina);

        tvHorarioPiscina = findViewById(R.id.tvHorarioPiscina);
        tvDataPiscina = findViewById(R.id.tvDataPiscina);
        horario = new Horario(tvHorarioPiscina);
        data = new Data(tvDataPiscina);
    }

    public void openTimePicker(View view) {
        horario.getTimePickerDialog().show();
    }

    public void openDatePicker(View view) {
        data.getDatePickerDialog().show();
    }
}