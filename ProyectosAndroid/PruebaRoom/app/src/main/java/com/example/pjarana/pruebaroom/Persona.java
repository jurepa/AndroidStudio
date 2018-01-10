package com.example.pjarana.pruebaroom;

import android.arch.persistence.room.*;

/**
 * Created by pjarana on 9/01/18.
 */

@Entity(tableName = "Personas")
public class Persona {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;

    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return this.nombre+" "+this.apellidos+" "+ this.edad;
    }
}
