package com.montfel.desaglomerese.activity.jogos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.montfel.desaglomerese.R;

public class JogosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);
        setTitle(R.string.jogos);
    }
}