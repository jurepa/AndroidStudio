package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pjarana on 22/11/17.
 */

public class ProductoAComprar implements Parcelable {
    private String nombre;
    private int talla;
    private int precio;
    private String color;
    private int imagen;
    private String categoria;
    private String descripcion;


    public ProductoAComprar() {
        this.nombre = "";
        this.talla =0;
        this.precio = 0;

        this.categoria = "";
        this.descripcion = "";
    }

    //Constructor para productos de moda
    public ProductoAComprar(String nombre, int talla, int precio, String categoria, String descripcion) {
        this.nombre = nombre;
        this.talla = talla;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }
    //Constructor para muebles
    public ProductoAComprar(String nombre, int precio, String color, String categoria, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.color = color;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeInt(this.talla);
        dest.writeInt(this.precio);
        dest.writeString(this.color);
        dest.writeInt(this.imagen);
        dest.writeString(this.categoria);
        dest.writeString(this.descripcion);
    }

    protected ProductoAComprar(Parcel in) {
        this.nombre = in.readString();
        this.talla = in.readInt();
        this.precio = in.readInt();
        this.color = in.readString();
        this.imagen = in.readInt();
        this.categoria = in.readString();
        this.descripcion = in.readString();
    }

    public static final Parcelable.Creator<ProductoAComprar> CREATOR = new Parcelable.Creator<ProductoAComprar>() {
        @Override
        public ProductoAComprar createFromParcel(Parcel source) {
            return new ProductoAComprar(source);
        }

        @Override
        public ProductoAComprar[] newArray(int size) {
            return new ProductoAComprar[size];
        }
    };
}
