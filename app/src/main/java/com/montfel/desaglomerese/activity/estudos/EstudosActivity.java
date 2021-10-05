package com.montfel.desaglomerese.activity.estudos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomerese.R;

public class EstudosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudos);
        setTitle(R.string.estudos);
    }
}