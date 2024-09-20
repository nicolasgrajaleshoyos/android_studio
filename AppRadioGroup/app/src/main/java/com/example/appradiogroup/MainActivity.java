package com.example.appradiogroup;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et1 =  findViewById(R.id.txt_valor1);
        et2 =  findViewById(R.id.txt_valor2);
        rb1 =  findViewById(R.id.rb_suma);
        rb2 =  findViewById(R.id.rb_resta);
        rb3 =  findViewById(R.id.rb_multiplicacion);
        rb4 =  findViewById(R.id.rb_Divicion);
        tv1 =  findViewById(R.id.txt_resultado);

    }
    public void calcular(View v) {
        int valor1 = Integer.parseInt(et1.getText().toString());
        int valor2 = Integer.parseInt(et2.getText().toString());
        int resultado = 0;
        if (rb1.isChecked()) {
            resultado = valor1 + valor2;
        }
        if (rb2.isChecked()) {
            resultado = valor1 - valor2;
        }
        if (rb3.isChecked()) {
            resultado = valor1 * valor2;
        }


        if (rb4.isChecked()) {
            if(valor1 == 0 || valor2 == 0){
                Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
            }else {
                resultado = valor1 / valor2;
            }
        }

        tv1.setText("Resultado: "+String.valueOf(resultado));
    }
}