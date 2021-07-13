package com.montfel.desaglomere.ui.ambientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.montfel.desaglomere.R;
import com.montfel.desaglomere.activity.academia.AcademiaActivity;
import com.montfel.desaglomere.activity.estudos.EstudosActivity;
import com.montfel.desaglomere.activity.festas.FestasActivity;
import com.montfel.desaglomere.activity.jogos.JogosActivity;
import com.montfel.desaglomere.activity.piscina.PiscinaActivity;
import com.montfel.desaglomere.activity.quadra.QuadraActivity;

public class AmbientesFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageButton imgbtnAcademia, imgbtnQuadra, imgbtnPiscina,
            imgbtnFestas, imgbtnJogos, imgbtnEstudos;
    private String mParam1;
    private String mParam2;

    public AmbientesFragment() {
        // Required empty public constructor
    }


    public static AmbientesFragment newInstance(String param1, String param2) {
        AmbientesFragment fragment = new AmbientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ambientes, container, false);
        imgbtnAcademia = view.findViewById(R.id.imgbtnAcademia);
        imgbtnQuadra = view.findViewById(R.id.imgbtnQuadra);
        imgbtnPiscina = view.findViewById(R.id.imgbtnPiscina);
        imgbtnFestas = view.findViewById(R.id.imgbtnFestas);
        imgbtnJogos = view.findViewById(R.id.imgbtnJogos);
        imgbtnEstudos = view.findViewById(R.id.imgbtnEstudos);

        imgbtnAcademia.setOnClickListener(this);
        imgbtnQuadra.setOnClickListener(this);
        imgbtnPiscina.setOnClickListener(this);
        imgbtnFestas.setOnClickListener(this);
        imgbtnJogos.setOnClickListener(this);
        imgbtnEstudos.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtnAcademia:
                startActivity(new Intent(getContext(), AcademiaActivity.class));
                break;
            case R.id.imgbtnQuadra:
                startActivity(new Intent(getContext(), QuadraActivity.class));
                break;
            case R.id.imgbtnPiscina:
                startActivity(new Intent(getContext(), PiscinaActivity.class));
                break;
            case R.id.imgbtnFestas:
                startActivity(new Intent(getContext(), FestasActivity.class));
                break;
            case R.id.imgbtnJogos:
                startActivity(new Intent(getContext(), JogosActivity.class));
                break;
            case R.id.imgbtnEstudos:
                startActivity(new Intent(getContext(), EstudosActivity.class));
                break;
        }
    }
}