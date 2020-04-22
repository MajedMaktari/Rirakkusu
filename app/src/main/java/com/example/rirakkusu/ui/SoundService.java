package com.example.rirakkusu.ui;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import static com.example.rirakkusu.R.raw.chris_zabriskie_04_the_oceans_continue_to_rise_short;

public class SoundService extends Service {

    public SoundService() {
    }
    MediaPlayer mediaPlayer;
    private final IBinder localBinder = new MyBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        soundPlayer();
        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.release();
        mediaPlayer = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public MediaPlayer soundPlayer() {
        mediaPlayer = MediaPlayer.create(SoundService.this, chris_zabriskie_04_the_oceans_continue_to_rise_short);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.start();
        return mediaPlayer;
    }
    public class MyBinder extends Binder{
        public SoundService getService() {
            return SoundService.this;
        }

        }

    }

