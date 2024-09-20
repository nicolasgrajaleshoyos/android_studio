package com.example.appcalculadorabasica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView tv_operator;
    private TextView tv_result;
    private int numero1;
    private int numero2;
    private String operator = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_operator = findViewById(R.id.txt_operacion);
        tv_result = findViewById(R.id.txt_Resultado);
    }

    public void NumeroPresionado(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        tv_operator.setText(buttonText);
        if (tv_result.getText().toString().equals("0")) {
            tv_result.setText(buttonText);
        } else {
            tv_result.append(buttonText);
        }


    }

    public void OperacionSeleccionada(View view) {
        Button btnOperacion = (Button) view;
        operator = btnOperacion.getText().toString();
        numero1 = Integer.parseInt(tv_result.getText().toString());//Guardar el primer numero y lo convertimos a double, para realizar operaciones con el.

        String operacionActual = numero1 + " " + operator + " ";
        tv_operator.setText(operacionActual);

        tv_result.setText("0");
    }
    public void limpiar(View view) {
        tv_operator.setText("");
        tv_result.setText("0");
    }
    public void CalcularResultado(View view)
    {
        numero2 = Integer.parseInt(tv_result.getText().toString());

        int resultado = 0;
        switch (operator){
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
            case "/":
                if(numero2 != 0){
                    resultado = numero1 / numero2;
                }else {
                    Toast.makeText(this, "No se puede Dividir por 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }

        //Actualiza el TextView con la operacion completa y el resultado
        String operacionCompleta = numero1 + " " + operator + " " + numero2 + "=";
        tv_operator.setText(operacionCompleta);

        tv_result.setText(String.valueOf(resultado));
    }

}
