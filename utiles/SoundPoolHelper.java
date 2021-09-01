package com.example.polshu;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPoolHelper {
    private static SoundPool soundPool;

    private static void inicializar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }
    }

    public static int loadSound(Context context, int sound, int priority){
        if(soundPool== null){
            inicializar();
        }
       return  soundPool.load(context,sound,priority);
    }
    public static int loadSound(Context context, int sound){
        return  loadSound(context,sound, 1);
    }

    public static void play(int soundId, int leftVol, int rightVol, int priority, int loop, int rate){
        if(soundPool== null){
            inicializar();
        }
        soundPool.play(soundId,leftVol,rightVol,priority,loop,rate);
    }

    public static void play(int soundId, int leftVol, int rightVol, int priority, int loop){
        play(soundId,leftVol,rightVol,priority,loop,1);
    }
    public static void play(int soundId, int leftVol, int rightVol, int priority){
        play(soundId,leftVol,rightVol,priority,0);
    }

    public static void play(int soundId, int leftVol, int rightVol){
        play(soundId,leftVol,rightVol,0);
    }

    public static void play(int soundId){
        play(soundId,1,1);
    }

    public static void resume(int soundId){
        if(soundPool== null){
            inicializar();
        }
        soundPool.resume(soundId);
    }
    public  static void pause(int soundId){
        if(soundPool== null){
            inicializar();
        }
        soundPool.pause(soundId);
    }
    public  static void stop(int soundId){
        if(soundPool== null){
            inicializar();
        }
        soundPool.stop(soundId);
    }

    public static void setVolume(int soundId, int leftVol, int rightVol){
        if(soundPool== null){
            inicializar();
        }
        soundPool.setVolume(soundId,leftVol,rightVol);
    }
}


