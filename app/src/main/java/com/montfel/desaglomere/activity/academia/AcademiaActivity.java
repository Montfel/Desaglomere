package com.montfel.desaglomere.activity.academia;

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
    private RecyclerView rvListaAcademia;
    private List<Academia> listaAcademia = new ArrayList<>();
    private Academia academiaSelecionada, academiaAtual;
    private Horario horario;
    private Button btnConfirmarAcademia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);
        setTitle(R.string.academia);

        inicializaCampos();
        horario = new Horario(tvHorarioAcademia);
        configuraBotaoConfirmar();
        configuraHorario();
        configuraClickRecyclerView();
    }

    private void configuraHorario() {
        tvHorarioAcademia.setOnClickListener(v -> horario.getTimePickerDialog().show());
    }

    private void configuraClickRecyclerView() {
        rvListaAcademia.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        rvListaAcademia,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                            }

                            @Override
                            public void onItemClick(View view, int position) {
                                academiaAtual = listaAcademia.get(position);
                                horario.getTimePickerDialog().show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                academiaSelecionada = listaAcademia.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(
                                        AcademiaActivity.this);

                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir o horário " +
                                        academiaSelecionada.getHorario() + "?");
                                dialog.setPositiveButton("Sim", (dialog1, which) -> {
                                    AcademiaDAO academiaDAO = new AcademiaDAO(getApplicationContext());

                                    if (academiaDAO.delete(academiaSelecionada)) {
                                        carregaListaAcademia();
                                        Toast.makeText(getApplicationContext(),
                                                "Sucesso ao excluir horário!",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Erro ao excluir horário!",
                                                Toast.LENGTH_SHORT).show();
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

    private void inicializaCampos() {
        tvHorarioAcademia = findViewById(R.id.tvHorarioPiscina);
        rvListaAcademia = findViewById(R.id.rvListaAcademia);
        btnConfirmarAcademia = findViewById(R.id.btnConfirmarAcademia);
    }

    public void carregaListaAcademia() {
        AcademiaDAO academiaDAO = new AcademiaDAO(getApplicationContext());
        listaAcademia = academiaDAO.read();
        configuraRecyclerView();
    }

    private void configuraRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvListaAcademia.setLayoutManager(layoutManager);
        rvListaAcademia.setHasFixedSize(true);
        rvListaAcademia.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.VERTICAL));
        rvListaAcademia.setAdapter(new AcademiaAdapter(listaAcademia));
    }

    @Override
    protected void onStart() {
        carregaListaAcademia();
        super.onStart();
    }

    public void configuraBotaoConfirmar() {
        btnConfirmarAcademia.setOnClickListener(v -> {
            AcademiaDAO academiaDAO = new AcademiaDAO(getApplicationContext());
            Academia academia = new Academia();
            academia.setHorario(tvHorarioAcademia.getText().toString());

            if (academiaAtual != null) {
                academia.setId(academiaAtual.getId());
                if (academiaDAO.update(academia)) {
                    Toast.makeText(getApplicationContext(), "Sucesso ao salvar",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                if (academiaDAO.create(academia)) {
                    Toast.makeText(getApplicationContext(), "Sucesso ao salvar",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa",
                            Toast.LENGTH_SHORT).show();
                }
            }
            carregaListaAcademia();
            academiaAtual = null;
        });
    }
}