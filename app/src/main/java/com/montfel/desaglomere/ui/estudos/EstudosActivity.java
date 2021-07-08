package com.montfel.desaglomere.ui.estudos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.montfel.desaglomere.R;

public class EstudosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudos);
        getSupportActionBar().setTitle(R.string.estudos);
    }
}