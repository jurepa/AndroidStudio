package com.iesnervion.pjarana.pruebaantesexamen.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by pjarana on 20/02/18.
 */
@Dao
public interface MyDAO {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    void insertUsuarios(Usuario...u);
    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    void updateUsuarios(Usuario...u);
    @Delete
    void deleteUsuarios(Usuario...u);
    @Query("Select*from Usuarios")
    LiveData<List<Usuario>>getAllUsuarios();
    @Query("Select*from Usuarios where ID=:id")
    Usuario getUsuarioPorId(int id);
}
