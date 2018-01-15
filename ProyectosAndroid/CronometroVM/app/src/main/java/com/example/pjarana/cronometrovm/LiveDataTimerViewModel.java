package com.example.pjarana.cronometrovm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by pjarana on 15/01/18.
 */

public class LiveDataTimerViewModel extends ViewModel
{
    private static final int ONE_SECOND=1000;
    private MutableLiveData<Long> tiempoPasado=new MutableLiveData<>();
    private long tiempoInicial;

    public LiveDataTimerViewModel()
    {
        tiempoInicial= SystemClock.elapsedRealtime();
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long newValue=(SystemClock.elapsedRealtime()-tiempoInicial)/1000;
                tiempoPasado.postValue(newValue);
            }
        },ONE_SECOND,ONE_SECOND);
    }

    public LiveData<Long>getElapsedTime()
    {
        return this.tiempoPasado;
    }
}
