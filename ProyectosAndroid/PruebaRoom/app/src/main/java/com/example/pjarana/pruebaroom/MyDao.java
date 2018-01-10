package com.example.pjarana.pruebaroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by pjarana on 9/01/18.
 */
@Dao
public interface MyDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insertPersonas(Persona... p);
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insertMascotas(Mascota...m);
    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updatePersonas(Persona...p);
    @Delete
    public void deletePersonas(Persona...p);
    @Query("Select * from Personas")
    public List<Persona> getPersonas();
    @Query("Select*from Personas where id=:id")
    public Persona getPersona(int id);
    @Query("Select * from Mascotas Where idPersona=:idPersona")
    public List<Mascota> getMascotasFromPersona(int idPersona);

}
