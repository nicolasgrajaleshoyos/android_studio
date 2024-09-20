package com.example.apphabitossaludables;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.util.Calendar;
import java.util.List;

public class HabitoAdapter extends BaseAdapter {

    private Context context;
    private List<Habito> habitos;

    // Constructor
    public HabitoAdapter(Context context, List<Habito> habitos) {
        this.context = context;
        this.habitos = habitos;
    }

    // Devuelve el número de hábitos en la lista
    @Override
    public int getCount() {
        return habitos.size();
    }

    // Devuelve un hábito en una posición específica
    @Override
    public Object getItem(int position) {
        return habitos.get(position);
    }

    // Devuelve el ID de un ítem en una posición específica
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Renderiza cada elemento en la lista de hábitos
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_habito, parent, false);
        }

        // Referencias a los elementos de la vista
        TextView nombreHabito = convertView.findViewById(R.id.nombreHabito);
        TextView descripcionHabito = convertView.findViewById(R.id.descripcionHabito);
        TextView progresoHabito = convertView.findViewById(R.id.progresoHabito);
        ImageButton btnEliminarHabito = convertView.findViewById(R.id.btnEliminarHabito);
        Button btnIncrementarProgreso = convertView.findViewById(R.id.btnIncrementarProgreso);
        ImageButton btnColocarAlarma = convertView.findViewById(R.id.btnColocarAlarma);
        ImageButton btnModificarHabito = convertView.findViewById(R.id.btnModificarHabito);

        // Obtener el hábito actual
        Habito habito = habitos.get(position);

        // Establecer valores de texto para nombre, descripción y progreso del hábito
        nombreHabito.setText(habito.getNombre());
        descripcionHabito.setText(habito.getDescripcion());

        // Mostramos el progreso formateado como "su progreso es de + <número>"
        String textoProgreso = "su progreso es de  :" + habito.getProgreso();
        progresoHabito.setText(textoProgreso);

        // Botón para eliminar el hábito
        btnEliminarHabito.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Confirmar Eliminación")
                    .setMessage("¿Estás seguro de que quieres eliminar este hábito?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        habitos.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Hábito eliminado", Toast.LENGTH_SHORT).show();

                        // Reproducir sonido al eliminar
                        MediaPlayer mp = MediaPlayer.create(context, R.raw.error);
                        mp.start();
                        mp.setOnCompletionListener(mediaPlayer -> mediaPlayer.release());
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });

        // Botón para incrementar el progreso
        btnIncrementarProgreso.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Incrementar Progreso")
                    .setMessage("¿Estás seguro de que quieres incrementar el progreso?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        habito.setProgreso(habito.getProgreso() + 1);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Progreso incrementado", Toast.LENGTH_SHORT).show();
                        progresoHabito.setText("su progreso es de  : " + habito.getProgreso());

                        // Reproducir sonido al incrementar el progreso
                        MediaPlayer mp = MediaPlayer.create(context, R.raw.correcto);
                        mp.start();
                        mp.setOnCompletionListener(mediaPlayer -> mediaPlayer.release());
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });

        // Botón para configurar la alarma
        btnColocarAlarma.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(context, (view, hourOfDay, minuteOfHour) -> {
                setAlarm(habito, hourOfDay, minuteOfHour);
            }, hour, minute, true);

            timePickerDialog.show();
        });

        // Botón para modificar el hábito
        btnModificarHabito.setOnClickListener(v -> {
            Intent intent = new Intent(context, AgregarHabitoActivity.class);
            intent.putExtra("id", habito.getId());
            intent.putExtra("nombre", habito.getNombre());
            intent.putExtra("descripcion", habito.getDescripcion());
            ((Activity) context).startActivityForResult(intent, MainActivity.REQUEST_CODE_AGREGAR_HABITO);
        });

        return convertView;
    }

    // Configuración de la alarma
    @SuppressLint("ScheduleExactAlarm")
    private void setAlarm(Habito habito, int hourOfDay, int minuteOfHour) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("habitName", habito.getNombre());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minuteOfHour);
        calendar.set(Calendar.SECOND, 0);

        long triggerTime = calendar.getTimeInMillis();
        if (triggerTime < System.currentTimeMillis()) {
            triggerTime += AlarmManager.INTERVAL_DAY; // Configurar para el día siguiente si la hora ya pasó
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        Toast.makeText(context, "Alarma configurada para " + hourOfDay + ":" + minuteOfHour, Toast.LENGTH_SHORT).show();
    }
}
