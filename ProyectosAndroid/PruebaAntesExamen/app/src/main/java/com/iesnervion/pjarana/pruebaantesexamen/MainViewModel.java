package com.iesnervion.pjarana.pruebaantesexamen;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.iesnervion.pjarana.pruebaantesexamen.DAO.Usuario;

import java.util.List;

/**
 * Created by pjarana on 20/02/18.
 */

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Usuario>>usuariosLiveData;
    private MutableLiveData<Usuario>usuarioSeleccionado=new MutableLiveData<>();
    private UsuarioRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository=new UsuarioRepository(application);
        usuariosLiveData=repository.getUsuariosLiveData();
    }

    public LiveData<List<Usuario>> getUsuariosLiveData() {
        return usuariosLiveData;
    }

    public void setUsuariosLiveData(LiveData<List<Usuario>> usuariosLiveData) {
        this.usuariosLiveData = usuariosLiveData;
    }

    public MutableLiveData<Usuario> getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }
}
