package com.example.pjarana.pruebaroom;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by pjarana on 9/01/18.
 */

@Entity(tableName = "Mascotas",
        foreignKeys =@ForeignKey(parentColumns = "id",entity = Persona.class,childColumns = "idPersona"))
public class Mascota {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private int idPersona;

    public Mascota(String nombre, int idPersona) {
        this.nombre = nombre;
        this.idPersona = idPersona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return this.getId()+" "+this.getNombre();
    }
}
