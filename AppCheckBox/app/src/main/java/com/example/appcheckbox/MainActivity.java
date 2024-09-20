package com.example.appcheckbox;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edt1;
    private  EditText edt2;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.txt_Decimal1);
        edt2 = findViewById(R.id.txt_Decimal2);
        cb1 = findViewById(R.id.check_Suma);
        cb2 = findViewById(R.id.check_Resta);
        cb3 = findViewById(R.id.check_Multiplicacion);
        tv1 = findViewById(R.id.text_Result);
    }
    public void Calcular(View view){
        String valor1_string = edt1.getText().toString();
        String valor2_string = edt2.getText().toString();

        float valor1_int = Float.parseFloat(valor1_string);
        float valor2_int = Float.parseFloat(valor2_string);

        String resultado = "";
        if(cb1.isChecked())
        {
            float suma = valor1_int + valor2_int;
            String res = String.valueOf(suma);
            resultado = "La suma es: " + res +"\n";
        }
        if(cb2.isChecked())
        {
            float resta = valor1_int - valor2_int;
            String res = String.valueOf(resta);
            resultado = resultado + "La resta es: " + res + "\n";
        }
        if(cb3.isChecked()) {
            float multi = valor1_int * valor2_int;
            String res = String.valueOf(multi);
            resultado = resultado + "La multi es: " + res;
        }
        tv1.setText(resultado);

    }


    }
