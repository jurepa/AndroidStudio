package com.example.pjarana.pruebaroom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        new getAllPersonasAsyncTask(database).execute();

    }

    public MutableLiveData<List<Persona>> getListaUsuarios() {
        return listaUsuarios;
    }
 /*   public List<Persona> getAllPersonas()
    {
        List<Persona>lista=null;
        try {

             lista=new getAllPersonasAsyncTask(database).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lista;
    }*/

    private class getAllPersonasAsyncTask extends AsyncTask<Void, Void, List<Persona>> {

        private MyDao miDao;

        public getAllPersonasAsyncTask(MyDao dao) {
            miDao = dao;
        }

        @Override
        protected List<Persona> doInBackground(Void... voids) {

            return  miDao.getPersonas();
        }
        @Override
        protected void onPostExecute(List<Persona> lista) { //Este método recibe lo que devuelve el doInBackground
            UsuarioRepository.this.listaUsuarios.setValue(lista);
        }
    }
}
