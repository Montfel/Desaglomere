package com.montfel.desaglomere.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.montfel.desaglomere.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        if (preferences.contains("ja_abriu_app")) {
            mostraTelaInicial();
        } else {
            adicionarPreferenceJaAbriu(preferences);
            mostraSplash();
        }
    }

    private void mostraSplash() {
        Handler handle = new Handler();
        handle.postDelayed(this::mostraTelaInicial, 2000);
    }

    private void mostraTelaInicial() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void adicionarPreferenceJaAbriu(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ja_abriu_app", true);
        editor.apply();
    }
}