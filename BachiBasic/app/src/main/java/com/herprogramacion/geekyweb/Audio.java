package com.herprogramacion.geekyweb;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class Audio extends Service{
    private final static int MAX_VOLUME = 100;
    private static float soundVolume=0;
    MediaPlayer objPlayer=new MediaPlayer();

    public void onCreate(){
        super.onCreate();
        objPlayer = MediaPlayer.create(this,R.raw.audio1);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        objPlayer.start();
        if(objPlayer.isLooping() != true){
        }
        return 1;
    }

    public void onStop(){
        objPlayer.stop();
        objPlayer.release();
    }

    public void onPause(){
        objPlayer.stop();
        objPlayer.release();
    }
    public void onDestroy(){
        objPlayer.stop();
        objPlayer.release();
    }
    public void manageVolumen(){
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - soundVolume) / Math.log(MAX_VOLUME)));
        soundVolume=volume;
        objPlayer.setVolume(volume, volume);
    }
    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }
}
