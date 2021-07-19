package com.montfel.desaglomere.activity.academia;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.montfel.desaglomere.helper.AcademiaDAO;
import com.montfel.desaglomere.helper.Horario;
import com.montfel.desaglomere.helper.RecyclerItemClickListener;
import com.montfel.desaglomere.model.AcademiaModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AcademiaActivity extends AppCompatActivity {
    private TextView tvHorarioAcademia;
    private Horario horario;
    private RecyclerView rvListaAcademia;
    private AcademiaAdapter academiaAdapter;
    private List<AcademiaModel> listaAcademia = new ArrayList<>();
    private AcademiaModel academiaSelecionada, academiaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.academia);

        tvHorarioAcademia = findViewById(R.id.tvHorarioPiscina);
        rvListaAcademia = findViewById(R.id.rvListaAcademia);

        horario = new Horario(tvHorarioAcademia);

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
                                openTimePicker(view);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                academiaSelecionada = listaAcademia.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(
                                        AcademiaActivity.this);

                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir o horário " +
                                        academiaSelecionada.getHorario() + "?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        AcademiaDAO academiaDAO = new AcademiaDAO(
                                                getApplicationContext());
                                        if (academiaDAO.delete(academiaSelecionada)) {
                                            carregarListaAcademia();
                                            Toast.makeText(getApplicationContext(),
                                                    "Sucesso ao excluir horário!",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Erro ao excluir horário!",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                                dialog.setNegativeButton("Não", null);
                                dialog.create();
                                dialog.show();
                            }
                        }
                )
        );
    }

    public void carregarListaAcademia() {
        AcademiaDAO academiaDAO = new AcademiaDAO(getApplicationContext());
        listaAcademia = academiaDAO.read();

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

    public void salvarTeste(View view) {
        AcademiaDAO academiaDAO = new AcademiaDAO(getApplicationContext());
        AcademiaModel academiaModel = new AcademiaModel();
        String horario = tvHorarioAcademia.getText().toString();
        academiaModel.setHorario(horario);

        if (academiaAtual != null) {
            academiaModel.setId(academiaAtual.getId());
            if (academiaDAO.update(academiaModel)) {
                Toast.makeText(getApplicationContext(), "Sucesso ao salvar",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            if (academiaDAO.create(academiaModel)) {
                Toast.makeText(getApplicationContext(), "Sucesso ao salvar",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa",
                        Toast.LENGTH_SHORT).show();
            }
        }
        carregarListaAcademia();
        academiaAtual = null;
    }
}