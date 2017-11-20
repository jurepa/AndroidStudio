package com.iesnervion.dleal.examenprimeraevaluacion;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dleal on 7/12/16.
 */

public class ViewHolderJugador {

    private TextView nombre;
    private TextView altura;
    private TextView peso;
    private TextView posicion;
    private ImageView imagen;

    public ViewHolderJugador(TextView nombre, TextView altura, TextView peso, TextView posicion, ImageView imagen) {
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.posicion = posicion;
        this.imagen = imagen;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getAltura() {
        return altura;
    }

    public TextView getPeso() {
        return peso;
    }

    public TextView getPosicion() {
        return posicion;
    }

    public ImageView getImagen() {
        return imagen;
    }
}
