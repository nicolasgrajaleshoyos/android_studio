package com.example.appmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button play_pause;
    Button btn_repetir;
    ImageView imgv;

    int repetir = 2;
    int posicion = 0;

    MediaPlayer[] arrayMp = new MediaPlayer[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = findViewById(R.id.bt_Play);
        btn_repetir = findViewById(R.id.bt_Repetir);
        imgv = findViewById(R.id.imageView4);

        arrayMp[0] = MediaPlayer.create(this, R.raw.race);
        arrayMp[1] = MediaPlayer.create(this, R.raw.tea);
        arrayMp[2] = MediaPlayer.create(this, R.raw.sound);
        arrayMp[3] = MediaPlayer.create(this, R.raw.blessd);
        arrayMp[4] = MediaPlayer.create(this, R.raw.futbol);
        arrayMp[5] = MediaPlayer.create(this, R.raw.onofone);
        arrayMp[6] = MediaPlayer.create(this, R.raw.ryan_t_blessd);

        actualizarImagen();
    }

    private void actualizarImagen() {
        int[] imagenes = {
                R.drawable.portada1,
                R.drawable.portada2,
                R.drawable.portada3,
                R.drawable.bleesd2,
                R.drawable.ryan,
                R.drawable.blessd_y_maluma,
                R.drawable.blessdandryanjpg
        };
        imgv.setImageResource(imagenes[posicion]);
    }

    public void PlayPause(View view) {
        if (arrayMp[posicion].isPlaying()) {
            arrayMp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        } else {
            arrayMp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View view) {
        if (arrayMp[posicion] != null) {
            arrayMp[posicion].stop();
            reiniciarMediaPlayers();
            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            actualizarImagen();
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    private void reiniciarMediaPlayers() {
        arrayMp[0] = MediaPlayer.create(this, R.raw.race);
        arrayMp[1] = MediaPlayer.create(this, R.raw.tea);
        arrayMp[2] = MediaPlayer.create(this, R.raw.sound);
        arrayMp[3] = MediaPlayer.create(this, R.raw.blessd);
        arrayMp[4] = MediaPlayer.create(this, R.raw.futbol);
        arrayMp[5] = MediaPlayer.create(this, R.raw.onofone);
        arrayMp[6] = MediaPlayer.create(this, R.raw.ryan_t_blessd);
    }

    public void Repetir(View view) {
        if (repetir == 1) {
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            arrayMp[posicion].setLooping(false);
            repetir = 2;
            Toast.makeText(this, "No Repetir", Toast.LENGTH_SHORT).show();
        } else {
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            arrayMp[posicion].setLooping(true);
            repetir = 1;
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
        }
    }

    public void Siguiente(View view) {
        if (posicion < arrayMp.length - 1) {
            cambiarCancion(1);
        } else {
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void Anterior(View view) {
        if (posicion > 0) {
            cambiarCancion(-1);
        } else {
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }

    private void cambiarCancion(int cambio) {
        if (arrayMp[posicion].isPlaying()) {
            arrayMp[posicion].stop();
            reiniciarMediaPlayers();
        }
        posicion += cambio;
        if (posicion >= 0 && posicion < arrayMp.length) {
            arrayMp[posicion].start();
            actualizarImagen();
        }
    }
}
