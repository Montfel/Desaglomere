package com.montfel.desaglomere.activity.jogos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.montfel.desaglomere.R;

public class JogosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);
        getSupportActionBar().setTitle(R.string.jogos);
    }
}