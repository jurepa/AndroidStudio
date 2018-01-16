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

public class ChronometerViewModel extends ViewModel
{
    @Nullable
    private Long tiempo;

    @Nullable
    public Long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long startTime) {
        this.tiempo = startTime;
    }
}
