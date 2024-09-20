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

public class MainActivity extends AppCompatActivity {

        private ImageView tv_player;
        private int aleatorio ;
        private MediaPlayer mp;
        private String nombre_string;
        private TextView tv_score;
        private EditText et_nombre;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tv_player = findViewById(R.id.img1_Player);
            et_nombre = findViewById(R.id.txt1_Nombre);
            tv_score = findViewById(R.id.txt1_Score);
            aleatorio =  (int) (Math.random() *5);
            ImagenAleatoria();
            mp = MediaPlayer.create(this,R.raw.a1_song);
            mp.start();
            mp.isLooping();

        }
    public void play(View v){
        nombre_string = et_nombre.getText().toString();
        if(!nombre_string.isEmpty()){
            Intent i = new Intent(this,MainActivity2_Nivel_1.class);
            i.putExtra("nombre",et_nombre.getText().toString());
            startActivity(i);
            mp.stop();
            mp.release();
        }else{
            Toast.makeText(this,"Por favor ingresa tu nombre",Toast.LENGTH_SHORT).show();
            et_nombre.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_nombre, InputMethodManager.SHOW_IMPLICIT);
        }

        }
        public void ImagenAleatoria(){
            switch (aleatorio){
                case 1:
                    tv_player.setImageResource(R.drawable.manzana);
                    break;
                case 2:
                    tv_player.setImageResource(R.drawable.mango);
                    break;
                case 3:
                    tv_player.setImageResource(R.drawable.naranja);
                    break;
                case 4:
                    tv_player.setImageResource(R.drawable.sandia);
                    break;
                case 5:
                    tv_player.setImageResource(R.drawable.uva);
                    break;
            }
        }

    }