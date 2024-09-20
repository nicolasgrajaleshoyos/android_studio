package com.example.appciclodevida;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.txt_Nota1);
        et2 = findViewById(R.id.txt_Nota2);
        et3 = findViewById(R.id.txt_Nota3);
        et4 = findViewById(R.id.txt_Nota4);
        et5 = findViewById(R.id.txt_Nota5);
        tv1 = findViewById(R.id.txt_Promedio);
    }

    public void calcularPromedio(View view) {
        int nota1 = Integer.parseInt(et1.getText().toString());
        int nota2 = Integer.parseInt(et2.getText().toString());
        int nota3 = Integer.parseInt(et3.getText().toString());
        int nota4 = Integer.parseInt(et4.getText().toString());
        int nota5 = Integer.parseInt(et5.getText().toString());
        int promedio =  (nota1 + nota2 + nota3 + nota4 + nota5) / 5;
        tv1.setText("El promedio es: " + promedio);
        // tv1.setTextColor(getResources().getColor(R.color.verde));
        //Toast.makeText(this, "El promedio es: " + promedio, Toast.LENGTH_SHORT).show();
        if (promedio >= 3 && promedio <= 5) {
            Toast.makeText(this, "APROVADO", Toast.LENGTH_SHORT).show();

        } else if(promedio > 5){
            Toast.makeText(this, "Valores incorrectos", Toast.LENGTH_SHORT).show();
        }else if(promedio < 3){
            Toast.makeText(this, "REPROBADO", Toast.LENGTH_SHORT).show();
        }

    }
}