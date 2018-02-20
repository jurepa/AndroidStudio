package com.iesnervion.pjarana.pruebaantesexamen;

import android.app.Application;
import android.arch.lifecycle.LiveData;

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
}
