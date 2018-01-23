package com.example.pjarana.pruebaroom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

/**
 * Created by pjarana on 23/01/18.
 */

public class MascotasRepository  {

    private MyDao dao;
    private LiveData<List<Mascota>>mascotas;

    public MascotasRepository(Application app,int id)
    {
        Database db=Database.getDatabase(app.getApplicationContext());
        dao=db.getUsuariosDao();
        mascotas=dao.getMascotasFromPersona(id);

    }

    public LiveData<List<Mascota>> getMascotas() {
        return mascotas;
    }
}
