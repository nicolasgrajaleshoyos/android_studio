package com.example.convertidormonedaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner esp1;
    private Spinner esp2;
    private EditText edt1;
    private EditText edt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        esp1 = findViewById(R.id.esp_Desde);
        esp2 = findViewById(R.id.esp_Hasta);
        edt1 = findViewById(R.id.edt_Desde);
        edt2 = findViewById(R.id.edt_Hasta);
        String[] opciones = {"COP", "USD", "MXN", "EUR","JPY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        esp1.setAdapter(adapter);
        esp2.setAdapter(adapter);
    }
    public void convertir(View view) {
        String moneda1 = esp1.getSelectedItem().toString();
        String moneda2 = esp2.getSelectedItem().toString();
        double valor1 = Double.parseDouble(edt1.getText().toString());
        double resultado = 0;
        if (moneda1.equals("COP") && moneda2.equals("USD")) {
            resultado = valor1 / 4000;
        }
        else if (moneda1.equals("COP") && moneda2.equals("MXN")) {
            resultado = valor1 / 20;
        }
        else if (moneda1.equals("COP") && moneda2.equals("EUR")) {
            resultado = valor1 / 4500;
        }
        else if (moneda1.equals("COP") && moneda2.equals("JPY")) {
            resultado = valor1 / 4.5;
        }
        else if (moneda1.equals("USD") && moneda2.equals("COP")) {
            resultado = valor1 * 4000;
        }
        else if (moneda1.equals("USD") && moneda2.equals("MXN")) {
            resultado = valor1 * 20;
        }
        else if (moneda1.equals("USD") && moneda2.equals("EUR")) {
            resultado = valor1 * 0.95;
        }
        else if (moneda1.equals("USD") && moneda2.equals("JPY")) {
            resultado = valor1 * 120;
        }
        else if (moneda1.equals("MXN") && moneda2.equals("COP")) {
            resultado = valor1 * 20;
        }
        else if (moneda1.equals("MXN") && moneda2.equals("USD")) {
            resultado = valor1 / 20;
        }
        else if (moneda1.equals("MXN") && moneda2.equals("EUR")) {
            resultado = valor1 * 0.05;
        }
        else if (moneda1.equals("MXN") && moneda2.equals("JPY")) {
            resultado = valor1 * 10;
        }
        else if (moneda1.equals("EUR") && moneda2.equals("COP")) {
            resultado = valor1 * 4500;
        }
        else if (moneda1.equals("EUR") && moneda2.equals("USD")) {
            resultado = valor1 / 0.95;
        }
        else if (moneda1.equals("EUR") && moneda2.equals("MXN")) {
            resultado = valor1 / 0.05;
        }
         else if (moneda1.equals("EUR") && moneda2.equals("JPY")) {
             resultado = valor1 * 120;
         }
         else if (moneda1.equals("JPY") && moneda2.equals("COP")) {
             resultado = valor1 * 4.5;

        }
         else if (moneda1.equals("JPY") && moneda2.equals("USD")) {
             resultado = valor1 / 120;
        }
         else if (moneda1.equals("JPY") && moneda2.equals("MXN")) {
             resultado = valor1 / 10;
        }
         else if (moneda1.equals("JPY") && moneda2.equals("EUR")) {
             resultado = valor1 / 120;
        }

        edt2.setText(String.format("%.2f", resultado));
    }

}