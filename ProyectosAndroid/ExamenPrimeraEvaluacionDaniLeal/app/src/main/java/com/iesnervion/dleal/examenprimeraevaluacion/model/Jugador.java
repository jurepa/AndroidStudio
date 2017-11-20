package com.iesnervion.dleal.examenprimeraevaluacion.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dleal on 7/12/16.
 */

public class Jugador implements Parcelable {

    private String nombre,posicion;
    private int img,altura,peso;

    public Jugador(String nombre, String posicion, int img, int altura, int peso) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.img = img;
        this.altura = altura;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }



    //METODOS PARA HACER EL OBJETO PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.nombre);
        dest.writeString(this.posicion);
        dest.writeValue(this.img);
        dest.writeValue(this.altura);
        dest.writeValue(this.peso);



    }

    protected Jugador(Parcel in) {
        this.nombre = in.readString();
        this.posicion = in.readString();
        this.img = (Integer) in.readValue(Integer.class.getClassLoader());
        this.altura =  (Integer) in.readValue(Integer.class.getClassLoader());
        this.peso =  (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel source) {
            return new Jugador(source);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };
}
