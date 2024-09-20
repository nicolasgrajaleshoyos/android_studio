package com.example.encuestaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb1 = findViewById(R.id.rb_Colombia);
        rb2 = findViewById(R.id.rb_Mexico);
        rb3 = findViewById(R.id.rb_EEUU);
        rb4 = findViewById(R.id.rb_Messi);
        rb5 = findViewById(R.id.txt_Neymar); // Cambiado de txt_Neymar a rb_Neymar
        rb6 = findViewById(R.id.rb_CR7);
        cb1 = findViewById(R.id.cb_Amarillo);
        cb2 = findViewById(R.id.cb_Azul); // Asegúrate de que este CheckBox esté definido en tu XML
        cb3 = findViewById(R.id.cb_Rojo);
        cb4 = findViewById(R.id.cb_Morado);
    }

    public void enviar(View view) {
        String pais = "";
        String jugador = "";
        String colores = "";

        // Determina el país seleccionado
        if (rb1.isChecked()) {
            pais = rb1.getText().toString();
        } else if (rb2.isChecked()) {
            pais = rb2.getText().toString();
        } else if (rb3.isChecked()) {
            pais = rb3.getText().toString();
        }

        // Determina el jugador seleccionado
        if (rb4.isChecked()) {
            jugador = rb4.getText().toString();
        } else if (rb5.isChecked()) {
            jugador = rb5.getText().toString();
        } else if (rb6.isChecked()) {
            jugador = rb6.getText().toString();
        }

        // Determina los colores seleccionados
        if (cb1.isChecked()) {
            colores += cb1.getText().toString() + " ";
        }
        if (cb2.isChecked()) {
            colores += cb2.getText().toString() + " ";
        }
        if (cb3.isChecked()) {
            colores += cb3.getText().toString() + " ";
        }
        if (cb4.isChecked()) {
            colores += cb4.getText().toString() + " ";
        }

        // Crea el Intent para enviar los datos
        Intent intent = new Intent(this, ResumenEncuesta.class);
        intent.putExtra("pais", pais);
        intent.putExtra("jugador", jugador);
        intent.putExtra("colores", colores.trim()); // Elimina espacios en blanco al final

        startActivity(intent);
    }
}
