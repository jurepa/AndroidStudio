package com.iesnervion.usuario.listviewprueba;

import android.graphics.drawable.Drawable;

/**
 * Created by Usuario on 17/10/2017.
 *
 * Propiedades
 *
 * Nombre: string modificable y consultable
 * Tipo: string modificable y consultable
 * Icono: Drawable modificable y consultable
 * No tiene propiedades compartidas ni derivadas
 *
 * Getters y setters: Get y set de cada propiedad
 *
 *
 */

public class Pokemon
{
    private String nombre;
    private String[] tipo;
    private String nivelEvolucion;
    private int icono;
    private int sonido;

    //Constructor por defecto
    public Pokemon()
    {
        nombre="";
        tipo=new String[2];
        icono=R.drawable.ultrabola;
        sonido=0;
        nivelEvolucion="1";
    }
    //Constructor por parámetros
    public Pokemon(String nombre, String [] tipo, int icono, int sonido, String nivelEvolucion)
    {
        this.nivelEvolucion=nivelEvolucion;
        this.nombre=nombre;
        this.tipo=tipo;
        this.icono=icono;
        this.sonido=sonido;
    }
    //Constructor de copia
    public Pokemon(Pokemon p)
    {
        this.nivelEvolucion=p.nivelEvolucion;
        this.nombre=p.nombre;
        this.tipo=p.tipo;
        this.icono=p.icono;
        this.sonido=p.sonido;
    }
    //Getters y Setters
    public String getNombre()
    {
        return nombre;
    }
    public String [] getTipo()
    {
        return tipo;
    }
    public int getIcono()
    {
        return  icono;
    }
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    public void setTipo(String[]tipo)
    {
        this.tipo=tipo;
    }
    public void setIcono(int icono)
    {
        this.icono=icono;
    }
    public int getSonido()
    {
        return sonido;
    }
    public void setSonido(int sonido)
    {
        this.sonido=sonido;
    }
    public String getNivelEvolucion(){return nivelEvolucion; }
    public void setNivelEvolucion(String nivelEvolucion){ this.nivelEvolucion=nivelEvolucion;}
}
