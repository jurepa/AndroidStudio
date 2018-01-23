package com.example.pjarana.pruebaroom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

/**
 * Created by pjarana on 17/01/18.
 */

public class ViewModelMascotas extends AndroidViewModel {

    private LiveData<List<Mascota>> mascotas=null;
    private MascotasRepository repositorio;

    public ViewModelMascotas(Application app)
    {
        super(app);
        //No furula porque el livedata es null, pero no lo puedo inicializar de ninguna forma
    }

    public LiveData<List<Mascota>> getMascotas() {
        return mascotas;
    }
    public void cargarListaMascotasDeUsuario(Application app, int id)
    {
        //this.mascotas.setValue(Database.getDatabase(app.getApplicationContext()).getUsuariosDao().getMascotasFromPersona(id));
        repositorio=new MascotasRepository(app,id);
        mascotas=repositorio.getMascotas();
    }
}
