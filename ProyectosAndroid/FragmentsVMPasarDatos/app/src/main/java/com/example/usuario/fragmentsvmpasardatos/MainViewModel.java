package com.example.usuario.fragmentsvmpasardatos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by usuario on 17/02/2018.
 */

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> resultado=new MutableLiveData<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getResultado() {
        return resultado;
    }

    public void setResultado(MutableLiveData<Integer> resultado) {
        this.resultado = resultado;
    }
}
