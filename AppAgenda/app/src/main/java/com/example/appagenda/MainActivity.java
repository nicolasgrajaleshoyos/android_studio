package com.example.appagenda;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtNombre = findViewById(R.id.txt_Nombre);
        txtDatos = findViewById(R.id.txt_Datos);


    }

    public void Guardar (View view) {
        String nombre = txtNombre.getText().toString();
        String datos = txtDatos.getText().toString();
        SharedPreferences prefs = getSharedPreferences("datos", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(nombre, datos);
        editor.apply();
        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();

    }
    public void Buscar(View view) {
        String nombre = txtNombre.getText().toString();
        SharedPreferences prefs = getSharedPreferences("datos", MODE_PRIVATE);
        String datos = prefs.getString(nombre, "");
        if (datos.isEmpty()) {
            Toast.makeText(this, "No se ENCONTRARON DATOS", Toast.LENGTH_SHORT).show();
            return;
        }
        txtDatos.setText(datos);
    }
}
