package com.example.a4_app_juegoinfantil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2_Nivel_1 extends AppCompatActivity {
    private TextView tv_nombre, tv_score;
    private ImageView tv_uno, tv_dos, tv_vidas;
    private EditText et_respuesta;
    private MediaPlayer mp, mp_bad, mp_great;
    public int resultado, score, num_aleatorio, vidas = 3, num_aleatorio2;
    public String nombrePlayer, score_string, vidas_string;
    String[] number = {"cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_nivel1);
        tv_nombre = findViewById(R.id.txt2_Nombre);
        tv_score = findViewById(R.id.txt2_Score);
        tv_uno = findViewById(R.id.img2_num1);
        tv_dos = findViewById(R.id.img2_num2);
        tv_vidas = findViewById(R.id.Img_Vidas);
        et_respuesta = findViewById(R.id.Respuesta2);

        mp = MediaPlayer.create(this, R.raw.a2_goats);
        mp.start();
        mp.isLooping();

        mp_great = MediaPlayer.create(this, R.raw.good);
        mp_bad = MediaPlayer.create(this, R.raw.bad);
        numAleatorio();
        String nombre = getIntent().getStringExtra("nombre");
        tv_nombre.setText("Jugador: " + nombre);

    }

    public void numAleatorio() {
        num_aleatorio = (int) (Math.random() * 10);
        num_aleatorio2 = (int) (Math.random() * 10);
        resultado = num_aleatorio + num_aleatorio2;

        if (num_aleatorio < 10) {

            for (int i = 0; i < number.length; i++) {
                int id = getResources().getIdentifier(number[i], "drawable", getPackageName());
                if (num_aleatorio == i) {
                    tv_uno.setImageResource(id);
                }
                if (num_aleatorio2 == i) {
                    tv_dos.setImageResource(id);
                }
            }
        } else {
            numAleatorio();
        }

    }

    public void Calcular() {
        String respuesta_string = et_respuesta.getText().toString();
        int respuesta_int = Integer.parseInt(respuesta_string);
        if (respuesta_int == resultado) {
            mp_great.start();
            score++;
            score_string = String.valueOf(score);
            tv_score.setText(score_string);
            numAleatorio();
        } else {
            mp_bad.start();
            vidas--;
            vidas_string = String.valueOf(vidas);
        }
    }

    public void validacionDevidas() {
        switch (vidas) {
            case 3:
                tv_vidas.setImageResource(R.drawable.tresvidas);
                break;
            case 2:
                tv_vidas.setImageResource(R.drawable.dosvidas);
                Toast.makeText(this, "Te quedan 2 vidas", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                tv_vidas.setImageResource(R.drawable.unavida);
                Toast.makeText(this, "Te quedan 1 vida", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(this, "Perdiste", Toast.LENGTH_SHORT).show();
                mp.stop();
                mp.release();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;


        }

    }

    public void comprobar(View v) {
        String respuesta = et_respuesta.getText().toString();
        if (!respuesta.isEmpty()) {
            int respuesta_jugador = Integer.parseInt(respuesta);
            if (respuesta_jugador == resultado) {
                mp_great.start();
                score++;
                tv_score.setText("score:" + score);
                et_respuesta.setText("");
            } else {
                mp_bad.start();
                vidas--;
                validacionDevidas();
                et_respuesta.setText("");

            }
            numAleatorio();
        }else{
            Toast.makeText(this,"Por favor ingresa una respuesta",Toast.LENGTH_SHORT).show();
        }
    }
}
