package myapp.files.day8.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import myapp.files.day8.NotificationActivity;
import myapp.files.day8.R;

public class ForegroundService extends Service {

    private static final String CHANNEL_ID = "CHANNLE_ID";

    @Nullable
    @Override
    public IBinder onBind (Intent intent){
        return null;


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String data = intent.getStringExtra("foreground");

        createNotificationChannel();

        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, notificationIntent, 0);

        android.app.Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentIntent(pendingIntent)
                .setContentText(data)
                .setSmallIcon(R.drawable.mango)
                .build();

        startForeground(1, notification);
        Log.e("Foreground", "Started");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.e("Foreground","Destroyed|Stopped");

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }




    }
}
