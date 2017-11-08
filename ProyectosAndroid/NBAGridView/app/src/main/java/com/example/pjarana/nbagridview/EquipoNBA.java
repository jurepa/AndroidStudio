package com.example.pjarana.nbagridview;

/**
 * Created by pjarana on 8/11/17.
 */

public class EquipoNBA {

    private String nombre;
    private int logo;

    public EquipoNBA()
    {
        this.nombre="";
        this.logo=0;
    }

    public EquipoNBA(String nombre, int logo)
    {
        this.nombre = nombre;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
