package com.example.encuestaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResumenEncuesta extends AppCompatActivity {
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_encuesta);

        tv1 = findViewById(R.id.txt_Resumen);

        // Obtén los datos enviados desde MainActivity
        String pais = getIntent().getStringExtra("pais");
        String jugador = getIntent().getStringExtra("jugador");
        String colores = getIntent().getStringExtra("colores");

        // Configura el texto del TextView
        tv1.setText("Resumen del formulario: \n \n \n" +
                "Su país de residencia es: " + pais + "\n \n" +
                "El mejor jugador del mundo para usted es: " + jugador + "\n \n" +
                "Sus colores favoritos son: " + colores);
    }

    public void volver(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
