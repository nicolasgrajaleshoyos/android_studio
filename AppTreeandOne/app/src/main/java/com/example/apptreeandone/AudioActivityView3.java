package com.example.apptreeandone;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AudioActivityView3 extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    Button btn_sp;
    Button btn_soundPool;
    SoundPool sp;
    int sonido_de_reproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_audio_view3);

        // Referencia al botón en el diseño
        btn_sp = findViewById(R.id.bt_One);
        // Inicializa SoundPool con un máximo de 1 flujo, tipo de audio STREAM_MUSIC y calidad de 1
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);

        // Carga el sonido corto (sound_short) desde los recursos y guarda su ID en 'sonido_de_reproduccion'
        sonido_de_reproduccion = sp.load(this, R.raw.sound_short,1);
    }


    public void siguiente3(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }

    public void MyMediaPlayer(View view) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_long);
        mediaPlayer.start();
    }
    public void StopMediaPlayer(View view)
    {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;

        }

    }
    public void AudioSoundPool(View view)
    {
        // Reproduce el sonido cargado. Los parámetros son:
        // 1. sonido_de_reproduccion: ID del sonido cargado
        // 2. 1: Volumen del canal izquierdo (máximo)
        // 3. 1: Volumen del canal derecho (máximo)
        // 4. 1: Prioridad de reproducción (menor número = mayor prioridad)
        // 5. 0: Número de repeticiones (0 significa que no se repite)
        // 6. 1.0f: Velocidad de reproducción (1.0 = velocidad normal)
        sp.play(sonido_de_reproduccion,1,1,1,0,1);
    }
}