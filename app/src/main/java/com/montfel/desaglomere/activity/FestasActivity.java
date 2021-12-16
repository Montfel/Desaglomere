package com.montfel.desaglomere.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomere.R;
import com.montfel.desaglomere.helper.Data;
import com.montfel.desaglomere.helper.Horario;

public class FestasActivity extends AppCompatActivity {

    private RadioGroup rgPredios;
    private TextView textView, tvHorarioFestas, tvDataFestas;
    private Horario horario;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festas);
        setTitle(R.string.festas);

        rgPredios = findViewById(R.id.rgPredios);
        textView = findViewById(R.id.textView);
        textView.setText(R.string.cana);
        tvHorarioFestas = findViewById(R.id.tvHorarioFestas);
        tvDataFestas = findViewById(R.id.tvDataFestas);
        horario = new Horario(tvHorarioFestas);
        data = new Data(tvDataFestas);

        rgPredios.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbSamambaia:
                    textView.setText(R.string.samambaia);
                    break;
                case R.id.rbHibisco:
                    textView.setText(R.string.hibisco);
                    break;
                case R.id.rbCana:
                    textView.setText(R.string.cana);
                    break;
            }
        });
    }

    public void openTimePicker(View view) {
        horario.getTimePickerDialog().show();
    }

    public void openDatePicker(View view) {
        data.getDatePickerDialog().show();
    }
}