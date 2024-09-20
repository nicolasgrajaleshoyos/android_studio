package com.example.apphabitossaludables;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarHabitoActivity extends Activity {

    public static final String EXTRA_ID = "id";

    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private Button btnGuardar;
    private String habitId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_habito);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar);

        Intent intent = getIntent();
        habitId = intent.getStringExtra(EXTRA_ID);

        if (habitId != null) {
            String nombre = intent.getStringExtra("nombre");
            String descripcion = intent.getStringExtra("descripcion");
            editTextNombre.setText(nombre);
            editTextDescripcion.setText(descripcion);
        }

        btnGuardar.setOnClickListener(v -> {
            String nombre = editTextNombre.getText().toString().trim();
            String descripcion = editTextDescripcion.getText().toString().trim();

            if (nombre.isEmpty() || descripcion.isEmpty()) {


                Toast.makeText(AgregarHabitoActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                MediaPlayer mp = MediaPlayer.create(AgregarHabitoActivity.this, R.raw.error);
                mp.start();
                mp.setOnCompletionListener(MediaPlayer::release);
                mp.setLooping(false);
                mp.start();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_ID, habitId);
            resultIntent.putExtra("nombre", nombre);
            resultIntent.putExtra("descripcion", descripcion);
            Toast.makeText(AgregarHabitoActivity.this, "Hábito guardado", Toast.LENGTH_SHORT).show();
            MediaPlayer mp = MediaPlayer.create(AgregarHabitoActivity.this, R.raw.correcto);
            mp.start();
            mp.setOnCompletionListener(MediaPlayer::release);
            mp.setLooping(false);
            mp.start();

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
