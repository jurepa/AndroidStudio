package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by pjarana on 22/11/17.
 */

public class Producto /*implements Parcelable*/ {

    private String nombre;
    private Talla talla;
    private int precio;
    private Color[] color;
    private String categoria;
    private String descripcion;

    public Producto() {
        this.nombre = "";
        this.talla =new Talla();
        this.precio = 0;
        this.categoria = "";
        this.descripcion = "";
    }

    //Constructor para productos de moda
    public Producto(String nombre, Talla talla, int precio, String categoria, String descripcion) {
        this.nombre = nombre;
        this.talla = talla;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }
    //Constructor para muebles
    public Producto(String nombre, int precio, Color[] color, String categoria, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.color = color;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Color[] getColor() {
        return color;
    }

    public void setColor(Color[] color) {
        this.color = color;
    }

   /* @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeParcelable(this.talla, flags);
        dest.writeInt(this.precio);
        dest.writeParcelableArray(this.color, flags);
        dest.writeString(this.categoria);
        dest.writeString(this.descripcion);
    }

    protected Producto(Parcel in) {
        this.nombre = in.readString();
        this.talla = in.readParcelable(Talla.class.getClassLoader());
        this.precio = in.readInt();
        this.color = in.readParcelable(Color[].class.getClassLoader());
        this.categoria = in.readString();
        this.descripcion = in.readString();
    }

    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel source) {
            return new Producto(source);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };*/
}
