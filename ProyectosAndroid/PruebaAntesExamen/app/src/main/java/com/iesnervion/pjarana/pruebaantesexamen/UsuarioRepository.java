package com.iesnervion.pjarana.pruebaantesexamen;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.iesnervion.pjarana.pruebaantesexamen.DAO.Database;
import com.iesnervion.pjarana.pruebaantesexamen.DAO.MyDAO;
import com.iesnervion.pjarana.pruebaantesexamen.DAO.Usuario;

import java.util.List;

/**
 * Created by pjarana on 20/02/18.
 */

public class UsuarioRepository {

    private MyDAO myDAO;
    private LiveData<List<Usuario>>usuariosLiveData;

    public UsuarioRepository(Application app)
    {
        myDAO= Database.getDatabase(app).getUsuariosDAO();
        usuariosLiveData=myDAO.getAllUsuarios();
    }

    public LiveData<List<Usuario>> getUsuariosLiveData() {
        return usuariosLiveData;
    }

    public void setUsuariosLiveData(LiveData<List<Usuario>> usuariosLiveData) {
        this.usuariosLiveData = usuariosLiveData;
    }
    public void insertUsuarios(Usuario...usuarios)
    {
        new insertUsuarios().execute(usuarios);
    }
    public void updateUsuarios(Usuario...usuarios){new updateUsuarios().execute(usuarios);}
    private class insertUsuarios extends AsyncTask<Usuario,Void,Void>
    {

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            myDAO.insertUsuarios(usuarios);
            return null;
        }
    }

    private class updateUsuarios extends AsyncTask<Usuario,Void,Void>
    {

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            myDAO.updateUsuarios(usuarios);
            return null;
        }
    }
}
