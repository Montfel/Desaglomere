package com.montfel.desaglomere.activity.jogos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomere.R;

public class JogosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);
        setTitle(R.string.jogos);
    }
}