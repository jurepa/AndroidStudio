package com.iesnervion.pjarana.fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

/**
 * Created by usuario on 17/02/2018.
 */

public class MainVM extends AndroidViewModel {

    private LiveData<Integer> resultado;
    public MainVM(@NonNull Application application) {
        super(application);

    }

    public LiveData<Integer> getResultado() {
        return resultado;
    }

    public void setResultado(LiveData<Integer> resultado) {
        this.resultado = resultado;
    }
}
