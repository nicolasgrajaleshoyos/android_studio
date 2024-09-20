package com.example.apphabitossaludables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final int REQUEST_CODE_AGREGAR_HABITO = 1; // Hacerlo público
    private ListView listaHabitos;
    private HabitoAdapter habitoAdapter;
    private List<Habito> listaDeHabitos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaHabitos = findViewById(R.id.listaHabitos);
        Button btnAgregarHabito = findViewById(R.id.btnAgregarHabito);

        listaDeHabitos = new ArrayList<>();
        habitoAdapter = new HabitoAdapter(this, listaDeHabitos);
        listaHabitos.setAdapter(habitoAdapter);

        // Botón para agregar nuevo hábito
        btnAgregarHabito.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarHabitoActivity.class);
            startActivityForResult(intent, REQUEST_CODE_AGREGAR_HABITO);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AGREGAR_HABITO && resultCode == RESULT_OK) {
            if (data != null) {
                // Obtener los datos del hábito desde el Intent
                String id = data.getStringExtra(AgregarHabitoActivity.EXTRA_ID);
                String nombre = data.getStringExtra("nombre");
                String descripcion = data.getStringExtra("descripcion");

                // Validar que los datos no sean nulos o vacíos
                if (nombre != null && descripcion != null) {
                    if (id == null) { // Si no hay ID, se trata de un nuevo hábito
                        id = String.valueOf(System.currentTimeMillis()); // Generar un ID único
                        Habito nuevoHabito = new Habito(id, nombre, descripcion, 0); // 0 es el progreso inicial
                        listaDeHabitos.add(nuevoHabito);
                    } else {
                        // Buscar y actualizar el hábito existente
                        for (Habito habito : listaDeHabitos) {
                            if (habito.getId().equals(id)) {
                                habito.setNombre(nombre);
                                habito.setDescripcion(descripcion);
                                break;
                            }
                        }
                    }
                    habitoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Error: Datos incompletos", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            // El usuario canceló la operación
            Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar el hábito", Toast.LENGTH_SHORT).show();

        }

    }

}
