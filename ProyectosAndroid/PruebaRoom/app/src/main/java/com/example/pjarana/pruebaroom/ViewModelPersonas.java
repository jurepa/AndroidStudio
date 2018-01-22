package com.example.pjarana.pruebaroom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjarana on 17/01/18.
 */

public class ViewModelPersonas extends AndroidViewModel {

    private final MutableLiveData<List<Persona>> personas;
    private UsuarioRepository repositorio;

    public ViewModelPersonas(Application app)
    {
        super(app);
        repositorio=new UsuarioRepository(app);
        personas=repositorio.getListaUsuarios();
    }

    public LiveData<List<Persona>> getPersonas()
    {
        return personas;
    }
    public void cargarLista(final Application app)
    {

        new AsyncTask<Void,Void,List<Persona>>()
        {
            @Override
            protected List<Persona> doInBackground(Void... voids) {
                List<Persona>lista=Database.getDatabase(app.getApplicationContext()).getUsuariosDao().getPersonas();
                return lista;
            }
            @Override
            protected void onPostExecute(List<Persona> lista) {
                ViewModelPersonas.this.personas.setValue(lista);
            }
        }.execute();
    }
}
