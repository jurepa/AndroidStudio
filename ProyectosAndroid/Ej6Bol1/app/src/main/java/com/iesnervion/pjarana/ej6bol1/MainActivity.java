package com.iesnervion.pjarana.ej6bol1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String jarana="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cambiarFoto(View v)
    {

        ImageButton imagen=(ImageButton)findViewById(R.id.imagenBoton);

        if(jarana!="foto")
        {
            imagen.setImageResource(R.drawable.yo);
            reproducirAudio();
            jarana="foto";
        }
        else
        {
            imagen.setImageResource(R.drawable.maxresdefault);
            reproducirAudio();
            jarana="";
        }
    }
    public void reproducirAudio()
    {
        Random random=new Random();
        int numero= random.nextInt(5)+1;
        MediaPlayer mp1= MediaPlayer.create(this,R.raw.voz0001);
        MediaPlayer mp2= MediaPlayer.create(this,R.raw.voz0002);
        MediaPlayer mp3= MediaPlayer.create(this,R.raw.voz0003);
        MediaPlayer mp4= MediaPlayer.create(this,R.raw.voz0004);
        MediaPlayer mp5= MediaPlayer.create(this,R.raw.voz0005);
        switch(numero)
        {
            case 1:
                mp1.start();
                break;
            case 2:
                mp2.start();
                break;
            case 3:
                mp3.start();
                break;
            case 4:
                mp4.start();
                break;
            case 5:
                mp5.start();
                break;
        }

    }
}
