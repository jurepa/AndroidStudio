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
        LiveDataTimerViewModel chronoVM= ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        chrono=(Chronometer)findViewById(R.id.cronometro);
        if(chronoVM.getTiempo()==null)
        {
            tiempoComienzo= SystemClock.elapsedRealtime();
            chronoVM.setTiempo(tiempoComienzo);
            chrono.setBase(tiempoComienzo);
        }
        else
        {
            chronoVM.setTiempo(chronoVM.getTiempo());
        }
        chrono.start();
    }
}
