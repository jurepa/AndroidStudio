package com.example.pjarana.pruebaroom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.List;

/**
 * Created by pjarana on 22/01/18.
 */

public class UsuarioRepository {
    private MyDao database;
    private MutableLiveData<List<Persona>> listaUsuarios;

    public UsuarioRepository(Application app)
    {
        Database db=Database.getDatabase(app.getApplicationContext());
        database=db.getUsuariosDao();
        listaUsuarios=new MutableLiveData<>();
        listaUsuarios.setValue(database.getPersonas());
    }

    public MutableLiveData<List<Persona>> getListaUsuarios() {
        return listaUsuarios;
    }

    private static class insertAsyncTask extends AsyncTask<Void, Void, List<Persona>> {

        private MyDao miDao;

        public insertAsyncTask(MyDao dao) {
            miDao = dao;
        }

        @Override
        protected List<Persona> doInBackground(Void... voids) {
            return miDao.getPersonas();
        }

    }
}
