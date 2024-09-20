package com.example.calcularimcapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
 private EditText edt1;
 private EditText edt2;
 private Spinner sp1;
 private Spinner sp2;
 private TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edt_Peso);
        edt2 = findViewById(R.id.edt_Altura);
        sp1 = findViewById(R.id.esp_Peso);
        sp2 = findViewById(R.id.esp_Altura);
        tv1 = findViewById(R.id.txt_Result);
        String[] Peso = {"Kg", "Lb"};
        String[] Altura = {"m", "in"};
        sp1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Peso));
        sp2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Altura));
    }
    public void Calcular(View v) {

        double peso = Double.parseDouble(edt1.getText().toString());
        double altura = Double.parseDouble(edt2.getText().toString());
        String pes = sp1.getSelectedItem().toString();
        String alt = sp2.getSelectedItem().toString();
        double imc = 0;
        if (pes.equals("Kg") && alt.equals("m")) {
            imc = peso / (altura * altura);
        }
       else  if (pes.equals("Lb") && alt.equals("m")) {
            imc = (peso * 0.453592) / (altura * altura);
        }
        else if (pes.equals("Kg") && alt.equals("in")) {
            imc = (peso ) / ((altura * 0.0254)*(altura * 0.0254));
        }
       else  if (pes.equals("Lb") && alt.equals("in")) {
            imc = (peso * 0.453592) / ((altura * 0.0254)*(altura * 0.0254));
        }
        tv1.setText("Resultado: "+String.format("%.2f", imc));
         if (imc < 18.5) {
            Toast.makeText(this, "bajo do peso", Toast.LENGTH_LONG).show();
        }
       else if (imc >= 18.5 && imc <= 24.9) {
            Toast.makeText(this, "peso normal", Toast.LENGTH_LONG).show();
        }
        else if (imc >= 25 && imc <= 29.9) {
            Toast.makeText(this, "sobrepeso", Toast.LENGTH_LONG).show();
        }
        else if (imc >= 30 && imc <= 34.9) {
            Toast.makeText(this, "obesidade grado 1", Toast.LENGTH_LONG).show();
        }
        else if (imc >= 35 && imc <= 39.9) {
            Toast.makeText(this, "obesidade grado 2", Toast.LENGTH_LONG).show();
        }
        else if (imc >= 40) {
            Toast.makeText(this, "obesidade grado 3", Toast.LENGTH_LONG).show();
        }

    }
    public void SiguienteActivity(View view){
        Intent intent = new Intent(this, intrucciones.class);
        startActivity(intent);
    }
}