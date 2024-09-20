package com.example.apphabitossaludables;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Configurar el canal de notificación para versiones de Android O y superiores
        String channelId = "habit_notification_channel";
        String channelName = "Hábito Notification Channel";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Notificaciones para recordatorios de hábitos");
            notificationManager.createNotificationChannel(channel);
        }

        // Crear la notificación
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Uso de FLAG_IMMUTABLE si no necesitas cambiar el PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Crear la notificación con NotificationCompat para soportar versiones anteriores
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("¡Hora de tu hábito!")
                .setContentText("Es momento de trabajar en tu hábito.")
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Cambia este ícono por el tuyo o usa uno predeterminado
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH); // Cambiado a PRIORITY_HIGH para mayor visibilidad

        // Mostrar la notificación
        if (notificationManager != null) {
            notificationManager.notify(0, builder.build());
        }
    }
}
