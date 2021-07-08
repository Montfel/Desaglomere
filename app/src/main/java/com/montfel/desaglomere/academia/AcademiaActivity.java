package com.montfel.desaglomere.academia;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomere.Horario;
import com.montfel.desaglomere.R;

import java.util.Objects;

public class AcademiaActivity extends AppCompatActivity {
    private TextView tvHorarioAcademia;
    private Horario horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.academia);
        tvHorarioAcademia = findViewById(R.id.tvHorarioPiscina);
        horario = new Horario(tvHorarioAcademia);
    }

    public void openTimePicker(View view) {
        horario.getTimePickerDialog().show();
    }
}