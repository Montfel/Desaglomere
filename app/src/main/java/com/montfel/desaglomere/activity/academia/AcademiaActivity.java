package com.montfel.desaglomere.activity.academia;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.montfel.desaglomere.adapter.AcademiaAdapter;
import com.montfel.desaglomere.helper.Horario;
import com.montfel.desaglomere.R;
import com.montfel.desaglomere.model.AcademiaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AcademiaActivity extends AppCompatActivity {
    private TextView tvHorarioAcademia;
    private Horario horario;
    private RecyclerView rvListaAcademia;
    private AcademiaAdapter academiaAdapter;
    private List<AcademiaModel> listaAcademia = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.academia);

        tvHorarioAcademia = findViewById(R.id.tvHorarioPiscina);
        rvListaAcademia = findViewById(R.id.rvListaAcademia);

        horario = new Horario(tvHorarioAcademia);
    }

    public void carregarListaAcademia() {
        AcademiaModel academia1 = new AcademiaModel();
        academia1.setHorario("10:30");
        listaAcademia.add(academia1);

        AcademiaModel academia2 = new AcademiaModel();
        academia2.setHorario("20:45");
        listaAcademia.add(academia2);

        academiaAdapter = new AcademiaAdapter(listaAcademia);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvListaAcademia.setLayoutManager(layoutManager);
        rvListaAcademia.setHasFixedSize(true);
        rvListaAcademia.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(),
                LinearLayout.VERTICAL)
        );
        rvListaAcademia.setAdapter(academiaAdapter);

    }

    @Override
    protected void onStart() {
        carregarListaAcademia();
        super.onStart();
    }

    public void openTimePicker(View view) {
        horario.getTimePickerDialog().show();
    }
}