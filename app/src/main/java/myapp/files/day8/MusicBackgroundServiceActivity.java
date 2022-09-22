package myapp.files.day8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import myapp.files.day8.databinding.ActivityMusicBackgroundServiceBinding;
import myapp.files.day8.service.BackgroundService;

public class MusicBackgroundServiceActivity extends AppCompatActivity {

    ActivityMusicBackgroundServiceBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_music_background_service);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_music_background_service);
        binding.btnLaunchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMyServiceRunning(MusicBackgroundServiceActivity.class)){
                    //true
                    binding.btnLaunchMusic.setText("Stopped");
                    stopService(new Intent(MusicBackgroundServiceActivity.this, BackgroundService.class));
                }else {
                    //false
                    binding.btnLaunchMusic.setText("Started");
                    startService(new Intent(MusicBackgroundServiceActivity.this, BackgroundService.class));
                }
            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}