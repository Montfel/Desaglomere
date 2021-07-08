package com.montfel.desaglomere.ui.quadra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.montfel.desaglomere.R;

public class QuadraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra);
        getSupportActionBar().setTitle(R.string.quadra);
    }
}