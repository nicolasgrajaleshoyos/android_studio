package com.example.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundoActivitid extends AppCompatActivity {
    private TextView txt_hola;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo_activitid);
        txt_hola = findViewById(R.id.txt_hola);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("datos");
        txt_hola.setText("Nombre: " + nombre);

    }
    public void anterior(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}