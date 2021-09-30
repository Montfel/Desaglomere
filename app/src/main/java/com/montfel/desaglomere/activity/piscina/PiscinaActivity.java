package com.montfel.desaglomere.activity.piscina;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomere.R;
import com.montfel.desaglomere.helper.Data;
import com.montfel.desaglomere.helper.Horario;

public class PiscinaActivity extends AppCompatActivity {

    private TextView tvHorarioPiscina, tvDataPiscina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piscina);
        setTitle(R.string.piscina);

        inicializaCampos();

        tvHorarioPiscina.setOnClickListener(v -> new Horario((TextView) v).getTimePickerDialog().show());
        tvDataPiscina.setOnClickListener(v -> new Data((TextView) v).getDatePickerDialog().show());
    }

    private void inicializaCampos() {
        tvHorarioPiscina = findViewById(R.id.tvHorarioPiscina);
        tvDataPiscina = findViewById(R.id.tvDataPiscina);
    }
}