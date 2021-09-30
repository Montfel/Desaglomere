package com.montfel.desaglomere.activity.festas;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomere.R;
import com.montfel.desaglomere.helper.Data;
import com.montfel.desaglomere.helper.Horario;

public class FestasActivity extends AppCompatActivity {

    private RadioGroup rgPredios;
    private TextView textView, tvHorarioFestas, tvDataFestas;

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

        tvHorarioFestas.setOnClickListener(v -> new Horario((TextView) v).getTimePickerDialog().show());

        tvDataFestas.setOnClickListener(v -> new Data((TextView) v).getDatePickerDialog().show());


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
}