package com.example.apptreeandone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private ImageView iv1;

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        iv1 = findViewById(R.id.img_chicken);
        bt1 = findViewById(R.id.bt_Dog);
        bt2 = findViewById(R.id.bt_Buffalo);
        bt3 = findViewById(R.id.bt_gorilla);
        bt4 = findViewById(R.id.bt_Monkey);
    }

    //esto se utiliza para hacer el onclick para configurar la clase AudioActivityView3
    public void siguiente(View view) {
        startActivity(new Intent(this, FrutasActivityView2.class));
    }
    public void ShowAnimals(View view)
    {
        int id = view.getId();

        if (id == R.id.bt_Dog) {
            // Cambia la imagen a la del oso (bear.png) cuando se presione el botón del oso
            iv1.setImageResource(R.drawable.dog);

        } else if (id == R.id.bt_Buffalo) {
            // Cambia la imagen a la del pollito (chick.png) cuando se presione el botón del pollito
            iv1.setImageResource(R.drawable.buffalo);

        } else if (id == R.id.bt_gorilla) {
            // Cambia la imagen a la de la vaca (cow.png) cuando se presione el botón de la vaca
            iv1.setImageResource(R.drawable.gorilla);

        } else if (id == R.id.bt_Monkey) {
            // Cambia la imagen al narval (narwhal.png) cuando se presione el botón del narval
            iv1.setImageResource(R.drawable.monkey);
        }
    }

    public void NextActivityAudio(View view)
    {
        Intent i = new Intent(this, AudioActivityView3.class);
        startActivity(i);
    }



}
