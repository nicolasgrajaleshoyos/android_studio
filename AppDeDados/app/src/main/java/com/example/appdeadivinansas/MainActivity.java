package com.example.appdeadivinansas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView dado1, dado2;
    private TextView resultado;
    private Button btnLanzar;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dado1 = findViewById(R.id.dado1);
        dado2 = findViewById(R.id.dado2);
        resultado = findViewById(R.id.resultado);
        btnLanzar = findViewById(R.id.btnLanzar);
        random = new Random();

        btnLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarDados();
            }
        });
    }

    private void lanzarDados() {
        int valorDado1 = random.nextInt(6) + 1;
        int valorDado2 = random.nextInt(6) + 1;

        int idDado1 = obtenerImagenDado(valorDado1);
        int idDado2 = obtenerImagenDado(valorDado2);

        dado1.setImageResource(idDado1);
        dado2.setImageResource(idDado2);

        if (valorDado1 == valorDado2) {
            resultado.setText("Ganaste. Los dados muestran: " + valorDado1 + " y " + valorDado2);
        } else {
            resultado.setText("Perdiste. Los dados muestran: " + valorDado1 + " y " + valorDado2);
        }
    }

    private int obtenerImagenDado(int valor) {
        switch (valor) {
            case 1:
                return R.drawable.dice1;
            case 2:
                return R.drawable.dice2;
            case 3:
                return R.drawable.dice3;
            case 4:
                return R.drawable.dice4;
            case 5:
                return R.drawable.dice5;
            case 6:
                return R.drawable.dice6;
            default:
                return R.drawable.dice1;
        }
    }
}
