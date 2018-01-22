package com.example.pjarana.pruebaroom;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.provider.ContactsContract;
import android.view.View;

import java.util.List;

/**
 * Created by pjarana on 17/01/18.
 */

public class ViewModelMascotas extends ViewModel {

    private final MutableLiveData<List<Mascota>> mascotas=new MutableLiveData<List<Mascota>>();

    public ViewModelMascotas()
    {}

    public MutableLiveData<List<Mascota>> getMascotas() {
        return mascotas;
    }

    public void cargarListaMascotasDeUsuario(Application app, int id)
    {
        this.mascotas.setValue(Database.getDatabase(app.getApplicationContext()).getUsuariosDao().getMascotasFromPersona(id));
    }
}
