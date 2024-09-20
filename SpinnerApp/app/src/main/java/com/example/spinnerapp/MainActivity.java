package com.example.spinnerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private Spinner sp1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.txt_valor1);
        et2 = findViewById(R.id.txt_Valor2);
        sp1 = findViewById(R.id.esp_Opciones);
        tv1 = findViewById(R.id.text_Resultado);
        String[] opciones = {"Suma", "Resta", "Multiplicacion", "Divicion"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_style,opciones);
        sp1.setAdapter(adapter);
    }

    public void Calcular(View view) {
        // Verificar si ambos campos tienen datos
        if (!et1.getText().toString().isEmpty() && !et2.getText().toString().isEmpty()) {

            try {
                BigInteger valor1 = new BigInteger(et1.getText().toString());
                BigInteger valor2 = new BigInteger(et2.getText().toString());

                String seleccion = sp1.getSelectedItem().toString();

                if (seleccion.equals("Suma")) {
                    BigInteger suma = valor1.add(valor2);
                    tv1.setText("Resultado: " + suma.toString());

                } else if (seleccion.equals("Resta")) {
                    BigInteger resta = valor1.subtract(valor2);
                    tv1.setText("Resultado: " + resta.toString());
                } else if (seleccion.equals("Multiplicacion")) {
                    BigInteger multiplicacion = valor1.multiply(valor2);
                    tv1.setText("Resultado: " + multiplicacion.toString());
                } else if (seleccion.equals("Divicion")) {
                    if (!valor2.equals(BigInteger.ZERO)) { // Verificar que valor2 no sea cero
                        BigInteger division = valor1.divide(valor2);
                        tv1.setText("Resultado: " + division.toString());
                    } else {
                        tv1.setText("Error: no se puede dividir por cero");
                    }
                }
            } catch (NumberFormatException e) {
                tv1.setText("Error: formato de número inválido");
            }

        } else {
            tv1.setText("Error: campos vacíos");
        }
    }
}

