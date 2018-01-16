package com.example.pjarana.cronometrovm;

import android.arch.lifecycle.ViewModelProviders;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    Chronometer chrono;
    long tiempoComienzo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChronometerViewModel chronoVM= ViewModelProviders.of(this).get(ChronometerViewModel.class);
        chrono=(Chronometer)findViewById(R.id.cronometro);
        tiempoComienzo= SystemClock.elapsedRealtime();
        if(chronoVM.getTiempo()==null)
        {
            chronoVM.setTiempo(tiempoComienzo);
            chrono.setBase(tiempoComienzo);
        }
        else
        {
            chrono.setBase(chronoVM.getTiempo());
        }
        chrono.start();
    }
}
