package com.example.juegopiedrapapel_o_tijeraapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AdivinasaActivity extends AppCompatActivity {

    private List<String> wordList = Arrays.asList("ANDROID", "JAVA", "DESARROLLADOR", "MÓVIL", "APLICACIÓN");
    private String selectedWord;
    private String guessedWord;
    private int attempts;
    private List<Character> guessedLetters = new ArrayList<>();

    private TextView wordTextView;
    private EditText guessEditText;
    private Button guessButton;
    private TextView feedbackTextView;
    private TextView attemptsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivinasa);

        wordTextView = findViewById(R.id.wordTextView);
        guessEditText = findViewById(R.id.letterEditText);
        guessButton = findViewById(R.id.guessButton);
        feedbackTextView = findViewById(R.id.feedbackTextView);
        attemptsTextView = findViewById(R.id.attemptsTextView);
        startNewGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGuess();
            }
        });
    }

    private void startNewGame() {
        selectedWord = wordList.get(new Random().nextInt(wordList.size())).toUpperCase();
        guessedWord = "_".repeat(selectedWord.length());
        guessedLetters.clear();
        attempts = 0;
        updateUI();
    }

    private void handleGuess() {
        String guessedLetter = guessEditText.getText().toString().toUpperCase();
        if (guessedLetter.length() == 1 && Character.isLetter(guessedLetter.charAt(0))) {
            char letter = guessedLetter.charAt(0);
            if (guessedLetters.contains(letter)) {
                feedbackTextView.setText("¡Ya adivinaste esa letra!");
                return;
            }

            guessedLetters.add(letter);
            if (selectedWord.contains(String.valueOf(letter))) {
                updateGuessedWord(letter);
                feedbackTextView.setText("¡Buena suposición!");
            } else {
                attempts++;
                feedbackTextView.setText("¡Conjetura equivocada!");
            }
            updateUI();
        } else {
            feedbackTextView.setText("Por favor, introduzca una letra válida.");
        }
        guessEditText.setText("");
    }

    private void updateGuessedWord(char letter) {
        StringBuilder updatedGuessedWord = new StringBuilder(guessedWord);
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == letter) {
                updatedGuessedWord.setCharAt(i, letter);
            }
        }
        guessedWord = updatedGuessedWord.toString();
    }

    private void updateUI() {
        wordTextView.setText(guessedWord);
        attemptsTextView.setText("Intentos: " + attempts);

        if (!guessedWord.contains("_")) {
            feedbackTextView.setText("¡Felicitaciones! ¡Has adivinado la palabra!");
        } else if (attempts >= 6) {
            feedbackTextView.setText("¡Se acabó el juego! La palabra era:" + selectedWord);
        }
    }
}

;


