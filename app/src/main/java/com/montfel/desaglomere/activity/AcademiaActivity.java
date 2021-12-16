package com.montfel.desaglomere.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.montfel.desaglomere.R;
import com.montfel.desaglomere.adapter.AcademiaAdapter;
import com.montfel.desaglomere.dao.AcademiaDAO;
import com.montfel.desaglomere.helper.Horario;
import com.montfel.desaglomere.helper.RecyclerItemClickListener;
import com.montfel.desaglomere.model.Academia;

import java.util.ArrayList;
import java.util.List;

public class AcademiaActivity extends AppCompatActivity {
    private TextView tvHorarioAcademia;
    private Horario horario;
    private RecyclerView rvListaAcademia;
    private List<Academia> listaAcademia = new ArrayList<>();
    private Academia academiaSelecionada, academiaAtual;
    private Button btnConfirmarAcademia;
    private AcademiaDAO academiaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);
        setTitle(R.string.academia);
        inicializaCampos();
        configuraBotaoConfirmar();
        configuraHorario();
        configuraRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarListaAcademia();
    }

    private void configuraHorario() {
        tvHorarioAcademia.setOnClickListener(v -> horario.getTimePickerDialog().show());
    }

    private void configuraDialog() {
        new AlertDialog
                .Builder(AcademiaActivity.this)
                .setTitle(R.string.confirmar_exclusao)
                .setMessage(getString(R.string.deseja_excluir_horario) + academiaSelecionada.getHorario() + "?")
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        realizaOperacao(academiaDAO.delete(academiaSelecionada)))
                .setNegativeButton(R.string.no, null)
                .create()
                .show();
    }

    private void inicializaCampos() {
        tvHorarioAcademia = findViewById(R.id.tvHorarioAcademia);
        rvListaAcademia = findViewById(R.id.rvListaAcademia);
        btnConfirmarAcademia = findViewById(R.id.btnConfirmarAcademia);
        horario = new Horario(tvHorarioAcademia);
        academiaDAO = new AcademiaDAO(getApplicationContext());
    }

    public void carregarListaAcademia() {
        listaAcademia = academiaDAO.read();
        rvListaAcademia.setAdapter(new AcademiaAdapter(listaAcademia));
    }

    private void configuraRecyclerView() {
        rvListaAcademia.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListaAcademia.setHasFixedSize(true);
        rvListaAcademia.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.VERTICAL));
        rvListaAcademia.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        rvListaAcademia,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {}

                            @Override
                            public void onItemClick(View view, int position) {
                                academiaAtual = listaAcademia.get(position);
                                horario.getTimePickerDialog().show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                academiaSelecionada = listaAcademia.get(position);
                                configuraDialog();
                            }
                        }
                )
        );
    }

    public void configuraBotaoConfirmar() {
        btnConfirmarAcademia.setOnClickListener(v -> {
            Academia academia = new Academia();
            academia.setHorario(tvHorarioAcademia.getText().toString());

            if (academiaAtual != null) {
                academia.setId(academiaAtual.getId());
                realizaOperacao(academiaDAO.update(academia));
            } else {
                realizaOperacao(academiaDAO.create(academia));
            }

            academiaAtual = null;
        });
    }

    private void realizaOperacao(boolean b) {
        if (b) {
            carregarListaAcademia();
            Toast.makeText(getApplicationContext(), R.string.sucess, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
        }
    }
}