package com.example.juegopiedrapapel_o_tijeraapp;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PiedrapapelOTijeraActivity extends AppCompatActivity {
    private SoundPool sp;
    private int SonidoReproduccion;
    private SoundPool sp2;
    private int SonidoReproduccion2;
    private SoundPool sp3;
    private int SonidoReproduccion3;
    private int wins = 0;
    private int losses = 0;
    private int ties = 0;
    private int tiempo = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piedrapapel_otijera);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        SonidoReproduccion = sp.load(this, R.raw.victoria, 1);
        sp2 = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        SonidoReproduccion2 = sp2.load(this, R.raw.error, 1);
        sp3 = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        SonidoReproduccion3 = sp3.load(this, R.raw.draw, 1);

        ImageButton rockButton = findViewById(R.id.rockButton);
        ImageButton paperButton = findViewById(R.id.paperButton);
        ImageButton scissorsButton = findViewById(R.id.scissorsButton);
        TextView resultTextView = findViewById(R.id.feedbackTextView);
        ImageView playerImage = findViewById(R.id.imagePlayerView);
        ImageView computerImage = findViewById(R.id.imageRobotView3);
        TextView scoreTextView = findViewById(R.id.attemptsTextView);

        View.OnClickListener onClickListener = view -> {
            String userChoice = "";
            String computerChoice = getRandomChoice();

            if (view.getId() == R.id.rockButton) {
                userChoice = "Piedra";
                playerImage.setImageResource(R.drawable.piedra);
            } else if (view.getId() == R.id.paperButton) {
                userChoice = "Papel";
                playerImage.setImageResource(R.drawable.papel);
            } else if (view.getId() == R.id.scissorsButton) {
                userChoice = "Tijera";
                playerImage.setImageResource(R.drawable.tijera);
            }

            if (computerChoice.equals("Piedra")) {
                computerImage.setImageResource(R.drawable.piedra);
            } else if (computerChoice.equals("Papel")) {
                computerImage.setImageResource(R.drawable.papel);
            } else if (computerChoice.equals("Tijera")) {
                computerImage.setImageResource(R.drawable.tijera);
            }

            String result = getResult(userChoice, computerChoice);
            resultTextView.setText("Resultado: " + result);
            updateScore(result, scoreTextView);
        };

        rockButton.setOnClickListener(onClickListener);
        paperButton.setOnClickListener(onClickListener);
        scissorsButton.setOnClickListener(onClickListener);

    }

    // Generar elección aleatoria de la computadora
    private String getRandomChoice() {
        String[] choices = {"Piedra", "Papel", "Tijera"};
        Random random = new Random();
        return choices[random.nextInt(choices.length)];
    }

    // Determinar el resultado del juego
    private String getResult(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "Empate";
        } else if ((userChoice.equals("Piedra") && computerChoice.equals("Tijera")) ||
                (userChoice.equals("Papel") && computerChoice.equals("Piedra")) ||
                (userChoice.equals("Tijera") && computerChoice.equals("Papel"))) {
            return "Victoria";
        } else {
            return "Derrota";
        }
    }

    private void updateScore(String result, TextView scoreTextView) {
        switch (result) {
            case "Victoria":
                wins++;
                sp.play(SonidoReproduccion, 1, 1, 0, 0, 1);
                break;
            case "Derrota":
                losses++;
                sp2.play(SonidoReproduccion, 1, 1, 0, 0, 1);
                break;
            case "Empate":
                ties++;
                sp3.play(SonidoReproduccion, 1, 1, 0, 0, 1);
                break;
        }
        scoreTextView.setText("Ganadas: " + wins + ", Perdidas: " + losses + ", Empates: " + ties);
    }
}
