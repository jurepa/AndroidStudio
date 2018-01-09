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

    @PrimaryKey
    private int id;
    private String nombre;
    private int idPersona;
}
