package com.example.juegopiedrapapel_o_tijeraapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Generar elección aleatoria de la computadora
   public void AbrirPapelPiedraTijera(View v) {
        Intent intent = new Intent(this, PiedrapapelOTijeraActivity.class);
        startActivity(intent);
   }
  public void AbrirDados(View v){
        Intent intent = new Intent(this, DadosActivity.class);
        startActivity(intent);
  }
  public void AbrirAdivinansa(View v){
        Intent intent = new Intent(this, AdivinasaActivity.class);
        startActivity(intent);
  }
}
